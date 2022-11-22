package newuser;

import java.util.Observable;

public class NewUserViewModel extends Observable {

    private boolean visible;
    private boolean failed;
    private NewUserStatus failReason;

    public NewUserViewModel(){
    }

    public void failed(NewUserStatus status){
        failed = true;
        failReason = status;
        setChanged();
        notifyObservers();
        failed = false;
        failReason = null;
    }

    public boolean isVisible(){
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        setChanged();
        notifyObservers();
    }

    public boolean getFailed(){
        return failed;
    }

    public NewUserStatus getFailReason() {
        return failReason;
    }
}
