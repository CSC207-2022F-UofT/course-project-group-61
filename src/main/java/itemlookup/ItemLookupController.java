package itemlookup;

import entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ItemLookupController {
    private final ItemLookupInteractor itemLookupInteractor;

    public ItemLookupController() {
        this.itemLookupInteractor = new ItemLookupInteractor();
    }

    public List<Object> lookupByUPC(Long upc) {
        if(this.itemLookupInteractor.lookupByUPC(upc) == null) {
            return null;
        }

        List<Object> returnArray = new ArrayList<Object>();
        returnArray.add(this.itemLookupInteractor.lookupByUPC(upc).getName());
        returnArray.add(this.itemLookupInteractor.lookupByUPC(upc).getUPC());
        returnArray.add(this.itemLookupInteractor.lookupByUPC(upc).getPrice());

        return returnArray;
    }

    public List<Object> lookupByName(String name) {
        if(this.itemLookupInteractor.lookupByName(name) == null) {
            return null;
        }

        List<Object> returnArray = new ArrayList<Object>();
        returnArray.add(this.itemLookupInteractor.lookupByName(name).getName());
        returnArray.add(this.itemLookupInteractor.lookupByName(name).getUPC());
        returnArray.add(this.itemLookupInteractor.lookupByName(name).getPrice());

        return returnArray;
    }
}
