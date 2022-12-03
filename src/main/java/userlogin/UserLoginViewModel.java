package userlogin;

import java.util.Observable;

public class UserLoginViewModel extends Observable {

    private boolean visible;
    private boolean failed;
    private LoginStatus failedReason;

    /* View model that keeps track of the state of the User Login View. */
    public UserLoginViewModel() {
        setVisible(false);
    }

    /* Failed method notifies the view to switch to the fail view. */
    public void failed(LoginStatus status) {
        failed = true;
        failedReason = status;
        setChanged();
        notifyObservers();
        failed = false;
        failedReason = null;
    }

    /* Getter for the visible boolean. */
    public boolean isVisible() {
        return visible;
    }

    /* Setter for the visible boolean. */
    public void setVisible(boolean visible) {
        this.visible = visible;
        setChanged();
        notifyObservers();
    }

    /* Getter for the failed boolean. */
    public boolean getFailed() {
        return failed;
    }

    /* Getter for the failed reason (LoginStatus). */
    public LoginStatus getFailedReason() {
        return failedReason;
    }
}
