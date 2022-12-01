package itemlookup;

import java.util.List;
import java.util.Observable;

public class ItemLookupViewModel extends Observable {

    private boolean visible;
    private boolean failed;
    private FailReason failedReason;
    private List<String> infoList;

    public ItemLookupViewModel() {
    }

    public void viewInfo(List<String> infoList) {
        this.infoList = infoList;
        setChanged();
        notifyObservers();
    }

    public void failed(FailReason status) {
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
    public List<String> getInfoList() {return infoList;}

    public boolean getFailed() {
        return failed;
    }

    public FailReason getFailedReason() {
        return failedReason;
    }
}
