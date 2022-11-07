package order;

//import entities.Facility;

public class OrderInteractor implements OrderInputBoundary {

    @Override
    public OrderResponseModel requestOrder(OrderRequestModel model) {

        //TODO: Separate orders by availability of items in each facility

//        for (Facility facility : getFacilities()) {
//            for (int upc : model.getOrderQuantities().keySet()) {
//
//            }
//        }
//
//        return null;
        return null;
    }
}
