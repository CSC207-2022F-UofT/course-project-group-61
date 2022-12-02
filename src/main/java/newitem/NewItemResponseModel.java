package newitem;

import entities.Product;

public record NewItemResponseModel(Product product, NewItemStatus status) {

}
