import adminmainmenu.AdminMainMenuController;
import adminmainmenu.AdminMainMenuPresenter;
import adminmainmenu.AdminMainMenuView;
import adminmainmenu.AdminMainMenuViewModel;
import database.*;
import entities.*;
import itemlookup.*;
import newfacility.*;
import newuser.*;
import fulfill.FulfillController;
import fulfill.FulfillPresenter;
import fulfill.FulfillView;
import fulfill.FulfillViewModel;
import newuser.NewUserViewModel;
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
        FacilityDbGateway facilityDbGateway = new FacilityDbGateway();
        OrderDbGateway orderDbGateway = new OrderDbGateway();
        ProductDbGateway productDbGateway = new ProductDbGateway();
        UserDbGateway userDbGateway = new UserDbGateway();

        UserLoginViewModel loginViewModel = new UserLoginViewModel();
        StoreMainMenuViewModel storeViewModel = new StoreMainMenuViewModel();
        WarehouseMainMenuViewModel warehouseViewModel = new WarehouseMainMenuViewModel();
        AdminMainMenuViewModel adminViewModel = new AdminMainMenuViewModel();
        OrderViewModel orderViewModel = new OrderViewModel();
        NewUserViewModel newUserViewModel = new NewUserViewModel();
        ItemLookupViewModel itemLookupViewModel = new ItemLookupViewModel();
        NewFacilityViewModel newFacilityViewModel = new NewFacilityViewModel();
        FulfillViewModel fulfillViewModel = new FulfillViewModel();

        UserLoginView loginView = new UserLoginView(new UserLoginController(new UserLoginInteractor(new UserLoginPresenter(loginViewModel, storeViewModel, warehouseViewModel, adminViewModel), userDbGateway)));
        loginViewModel.addObserver(loginView);
        loginViewModel.setVisible(true);

        StoreMainMenuView storeMainMenuView = new StoreMainMenuView(new StoreMainMenuController(new StoreMainMenuPresenter(storeViewModel, orderViewModel, itemLookupViewModel)));
        storeViewModel.addObserver(storeMainMenuView);

        WarehouseMainMenuView warehouseMainMenuView = new WarehouseMainMenuView(new WarehouseMainMenuController(new WarehouseMainMenuPresenter(warehouseViewModel, itemLookupViewModel)));
        warehouseViewModel.addObserver(warehouseMainMenuView);

        AdminMainMenuView adminMainMenuView = new AdminMainMenuView(new AdminMainMenuController(new AdminMainMenuPresenter(adminViewModel, newUserViewModel, newFacilityViewModel)));
        adminViewModel.addObserver(adminMainMenuView);

        FulfillView fulfillView = new FulfillView(new FulfillController(new FulfillPresenter(fulfillViewModel, warehouseViewModel)));
        fulfillViewModel.addObserver(fulfillView);

        OrderView orderView = new OrderView(new OrderController(new OrderInteractor(new OrderPresenter(orderViewModel, storeViewModel), new OrderDbGateway(), new FacilityDbGateway(), new ProductDbGateway())), orderViewModel);
        orderViewModel.addObserver(orderView);

        NewUserView newUserView = new NewUserView(new NewUserController(new NewUserInteractor(new NewUserPresenter(newUserViewModel, adminViewModel), new UserDbGateway())));
        newUserViewModel.addObserver(newUserView);

        NewFacilityView newFacilityView = new NewFacilityView(new NewFacilityController(new NewFacilityInteractor(new NewFacilityPresenter(newFacilityViewModel), new FacilityDbGateway())));
        newFacilityViewModel.addObserver(newFacilityView);

        ItemLookupView itemLookupView = new ItemLookupView(new ItemLookupController(new ItemLookupInteractor(new ItemLookupPresenter(itemLookupViewModel), productDbGateway, facilityDbGateway)));
        itemLookupViewModel.addObserver(itemLookupView);

        userDbGateway.fileReset();
        userDbGateway.updateUser(new FacilityUser("Jacob", "Password", UUID.randomUUID(), FacilityType.STORE));
        userDbGateway.updateUser(new AdminUser("Admin", "Password"));
        ProductDbGateway productDb = new ProductDbGateway();
        productDb.fileReset();
        productDb.updateProduct(new Product("Strawberries", 4001L, 2));

        FacilityDbGateway facilityDb = new FacilityDbGateway();
        Facility testFacility = new Facility("TestFacility", FacilityType.WAREHOUSE);
        testFacility.addProduct(4001L, 100);
        // null pointer exception occurs on this line
        facilityDb.updateFacility(testFacility);

        OrderDbGateway orderDb = new OrderDbGateway();
        orderDb.fileReset();
    }
}
