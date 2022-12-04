package itemlookup;

import database.FacilityDbGateway;
import database.ProductDbGateway;
import entities.Facility;
import entities.FacilityType;
import entities.Product;
import storemainmenu.StoreMainMenuViewModel;


public class test {
    public static void main(String[] args) {
        ProductDbGateway productDbGateway = new ProductDbGateway();
        FacilityDbGateway facilityDbGateway = new FacilityDbGateway();
        productDbGateway.fileReset();
        productDbGateway.updateProduct(new Product("Apple", 4001L, 5));
        productDbGateway.updateProduct(new Product("Orange", 4002L, 4));
        productDbGateway.updateProduct(new Product("Banana", 4003L, 3));

        facilityDbGateway.fileReset();
        Facility facility1 = new Facility("Facility1", FacilityType.WAREHOUSE);
        facility1.addProduct(4001L, 50);
        facility1.addProduct(4002L, 40);
        facility1.addProduct(4003L, 30);
        facilityDbGateway.updateFacility(facility1);


        ItemLookupViewModel viewModel = new ItemLookupViewModel();
        StoreMainMenuViewModel storeViewModel = new StoreMainMenuViewModel();
        ItemLookupView view = new ItemLookupView(new ItemLookupController(new ItemLookupInteractor(new ItemLookupPresenter(viewModel,storeViewModel), productDbGateway, facilityDbGateway)));
        viewModel.addObserver(view);
        viewModel.setVisible(true);
    }
}
