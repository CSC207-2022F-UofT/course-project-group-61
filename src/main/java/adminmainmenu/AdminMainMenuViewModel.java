package adminmainmenu;

import java.util.Observable;

public class AdminMainMenuViewModel extends Observable {

    private boolean visible;

    public AdminMainMenuViewModel() {
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
