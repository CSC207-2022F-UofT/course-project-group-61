package itemlookup;

public class ItemLookupController {
    private final ItemLookupInputBoundary inputBoundary;

    public ItemLookupController(ItemLookupInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public ItemLookupResponseModel lookupByUPC(Long upc) {
        ItemLookupRequestModel request = new ItemLookupRequestModel(null, upc);
        return inputBoundary.lookupByUPC(request);
    }

    public ItemLookupResponseModel lookupByName(String name) {
        ItemLookupRequestModel request = new ItemLookupRequestModel(name, null);
        return inputBoundary.lookupByName(request);
    }

    public void returnToMenu() {
        inputBoundary.returnToMenu();
    }
}
