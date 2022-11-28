package fulfill;

import database.FacilityDb;
import database.FacilityDbGateway;
import database.OrderDb;
import database.OrderDbGateway;
import entities.Facility;
import entities.Order;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class FulfillInteractor implements FulfillInputBoundry{

    private final FulfillOutputBoundry outputBoundry;

    private FacilityDb facilityDb;
    private OrderDb orderDb;

    public FulfillInteractor(FulfillOutputBoundry outputBoundry){
        this.facilityDb = new FacilityDbGateway();
        this.orderDb = new OrderDbGateway();

        this.outputBoundry = outputBoundry;
    }

    public FulfillResponseModel attemptUpdateInventory(FulfillRequestModel requestModel){
        /*
        Checks if an order can be fulfilled. That is it checks for each item in the order that the warehouse has enough
        items to fulfill the order and if not it asks the user to confirm whether or not they want to still fulfill the
        order with lower stock.
        */
        // Creates objects to more easily work with them
        Facility store = findFacility(requestModel.getStoreID());
        Facility warehouse = findFacility(requestModel.getWarehouseID());
        Order order = findOrder(requestModel.getOrderID());

        HashMap<Long, Boolean> outOfStock = outOfOrderChecker(order.getOrderQuantities(), warehouse);

        // Checks if any items are out of stock, if so show a failed reponse
        if(outOfStock.containsValue(true)){
            FulfillResponseModel returnModel = new FulfillResponseModel(FulfillStatus.OUT_OF_STOCK, outOfStock, order);

            outputBoundry.prepareOutOfStockView(returnModel);
            return returnModel;
        }

        // Updates inventories of the facilities and updates order to be fulfilled
        updateInventories(store, warehouse, order.getOrderQuantities());
        order.fulfillOrder(new Date());
        orderDb.updateOrder(order);

        FulfillResponseModel returnModel = new FulfillResponseModel(FulfillStatus.SUCCESS, null, order);

        outputBoundry.prepareSuccessView(returnModel);
        return returnModel;
    }

    public FulfillResponseModel confirmUpdateInventory(FulfillRequestModel requestModel){
        /*
        Assumes the attemptUpdateInventory has been run before and that at least one item was out of stock. This
        method will fulfill the order regardless of the out of stock or low items by updating the stock the most it can.
        */
        // Creates objects to more easily work with them
        Facility store = findFacility(requestModel.getStoreID());
        Facility warehouse = findFacility(requestModel.getWarehouseID());
        Order order = findOrder(requestModel.getOrderID());

        // Finds the minimum quantities you can update by
        HashMap<Long, Integer> minimumQuantities = minimumOrderQuantities(order.getOrderQuantities(), warehouse);

        // Updates inventories with items and marks the order as fulfilled
        updateInventories(store, warehouse, minimumQuantities);
        order.fulfillOrder(new Date());
        orderDb.updateOrder(order);

        FulfillResponseModel returnModel = new FulfillResponseModel(FulfillStatus.SUCCESS, null, order);

        outputBoundry.prepareSuccessView(returnModel);
        return returnModel;
    }

    public FulfillResponseModel newSelectedOrder(FulfillRequestModel requestModel){
        Order newOrder = findOrder(requestModel.getOrderID());

        FulfillResponseModel model = new FulfillResponseModel(FulfillStatus.NOT_APPLICABLE, null, newOrder);
        outputBoundry.prepareNewOrderView(model);

        // TODO: add this
        return model;
    }

    public FulfillResponseModel backToMainMenu(FulfillRequestModel requestModel){
        /*
        Handles the case of the user wanting to go back to the warehouse main menu view
         */
        FulfillResponseModel responseModel = new FulfillResponseModel(null, null, null);
        outputBoundry.prepareWarehouseMainMenuView(responseModel);
        return responseModel;
    }

    private HashMap<Long, Integer> minimumOrderQuantities(HashMap<Long, Integer> orderQuantites, Facility warehouse) {
        /*
        Returns a hashmap of the minimum order quantities. That is for each order the minimum that the warehouse can
        provide.
         */
        HashMap<Long, Integer> minOrderQuantities = new HashMap<>();

        for(long UPC: orderQuantites.keySet()){
            minOrderQuantities.put(UPC, Math.min(orderQuantites.get(UPC), warehouse.getUPCQuantity(UPC)));
        }

        return minOrderQuantities;
    }

    private void updateInventories(Facility store, Facility warehouse, HashMap<Long, Integer> orderQuantites){
        /*
        Updates the store and warehouse to reduce and increase (respectively) their inventories. Assumes the order
        quantities is the exact amount that is needed to update; that is this function won't deal with checking if the
        warehouse has enough stock.
         */
        for(long UPC: orderQuantites.keySet()){
            store.addProduct(UPC, orderQuantites.get(UPC));
            warehouse.removeProduct(UPC, orderQuantites.get(UPC));
        }

        facilityDb.updateFacility(store);
        facilityDb.updateFacility(warehouse);
    }

    private HashMap<Long, Boolean> outOfOrderChecker(HashMap<Long, Integer> orderQuantities, Facility warehouse){
        /*
        Checks if the warehouse has enough items in storage for all the items in the order. The hashmap corresponds to
        UPC codes and booleans, which states whether or not the warehouse has enough in storage for each product.
         */
        HashMap<Long, Boolean> outOfStock = new HashMap<>();

        // Checks that for each item is in stock for the warehouse
        for(long UPC: orderQuantities.keySet()){
            if(warehouse.getUPCQuantity(UPC) < orderQuantities.get(UPC)){
                outOfStock.put(UPC, true);
            }else{
                outOfStock.put(UPC, false);
            }
        }

        return outOfStock;
    }

    private Facility findFacility(UUID facilityID){
        return facilityDb.getFacility(facilityID);
    }

    private Order findOrder(UUID orderID){
        return orderDb.getOrder(orderID);
    }
}
