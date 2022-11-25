import adminmainmenu.AdminMainMenuController;
import adminmainmenu.AdminMainMenuPresenter;
import adminmainmenu.AdminMainMenuView;
import adminmainmenu.AdminMainMenuViewModel;
import database.FacilityDbGateway;
import database.OrderDbGateway;
import database.ProductDbGateway;
import database.UserDbGateway;
import entities.*;
import itemlookup.ItemLookupViewModel;
import newfacility.*;
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
import newuser.*;

import java.util.UUID;

public class Main {

    private User userSession;

    public static void main(String[] args) {
        UserLoginViewModel loginViewModel = new UserLoginViewModel();
        StoreMainMenuViewModel storeViewModel = new StoreMainMenuViewModel();
        WarehouseMainMenuViewModel warehouseViewModel = new WarehouseMainMenuViewModel();
        AdminMainMenuViewModel adminViewModel = new AdminMainMenuViewModel();
        OrderViewModel orderViewModel = new OrderViewModel();
        NewUserViewModel newUserViewModel = new NewUserViewModel();
        ItemLookupViewModel itemLookupViewModel = new ItemLookupViewModel();
        NewFacilityViewModel newFacilityViewModel = new NewFacilityViewModel();

        UserLoginView loginView = new UserLoginView(new UserLoginController(new UserLoginInteractor(new UserLoginPresenter(loginViewModel, storeViewModel, warehouseViewModel, adminViewModel), new UserDbGateway())));
        loginViewModel.addObserver(loginView);
        loginViewModel.setVisible(true);

        StoreMainMenuView storeMainMenuView = new StoreMainMenuView(new StoreMainMenuController(new StoreMainMenuPresenter(storeViewModel, orderViewModel, itemLookupViewModel)));
        storeViewModel.addObserver(storeMainMenuView);

        WarehouseMainMenuView warehouseMainMenuView = new WarehouseMainMenuView(new WarehouseMainMenuController(new WarehouseMainMenuPresenter(warehouseViewModel, itemLookupViewModel)));
        warehouseViewModel.addObserver(warehouseMainMenuView);

        AdminMainMenuView adminMainMenuView = new AdminMainMenuView(new AdminMainMenuController(new AdminMainMenuPresenter(adminViewModel, newUserViewModel, newFacilityViewModel)));
        adminViewModel.addObserver(adminMainMenuView);

        OrderView orderView = new OrderView(new OrderController(new OrderInteractor(new OrderPresenter(orderViewModel, storeViewModel), new OrderDbGateway(), new FacilityDbGateway(), new ProductDbGateway())), orderViewModel);
        orderViewModel.addObserver(orderView);

        NewUserView newUserView = new NewUserView(new NewUserController(new NewUserInteractor(new NewUserPresenter(newUserViewModel, adminViewModel), new UserDbGateway())));
        newUserViewModel.addObserver(newUserView);

        NewFacilityView newFacilityView = new NewFacilityView(new NewFacilityController(new NewFacilityInteractor(new NewFacilityPresenter(newFacilityViewModel), new FacilityDbGateway())));
        newFacilityViewModel.addObserver(newFacilityView);

        UserDbGateway userDb = new UserDbGateway();
        userDb.fileReset();
        userDb.updateUser(new FacilityUser("Jacob", "Password", UUID.randomUUID(), FacilityType.STORE));
        userDb.updateUser(new AdminUser("Admin", "Password"));
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
