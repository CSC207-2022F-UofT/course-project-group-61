package userlogin.order;

import java.util.HashMap;

public class OrderResponseModel {

    private HashMap<Long, Integer> orderedItems;
    private HashMap<Long, Integer> unavailableItems;

    public OrderResponseModel(HashMap<Long, Integer> orderedItems, HashMap<Long, Integer> unavailableItems) {
        this.orderedItems = orderedItems;
        this.unavailableItems = unavailableItems;
    }

    public HashMap<Long, Integer> getOrderedItems() {
        return orderedItems;
    }

    public HashMap<Long, Integer> getUnavailableItems() {
        return unavailableItems;
    }
}
