package newitem;

import database.ProductDb;
import entities.Product;

import java.util.HashMap;

public class NewItemInteractor implements NewItemInputBoundary {

    private final ProductDb productDb;
    private final NewItemOutputBoundary presenter;

    public NewItemInteractor(NewItemOutputBoundary presenter, ProductDb productDb) {
        this.presenter = presenter;
        this.productDb = productDb;
    }


    @Override
    public NewItemResponseModel newItem(NewItemRequestModel request) {
        Product newProduct = new Product(request.getName(), request.getUPC(), request.getPrice());
        HashMap<Long, Product> products = productDb.getAllProducts();
        NewItemResponseModel response = null;
        boolean isInvalid = false;

        for (Product product : products.values()) {
            if (product.getUPC() == newProduct.getUPC()) {
                response = new NewItemResponseModel(newProduct, NewItemStatus.REPEAT_UPC);
                presenter.prepareFailView(response);
                isInvalid = true;
                break;
            }
        }

        if (!isInvalid) {
            productDb.updateProduct(newProduct);
            response = new NewItemResponseModel(newProduct, NewItemStatus.SUCCESS);
            presenter.prepareSuccessView(response);
        }

        return response;

    }
}
