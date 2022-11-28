package fulfill;

import java.util.UUID;

public class FulfillController {

    private FulfillInputBoundry inputBoundry;

    public FulfillController(){
        this.inputBoundry = new FulfillInteractor();
    }

    public FulfillResponseModel attemptFulfill(UUID storeID, UUID warehouseID, UUID orderID){
        /*
        Attempts to fulfill an order. Note that this can fail is the warehouse is out of stock on some items.
         */
        FulfillRequestModel requestModel = new FulfillRequestModel(storeID, warehouseID, orderID);
        return inputBoundry.attemptUpdateInventory(requestModel);
    }

    public FulfillResponseModel confirmFulfill(UUID storeID, UUID warehouseID, UUID orderID){
        /*
        After attempting to fulfill and being rejected, this will confirm the fulfill no matter how low on stock some
        items are.
         */
        FulfillRequestModel requestModel = new FulfillRequestModel(storeID, warehouseID, orderID);
        return inputBoundry.confirmUpdateInventory(requestModel);
    }
}
