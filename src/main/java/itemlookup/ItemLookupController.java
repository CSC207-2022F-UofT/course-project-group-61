package itemlookup;

import entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ItemLookupController {
    //private final ItemLookupInteractor itemLookupInteractor;
    private final ItemLookupInputBoundary inputBoundary;

    public ItemLookupController(ItemLookupInputBoundary inputBoundary) {
        //this.itemLookupInteractor = new ItemLookupInteractor();
        this.inputBoundary = inputBoundary;
    }

    public ItemLookupResponseModel lookupByUPC(Long upc) {
        ItemLookupRequestModel request = new ItemLookupRequestModel(null, upc);
        return inputBoundary.lookupByUPC(request);



        /*if(inputBoundary.lookupByUPC(request) == null) {
            return new ItemLookupResponseModel(null, FailReason.INVALID_UPC);
        }

        List<Object> returnArray = inputBoundary.lookupByUPC(request).getInfoList();
        *//*returnArray.add(inputBoundary.lookupByUPC(request).getName());
        returnArray.add(inputBoundary.lookupByUPC(request).getUPC());
        returnArray.add(inputBoundary.lookupByUPC(request).getPrice());*//*

        return new ItemLookupResponseModel(returnArray, null);*/
    }

    public ItemLookupResponseModel lookupByName(String name) {
        ItemLookupRequestModel request = new ItemLookupRequestModel(name, null);
        return inputBoundary.lookupByName(request);




        /*if(inputBoundary.lookupByName(request) == null) {
            return new ItemLookupResponseModel(null, FailReason.INVALID_NAME);
        }

        List<Object> returnArray = inputBoundary.lookupByName(request).getInfoList();
        *//*returnArray.add(this.itemLookupInteractor.lookupByName(name).getName());
        returnArray.add(this.itemLookupInteractor.lookupByName(name).getUPC());
        returnArray.add(this.itemLookupInteractor.lookupByName(name).getPrice());*//*

        return new ItemLookupResponseModel(returnArray, null);*/
    }
}
