package newitem;

import java.util.Observable;


public class NewItemViewModel extends Observable {

    private boolean visible;
    private NewItemStatus status;
    private boolean isStatusChanged;



    public NewItemViewModel() {
        setVisible(false);
    }

    public void changeStatus(NewItemStatus status) {
        isStatusChanged = true;
        this.status = status;
        setChanged();
        notifyObservers();
        isStatusChanged = false;
        this.status = null;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        setChanged();
        notifyObservers();
    }

    public boolean getStatusChanged() {
        return isStatusChanged;
    }

    public NewItemStatus getStatus() {
        return status;
    }

}
