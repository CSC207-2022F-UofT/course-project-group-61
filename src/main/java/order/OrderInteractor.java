package order;

import database.FacilityDb;
import database.OrderDb;
import entities.*;

import java.util.*;

public class OrderInteractor implements OrderInputBoundary {

    private OrderOutputBoundary presenter;
    private OrderDb orderDb;
    private FacilityDb facilityDb;

    public OrderInteractor(OrderOutputBoundary presenter, OrderDb orderDb, FacilityDb facilityDb) {
        this.presenter = presenter;
        this.orderDb = orderDb;
        this.facilityDb = facilityDb;
    }

    @Override
    public OrderResponseModel requestOrder(OrderRequestModel request) {
        FacilityUser requestingUser = (FacilityUser) UserSession.getUserSession();
        Collection<Facility> facilityCollection = facilityDb.getAllFacilities().values();
        HashMap<Long, Integer> orderedItems = (HashMap<Long, Integer>) request.getOrderQuantities().clone();
        for (Facility f : facilityCollection) {
             if (f.getFacilityType() == FacilityType.WAREHOUSE) {
                 HashMap<Long, Integer> items = new HashMap<>();
                 for (Long upc : request.getOrderQuantities().keySet()) {
                    int available = f.getUPCQuantity(upc);
                    int requested = request.getOrderQuantities().get(upc);
                    request.getOrderQuantities().put(upc, requested - Math.min(available, requested));
                    items.put(upc, Math.min(available, requested));
                 }

                 boolean hasQuantity = false;
                 for (int q : items.values()) {
                     if (q > 0) {
                         hasQuantity = true;
                         break;
                     }
                 }

                 if (hasQuantity) {
                     Order order = new Order(f.getFacilityID(),
                             requestingUser.getFacilityID(),
                             requestingUser.getUsername(),
                             items,
                             new Date());
                     orderDb.updateOrder(order);
                 }
             }
        }

        for (Long upc : orderedItems.keySet()) {
            orderedItems.put(upc, orderedItems.get(upc) - request.getOrderQuantities().get(upc));
        }

        OrderResponseModel response = new OrderResponseModel(orderedItems, request.getOrderQuantities());
        // TODO: Call presenter

        return response;
    }
}
