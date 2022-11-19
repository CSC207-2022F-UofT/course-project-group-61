package warehousemainmenu;

import java.util.Observable;

public class WarehouseMainMenuViewModel extends Observable {

    private boolean visible;

    public WarehouseMainMenuViewModel() {
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        setChanged();
        notifyObservers();
    }
}
