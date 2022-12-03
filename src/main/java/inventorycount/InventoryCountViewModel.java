package inventorycount;
import java.util.Observable;

public class InventoryCountViewModel extends Observable{
    private boolean visible;

    public InventoryCountViewModel() {
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

}
