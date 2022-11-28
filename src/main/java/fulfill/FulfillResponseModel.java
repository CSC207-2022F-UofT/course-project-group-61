package fulfill;

import entities.Order;

import java.util.HashMap;

public class FulfillResponseModel {
    private final FulfillStatus status;
    private final HashMap<Long, Boolean> outOfStockItems;

    private final Order order;

    public FulfillResponseModel(FulfillStatus status, HashMap<Long, Boolean> outOfStockItems, Order order){
        this.status = status;
        this.outOfStockItems = outOfStockItems;
        this.order = order;
    }

    public FulfillStatus getStatus() {
        return status;
    }

    public HashMap<Long, Boolean> getOutOfStockItems() {
        return outOfStockItems;
    }

    public Order getOrder() {
        return order;
    }
}
