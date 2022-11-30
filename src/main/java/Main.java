import adminmainmenu.AdminMainMenuController;
import adminmainmenu.AdminMainMenuPresenter;
import adminmainmenu.AdminMainMenuView;
import adminmainmenu.AdminMainMenuViewModel;
import dailysales.*;
import database.FacilityDbGateway;
import database.OrderDbGateway;
import database.ProductDbGateway;
import database.UserDbGateway;
import entities.*;
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

public class Main {

    public static void main(String[] args) {
        UserLoginViewModel loginViewModel = new UserLoginViewModel();
        StoreMainMenuViewModel storeViewModel = new StoreMainMenuViewModel();
        WarehouseMainMenuViewModel warehouseViewModel = new WarehouseMainMenuViewModel();
        AdminMainMenuViewModel adminViewModel = new AdminMainMenuViewModel();
        OrderViewModel orderViewModel = new OrderViewModel();
        NewUserViewModel newUserViewModel = new NewUserViewModel();
        DailySalesViewModel dailySalesViewModel = new DailySalesViewModel();

        FacilityDbGateway facilityDbGateway = new FacilityDbGateway();
        ProductDbGateway productDbGateway = new ProductDbGateway();
        UserDbGateway userDbGateway = new UserDbGateway();
        OrderDbGateway orderDbGateway = new OrderDbGateway();

        UserLoginView loginView = new UserLoginView(new UserLoginController(new UserLoginInteractor(new UserLoginPresenter(loginViewModel, storeViewModel, warehouseViewModel, adminViewModel), userDbGateway)));
        loginViewModel.addObserver(loginView);
        loginViewModel.setVisible(true);

        StoreMainMenuView storeMainMenuView = new StoreMainMenuView(new StoreMainMenuController(new StoreMainMenuPresenter(storeViewModel, orderViewModel, dailySalesViewModel)));
        storeViewModel.addObserver(storeMainMenuView);

        WarehouseMainMenuView warehouseMainMenuView = new WarehouseMainMenuView(new WarehouseMainMenuController(new WarehouseMainMenuPresenter(warehouseViewModel)));
        warehouseViewModel.addObserver(warehouseMainMenuView);

        AdminMainMenuView adminMainMenuView = new AdminMainMenuView(new AdminMainMenuController(new AdminMainMenuPresenter(adminViewModel, newUserViewModel)));
        adminViewModel.addObserver(adminMainMenuView);

        OrderView orderView = new OrderView(new OrderController(new OrderInteractor(new OrderPresenter(orderViewModel, storeViewModel), orderDbGateway, facilityDbGateway, productDbGateway)), orderViewModel);
        orderViewModel.addObserver(orderView);

        NewUserView newUserView = new NewUserView(new NewUserController(new NewUserInteractor(new NewUserPresenter(newUserViewModel, adminViewModel), userDbGateway)));
        newUserViewModel.addObserver(newUserView);

        DailySalesView dailySalesView = new DailySalesView(new DailySalesController(new DailySalesInteractor(new DailySalesPresenter(dailySalesViewModel, storeViewModel), facilityDbGateway, productDbGateway)));
        dailySalesViewModel.addObserver(dailySalesView);

        Facility testStore = new Facility("TestStore", FacilityType.STORE);
        testStore.addProduct(4001L, 150);
        Facility testWarehouse = new Facility("TestFacility", FacilityType.WAREHOUSE);
        testWarehouse.addProduct(4001L, 100);

        userDbGateway.fileReset();
        userDbGateway.updateUser(new FacilityUser("Store", "Password", testStore.getFacilityID(), FacilityType.STORE));
        userDbGateway.updateUser(new FacilityUser("Warehouse", "Password", testWarehouse.getFacilityID(), FacilityType.WAREHOUSE));
        userDbGateway.updateUser(new AdminUser("Admin", "Password"));
        productDbGateway.fileReset();
        productDbGateway.updateProduct(new Product("Strawberries", 4001L, 2));
        // null pointer exception occurs on this line
        facilityDbGateway.fileReset();
        facilityDbGateway.updateFacility(testStore);
        facilityDbGateway.updateFacility(testWarehouse);

        orderDbGateway.fileReset();
    }
}
