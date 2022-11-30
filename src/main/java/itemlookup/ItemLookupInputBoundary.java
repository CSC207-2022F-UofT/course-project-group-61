package itemlookup;

public interface ItemLookupInputBoundary {

    ItemLookupResponseModel lookupByUPC(ItemLookupRequestModel request);

    ItemLookupResponseModel lookupByName(ItemLookupRequestModel request);
}
