package order;

import java.util.HashMap;

public class OrderController {

    private OrderInputBoundary interactor;

    public OrderController(OrderInputBoundary interactor) {
        this.interactor = interactor;
    }

    public OrderResponseModel requestOrder(HashMap<Long, Integer> requestedItems) {
        OrderRequestModel request = new OrderRequestModel(requestedItems);
        return interactor.requestOrder(request);
    }
}
