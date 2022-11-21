package itemlookup;

import entities.User;
import org.junit.internal.runners.statements.Fail;
import userlogin.LoginStatus;

import java.util.List;

public class ItemLookupResponseModel {

    private final List<Object> infoList;

    private final FailReason failReason;

    public ItemLookupResponseModel(List<Object> infoList, FailReason failReason) {
        this.infoList = infoList;
        this.failReason = failReason;
    }

    public List<Object> getInfoList() {
        return infoList;
    }

    public FailReason getFailReason() {return failReason;}
}
