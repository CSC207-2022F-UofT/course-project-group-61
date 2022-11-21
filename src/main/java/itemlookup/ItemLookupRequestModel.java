package itemlookup;

import java.util.List;

public class ItemLookupRequestModel {

    private Long UPC;
    private String name;

    public ItemLookupRequestModel(String name, Long UPC) {
        this.UPC = UPC;
        this.name = name;
    }

    public Long getUPC() {
        return UPC;
    }

    public String getName() {
        return name;
    }
}
