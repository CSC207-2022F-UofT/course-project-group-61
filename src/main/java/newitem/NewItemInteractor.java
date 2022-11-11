package newitem;

import database.ProductDbGateway;
import entities.Product;

public class NewItemInteractor {

    ProductDbGateway readWriter = new ProductDbGateway();
    public NewItemInteractor() {

    }

    public boolean addNewItem(String name, Long upc, int price) {
        Product newProduct = new Product(name, upc, price);
        this.readWriter.updateProduct(newProduct);
        return true;
    }
}
