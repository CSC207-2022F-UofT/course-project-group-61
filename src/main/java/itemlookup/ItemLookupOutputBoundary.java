package itemlookup;

public interface ItemLookupOutputBoundary {

    void prepareSuccessView(ItemLookupResponseModel model);

    void prepareFailView(ItemLookupResponseModel model);
}
