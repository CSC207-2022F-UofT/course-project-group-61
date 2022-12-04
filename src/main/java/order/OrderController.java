package order;

import java.util.HashMap;

public class OrderController {

    private final OrderInputBoundary interactor;

    public OrderController(OrderInputBoundary interactor) {
        this.interactor = interactor;
    }

    public OrderResponseModel requestOrder(HashMap<Long, Integer> requestedItems) {
        OrderRequestModel request = new OrderRequestModel(requestedItems);
        return interactor.requestOrder(request);
    }

    public boolean upcExists(Long upc) {
        return interactor.upcExists(upc);
    }
    public String getProductName(Long upc) {
        return interactor.getProductName(upc);
    }

    public void returnToMenu() {
        interactor.returnToMenu();
    }
}
