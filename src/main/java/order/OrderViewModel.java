package order;

import java.util.Observable;

public class OrderViewModel extends Observable {

    private boolean visible;
    private boolean orderComplete;

    public OrderViewModel() {
        setVisible(false);
        orderComplete = false;
    }

    public void setVisible(boolean b) {
        visible = b;
        setChanged();
        notifyObservers();
    }

    public boolean isVisible() {
        return visible;
    }

    public void orderCompleted() {
        orderComplete = true;
        setChanged();
        notifyObservers();
        orderComplete = false;
    }

    public boolean isOrderComplete() {
        return orderComplete;
    }
}
