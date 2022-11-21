package order;

import java.util.HashMap;

public class OrderRequestModel {

    private final HashMap<Long, Integer> orderQuantities;

    public OrderRequestModel(HashMap<Long, Integer> orderQuantities) {
        this.orderQuantities = orderQuantities;
    }
    public HashMap<Long, Integer> getOrderQuantities() {
        return orderQuantities;
    }
}
