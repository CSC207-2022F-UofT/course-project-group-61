package warehousemainmenu;

import java.util.Observable;

public class WarehouseMainMenuViewModel extends Observable {

    private boolean visible;

    public WarehouseMainMenuViewModel() {
        setVisible(false);
    }

    public boolean isVisible() {
        return visible;
    }

    /* Sets ViewModel to visible/invisible and alerts listeners (View). */
    public void setVisible(boolean visible) {
        this.visible = visible;
        setChanged();
        notifyObservers();
    }
}
