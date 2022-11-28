package fulfill;

import entities.Facility;
import entities.Order;
import org.junit.internal.runners.statements.Fail;

import javax.swing.*;
import java.util.HashMap;
import java.util.UUID;

public class FulfillController {

    private FulfillInputBoundry inputBoundry;

    public FulfillController(FulfillOutputBoundry outputBoundry){
        this.inputBoundry = new FulfillInteractor(outputBoundry);
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

    public FulfillResponseModel newSelectedOrder(Object[][] data, int indexSelected){
        /*
        Updates the UI to show the newly selected order to fulfill
         */
        UUID orderID = (UUID) (data[indexSelected][0]); // TODO: fix magic #
        FulfillRequestModel requestModel = new FulfillRequestModel(null, null, orderID);
        return inputBoundry.newSelectedOrder(requestModel);
    }

    public FulfillResponseModel backToMainMenu(){
        /*
        After the user clicked the back button it informs the interactor to change the UI back to the main menu
         */
        return inputBoundry.backToMainMenu(new FulfillRequestModel(null, null, null));
    }
}
