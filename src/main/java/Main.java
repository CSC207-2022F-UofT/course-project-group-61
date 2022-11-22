import adminmainmenu.AdminMainMenuController;
import adminmainmenu.AdminMainMenuPresenter;
import adminmainmenu.AdminMainMenuView;
import adminmainmenu.AdminMainMenuViewModel;
import database.FacilityDbGateway;
import database.OrderDbGateway;
import database.ProductDbGateway;
import database.UserDbGateway;
import entities.*;
import itemlookup.*;
import order.*;
import storemainmenu.StoreMainMenuController;
import storemainmenu.StoreMainMenuPresenter;
import storemainmenu.StoreMainMenuView;
import storemainmenu.StoreMainMenuViewModel;
import userlogin.*;
import warehousemainmenu.WarehouseMainMenuController;
import warehousemainmenu.WarehouseMainMenuPresenter;
import warehousemainmenu.WarehouseMainMenuView;
import warehousemainmenu.WarehouseMainMenuViewModel;

import java.util.UUID;

public class Main {

    private User userSession;

    public static void main(String[] args) {
        UserLoginViewModel loginViewModel = new UserLoginViewModel();
        StoreMainMenuViewModel storeViewModel = new StoreMainMenuViewModel();
        WarehouseMainMenuViewModel warehouseViewModel = new WarehouseMainMenuViewModel();
        AdminMainMenuViewModel adminViewModel = new AdminMainMenuViewModel();
        OrderViewModel orderViewModel = new OrderViewModel();
        ItemLookupViewModel itemLookupViewModel = new ItemLookupViewModel();

        UserLoginView loginView = new UserLoginView(new UserLoginController(new UserLoginInteractor(new UserLoginPresenter(loginViewModel, storeViewModel, warehouseViewModel, adminViewModel), new UserDbGateway())));
        loginViewModel.addObserver(loginView);
        loginViewModel.setVisible(true);

        StoreMainMenuView storeMainMenuView = new StoreMainMenuView(new StoreMainMenuController(new StoreMainMenuPresenter(storeViewModel, orderViewModel, itemLookupViewModel)));
        storeViewModel.addObserver(storeMainMenuView);

        WarehouseMainMenuView warehouseMainMenuView = new WarehouseMainMenuView(new WarehouseMainMenuController(new WarehouseMainMenuPresenter(warehouseViewModel, itemLookupViewModel)));
        warehouseViewModel.addObserver(warehouseMainMenuView);

        AdminMainMenuView adminMainMenuView = new AdminMainMenuView(new AdminMainMenuController(new AdminMainMenuPresenter(adminViewModel)));
        adminViewModel.addObserver(adminMainMenuView);

        OrderView orderView = new OrderView(new OrderController(new OrderInteractor(new OrderPresenter(orderViewModel, storeViewModel), new OrderDbGateway(), new FacilityDbGateway(), new ProductDbGateway())), orderViewModel);
        orderViewModel.addObserver(orderView);

        ItemLookupView view = new ItemLookupView(new ItemLookupController(new ItemLookupInteractor(new ItemLookupPresenter(itemLookupViewModel), new ProductDbGateway(), new FacilityDbGateway())));
        itemLookupViewModel.addObserver(view);

        UserDbGateway userDb = new UserDbGateway();
        userDb.fileReset();
        userDb.updateUser(new FacilityUser("Jacob", "Test", UUID.randomUUID(), FacilityType.STORE));

        ProductDbGateway productDb = new ProductDbGateway();
        productDb.fileReset();
        productDb.updateProduct(new Product("Strawberries", 4001L, 2));

        FacilityDbGateway facilityDb = new FacilityDbGateway();
        facilityDb.fileReset();
        Facility testFacility = new Facility("TestFacility", FacilityType.WAREHOUSE);
        testFacility.addProduct(4001L, 100);
        facilityDb.updateFacility(testFacility);

        OrderDbGateway orderDb = new OrderDbGateway();
        orderDb.fileReset();
    }
}
