package itemlookup;

import database.ProductDbGateway;
import entities.Product;


public class test {
    public static void main(String[] args) {
        ProductDbGateway gateway = new ProductDbGateway();
        gateway.fileReset();
        gateway.updateProduct(new Product("Apple", 4001L, 5));
        gateway.updateProduct(new Product("Orange", 4002L, 4));
        gateway.updateProduct(new Product("Banana", 4003L, 3));

        ItemLookupViewModel viewModel = new ItemLookupViewModel();
        ItemLookupView view = new ItemLookupView(new ItemLookupController(new ItemLookupInteractor(new ItemLookupPresenter(viewModel), new ProductDbGateway())));
        viewModel.addObserver(view);
        viewModel.setVisible(true);
    }
}
