package newitem;

import database.ProductDbGateway;
import entities.Product;

public class NewItemInteractor {

    ProductDbGateway readWriter = new ProductDbGateway();

    public long addNewItem(String name, long UPC, int price) {
        Product newProduct = new Product(name, UPC, price);
        this.readWriter.updateProduct(newProduct);
        return newProduct.getUPC();
    }

}
