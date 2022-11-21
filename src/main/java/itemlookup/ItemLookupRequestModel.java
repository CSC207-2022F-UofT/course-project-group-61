package itemlookup;

import java.util.List;

public class ItemLookupRequestModel {

    private List<Object> infoList;

    public ItemLookupRequestModel(List<Object> infoList) {
        this.infoList = infoList;
    }

    public List<Object> getInfoList() {
        return infoList;
    }
}
