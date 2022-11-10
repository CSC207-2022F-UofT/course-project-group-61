package itemlookup;

import database.ProductDbGateway;
import entities.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ItemLookupInteractor {

    ProductDbGateway readWriter = new ProductDbGateway();
    public ItemLookupInteractor() {

    }

    //TODO: what to return if upc doesn't exist
    public Product lookupByUPC(Long upc) {
        HashMap<Long, Product> products = this.readWriter.getAllProducts();
        return products.get(upc);
    }

    //TODO: what to return if name doesn't exist
    public Product lookupByName(String name) {
        HashMap<Long, Product> products = this.readWriter.getAllProducts();

        for(Map.Entry<Long, Product> entry : products.entrySet()) {
            if (Objects.equals(name, entry.getValue().getName())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
