package userlogin.order;

public interface OrderInputBoundary {
    OrderResponseModel requestOrder(OrderRequestModel model);
    boolean upcExists(Long upc);
    String getProductName(Long upc);
}
