package fulfill;

import entities.Facility;
import entities.Order;
import org.junit.internal.runners.statements.Fail;

import java.util.HashMap;
import java.util.UUID;

public class FulfillController {
    private FulfillInteractor interactor;

    public FulfillController(){
            interactor = new FulfillInteractor(); // TODO: make it take in the presenter too
    }

    public void addOrder(Order order){
        interactor.addOrder(order);
    }

    public void attemptFulfill(){
        /*
        Attempts to fulfill an order. Note that this can fail is the warehouse is out of stock on some items.
         */
        HashMap<Integer, Boolean> attemptSuccess = interactor.attemptUpdateInventory();

        // Checks if the warehouse is out of stock on any specific items
        if(attemptSuccess.containsValue(true)){
            ; // TODO: presenter logic for some items are out of stock
        }else{
            ; // TODO: presenter logic for the user fully fulfilled the order
        }
    }

    public void confirmFulfill(){
        /*
        After attempting to fulfill and being rejected, this will confirm the fulfill no matter how low on stock some
        items are.
         */
        interactor.confirmUpdateInventory();
        // TODO: presenter logic for confirming an order is fulfilled
    }
}
