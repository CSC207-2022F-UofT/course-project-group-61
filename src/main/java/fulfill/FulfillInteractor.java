package fulfill;

import database.FacilityDb;
import database.FacilityDbGateway;
import entities.Facility;
import entities.Order;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class FulfillInteractor {
    private Order order;
    private FacilityDb facilityDb;

    private Facility store;
    private Facility warehouse;

    public FulfillInteractor(){
        this.facilityDb = new FacilityDbGateway();
        // TODO: also make this take in the presenter (or at least the interface)
    }

    public void addOrder(Order order){
        /*
        Adds the order object for this order being fulfilled as well as the store, and warehouse corresponding to the
        order.
         */
        this.order = order;

        this.store = findStore(order.getStoreID());
        this.warehouse = findWarehouse(order.getWarehouseID());
    }

    public HashMap<Integer, Boolean> attemptUpdateInventory(){
        /*
        Checks if an order can be fulfilled. That is it checks for each item in the order that the warehouse has enough
        items to fulfill the order and if not it asks the user to confirm whether or not they want to still fulfill the
        order with lower stock.
         */

        HashMap<Integer, Integer> orderQuantities = order.getOrderQuantities();
        HashMap<Integer, Boolean> outOfStock = outOfOrderChecker(orderQuantities, warehouse);

        if(outOfStock.containsValue(false)){
            updateInventories(store, warehouse, orderQuantities);

            Date currentDate = new Date();
            order.fulfillOrder(currentDate);
        }

        return outOfStock;
    }

    public void confirmUpdateInventory(){
        /*
        Assumes the attemptUpdateInventory has been run before and that at least one item was out of stock. This
        method will fulfill the order regardless of the out of stock or low items by updating the stock the most it can.
         */
        HashMap<Integer, Integer> orderQuantites = order.getOrderQuantities();
        orderQuantites = minimumOrderQuantities(orderQuantites, warehouse);

        updateInventories(store, warehouse, orderQuantites);

        Date currentDate = new Date();
        order.fulfillOrder(currentDate);
    }

    private HashMap<Integer, Integer> minimumOrderQuantities(HashMap<Integer, Integer> orderQuantites, Facility warehouse) {
        /*
        Returns a hashmap of the minimum order quantities. That is for each order the minimum that the warehouse can
        provide.
         */
        HashMap<Integer, Integer> minOrderQuantities = new HashMap<>();

        for(int UPC: orderQuantites.keySet()){
            minOrderQuantities.put(UPC, Math.min(orderQuantites.get(UPC), warehouse.getUPCQuantity(UPC)));
        }

        return minOrderQuantities;
    }

    private void updateInventories(Facility store, Facility warehouse, HashMap<Integer, Integer> orderQuantites){
        /*
        Updates the store and warehouse to reduce and increase (respectively) their inventories. Assumes the order
        quantities is the exact amount that is needed to update; that is this function won't deal with checking if the
        warehouse has enough stock.
         */
        for(int UPC: orderQuantites.keySet()){
            store.addProduct(UPC, orderQuantites.get(UPC));
            warehouse.removeProduct(UPC, orderQuantites.get(UPC));
        }

        facilityDb.updateFacility(store);
        facilityDb.updateFacility(warehouse);
    }

    private Facility findWarehouse(UUID warehouseID){
        return facilityDb.getFacility(warehouseID);
    }

    private Facility findStore(UUID storeID){
        return facilityDb.getFacility(storeID);
    }

    private HashMap<Integer, Boolean> outOfOrderChecker(HashMap<Integer, Integer> orderQuantities, Facility warehouse){
        /*
        Checks if the warehouse has enough items in storage for all the items in the order. The hashmap corresponds to
        UPC codes and booleans, which states whether or not the warehouse has enough in storage for each product.
         */
        HashMap<Integer, Boolean> outOfStock = new HashMap<>();

        // Checks that for each item is in stock for the warehouse
        for(int UPC: orderQuantities.keySet()){
            if(warehouse.getUPCQuantity(UPC) < orderQuantities.get(UPC)){
                outOfStock.put(UPC, true);
            }else{
                outOfStock.put(UPC, false);
            }
        }

        return outOfStock;
    }
}
