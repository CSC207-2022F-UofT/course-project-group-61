package fulfill;

import entities.Order;

import java.util.HashMap;
import java.util.Observable;

public class FulfillViewModel extends Observable {
    private boolean orderInfoVisible = false;
    private Order order;

    private boolean failedFulfillment = false;
    private boolean successfulFulfillment = false;
    private HashMap<Long, Boolean> outOfStock;

    private boolean visible = false;

    public FulfillViewModel(){
    }

    public void addNewOrder(Order order){
        /*
        Informs the view of a new order being selected
         */
        orderInfoVisible = true;
        this.order = order;

        setChanged();
        notifyObservers();

        orderInfoVisible = false;
    }

    public void successfulFulfillment(){
        /*
        Informs the view of a successful fulfillment of the order
         */
        successfulFulfillment = true;
        outOfStock = null;

        setChanged();
        notifyObservers();

        successfulFulfillment = false;
    }

    public void failedFulfillment(HashMap<Long, Boolean> outOfStock){
        /*
        Informs the view that a fulfillment has failed
         */
        failedFulfillment = true;
        this.outOfStock = outOfStock;

        setChanged();
        notifyObservers();

        failedFulfillment = false;
    }

    public void setVisible(boolean visible){
        this.visible = visible;

        setChanged();
        notifyObservers();
    }

    public boolean getSuccessfulFulfillment() {
        return successfulFulfillment;
    }

    public boolean getFailedFulfillment(){
        return failedFulfillment;
    }

    public HashMap<Long, Boolean> getOutOfStock() {
        return outOfStock;
    }

    public boolean getOrderInfoVisible(){
        return orderInfoVisible;
    }

    public Order getOrder() {
        return order;
    }

    public boolean isVisible() {
        return visible;
    }
}
