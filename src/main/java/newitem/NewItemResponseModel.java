package newitem;

import entities.Product;

public class NewItemResponseModel {

    private final Product product;
    private final NewItemStatus status;

    public NewItemResponseModel(Product product, NewItemStatus status) {
        this.product = product;
        this.status = status;
    }

    public Product getProduct() {
        return this.product;
    }

    public NewItemStatus getStatus() {
        return this.status;
    }

}
