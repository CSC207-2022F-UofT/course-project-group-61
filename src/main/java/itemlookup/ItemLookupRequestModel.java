package itemlookup;

import java.util.List;

public class ItemLookupRequestModel {

    //private List<Object> infoList;
    private Long UPC;
    private String name;

    public ItemLookupRequestModel(String name, Long UPC) {
        //this.infoList = infoList;
        this.UPC = UPC;
        this.name = name;
    }

    /*public List<Object> getInfoList() {
        return infoList;
    }*/

    public Long getUPC() {
        return UPC;
    }

    public String getName() {
        return name;
    }
}
