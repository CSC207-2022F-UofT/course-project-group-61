package adminmainmenu;

import java.util.Observable;

public class AdminMainMenuViewModel extends Observable {

    private boolean visible;

    public AdminMainMenuViewModel() {
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
