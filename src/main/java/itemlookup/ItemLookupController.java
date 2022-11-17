package itemlookup;

import entities.Product;

public class ItemLookupController {
    private final ItemLookupInteractor itemLookupInteractor;

    public ItemLookupController() {
        this.itemLookupInteractor = new ItemLookupInteractor();
    }

    public Product lookupByUPC(Long upc) {
        return this.itemLookupInteractor.lookupByUPC(upc);
    }

    public Product lookupByName(String name) {
        return this.itemLookupInteractor.lookupByName(name);
    }
}
