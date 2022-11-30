package itemlookup;

public class ItemLookupRequestModel {

    private final Long UPC;
    private final String name;

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
