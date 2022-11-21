package itemlookup;

import userlogin.LoginStatus;

import java.util.Observable;

public class ItemLookupViewModel extends Observable {

    private boolean visible;
    private boolean failed;
    private LoginStatus failedReason;

    public ItemLookupViewModel() {
    }

    public

    public void failed(LoginStatus status) {
        failed = true;
        failedReason = status;
        setChanged();
        notifyObservers();
        failed = false;
        failedReason = null;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        setChanged();
        notifyObservers();
    }

    public boolean getFailed() {
        return failed;
    }

    public LoginStatus getFailedReason() {
        return failedReason;
    }
}
