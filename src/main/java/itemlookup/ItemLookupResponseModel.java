package itemlookup;

import entities.User;
import userlogin.LoginStatus;

import java.util.List;

public class ItemLookupResponseModel {

    private final List<Object> infoList;

    public ItemLookupResponseModel(List<Object> infoList) {
        this.infoList = infoList;
    }

    public List<Object> getInfoList() {
        return infoList;
    }
}
