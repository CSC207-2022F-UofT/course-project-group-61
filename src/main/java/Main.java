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
import fulfill.FulfillController;
import fulfill.FulfillPresenter;
import fulfill.FulfillView;
import fulfill.FulfillViewModel;
import itemlookup.*;
import newfacility.*;
import newuser.*;
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

public class Main {

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
        DailySalesViewModel dailySalesViewModel = new DailySalesViewModel();
        FulfillViewModel fulfillViewModel = new FulfillViewModel();
        ItemLookupViewModel itemLookupViewModel = new ItemLookupViewModel();
        NewFacilityViewModel newFacilityViewModel = new NewFacilityViewModel();

        UserLoginView loginView = new UserLoginView(new UserLoginController(new UserLoginInteractor(new UserLoginPresenter(loginViewModel, storeViewModel, warehouseViewModel, adminViewModel), userDbGateway)));
        loginViewModel.addObserver(loginView);
        loginViewModel.setVisible(true);

        StoreMainMenuView storeMainMenuView = new StoreMainMenuView(new StoreMainMenuController(new StoreMainMenuPresenter(storeViewModel, orderViewModel, dailySalesViewModel, itemLookupViewModel)));
        storeViewModel.addObserver(storeMainMenuView);

        WarehouseMainMenuView warehouseMainMenuView = new WarehouseMainMenuView(new WarehouseMainMenuController(new WarehouseMainMenuPresenter(warehouseViewModel, itemLookupViewModel, fulfillViewModel)));
        warehouseViewModel.addObserver(warehouseMainMenuView);

        AdminMainMenuView adminMainMenuView = new AdminMainMenuView(new AdminMainMenuController(new AdminMainMenuPresenter(adminViewModel, newUserViewModel, newFacilityViewModel)));
        adminViewModel.addObserver(adminMainMenuView);

        //OrderView orderView = new OrderView(new OrderController(new OrderInteractor(new OrderPresenter(orderViewModel, storeViewModel), orderDbGateway, facilityDbGateway, productDbGateway)), orderViewModel);
        //orderViewModel.addObserver(orderView);

        FulfillView fulfillView = new FulfillView(new FulfillController(new FulfillPresenter(fulfillViewModel, warehouseViewModel)));
        fulfillViewModel.addObserver(fulfillView);

        NewUserView newUserView = new NewUserView(new NewUserController(new NewUserInteractor(new NewUserPresenter(newUserViewModel, adminViewModel), userDbGateway, facilityDbGateway)));
        newUserViewModel.addObserver(newUserView);

        DailySalesView dailySalesView = new DailySalesView(new DailySalesController(new DailySalesInteractor(new DailySalesPresenter(dailySalesViewModel, storeViewModel), facilityDbGateway, productDbGateway)));
        dailySalesViewModel.addObserver(dailySalesView);

        NewFacilityView newFacilityView = new NewFacilityView(new NewFacilityController(new NewFacilityInteractor(new NewFacilityPresenter(newFacilityViewModel), new FacilityDbGateway())));
        newFacilityViewModel.addObserver(newFacilityView);

        ItemLookupView itemLookupView = new ItemLookupView(new ItemLookupController(new ItemLookupInteractor(new ItemLookupPresenter(itemLookupViewModel), productDbGateway, facilityDbGateway)));
        itemLookupViewModel.addObserver(itemLookupView);

        // ***** TEST CODE BELOW *****/

        Facility testStore = new Facility("TestStore", FacilityType.STORE);
        Facility testStore2 = new Facility("TestStore2", FacilityType.STORE);
        Facility testStore3 = new Facility("TestStore3", FacilityType.STORE);
        testStore.addProduct(4001L, 150);
        testStore2.addProduct(4001L, 100);
        testStore3.addProduct(4001L, 200);
        Facility testWarehouse = new Facility("TestWarehouse", FacilityType.WAREHOUSE);
        Facility testWarehouse2 = new Facility("TestWarehouse2", FacilityType.WAREHOUSE);
        Facility testWarehouse3 = new Facility("TestWarehouse3", FacilityType.WAREHOUSE);
        testWarehouse.addProduct(4001L, 100);
        testWarehouse2.addProduct(4001L, 50);
        testWarehouse3.addProduct(4001L, 300);

        userDbGateway.fileReset();
        userDbGateway.updateUser(new FacilityUser("Store", "Password", testStore.getFacilityID(), FacilityType.STORE));
        userDbGateway.updateUser(new FacilityUser("Warehouse", "Password", testWarehouse.getFacilityID(), FacilityType.WAREHOUSE));
        userDbGateway.updateUser(new AdminUser("Admin", "Password"));
        productDbGateway.fileReset();
        productDbGateway.updateProduct(new Product("Strawberries", 4001L, 2));
        // null pointer exception occurs on this line
        facilityDbGateway.fileReset();
        facilityDbGateway.updateFacility(testStore);
        facilityDbGateway.updateFacility(testStore2);
        facilityDbGateway.updateFacility(testStore3);
        facilityDbGateway.updateFacility(testWarehouse);
        facilityDbGateway.updateFacility(testWarehouse2);
        facilityDbGateway.updateFacility(testWarehouse3);

        orderDbGateway.fileReset();
    }
}
