package dailysales;

import java.util.Observable;

public class DailySalesViewModel extends Observable {

    private boolean visible;
    private boolean success;

    public DailySalesViewModel() {
        setVisible(false);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        setChanged();
        notifyObservers();
    }

    public boolean isSuccess() {
        return success;
    }

    private void setSuccess(boolean success) {
        this.success = success;
    }

    public void completed() {
        setSuccess(true);
        setChanged();
        notifyObservers();
        setSuccess(false);
    }
}
