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
import inventorycount.*;
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
        InventoryCountViewModel inventoryCountViewModel = new InventoryCountViewModel();
        ItemLookupViewModel itemLookupViewModel = new ItemLookupViewModel();
        NewFacilityViewModel newFacilityViewModel = new NewFacilityViewModel();

        UserLoginView loginView = new UserLoginView(new UserLoginController(new UserLoginInteractor(new UserLoginPresenter(loginViewModel, storeViewModel, warehouseViewModel, adminViewModel), userDbGateway)));
        loginViewModel.addObserver(loginView);
        loginViewModel.setVisible(true);

        StoreMainMenuView storeMainMenuView = new StoreMainMenuView(new StoreMainMenuController(new StoreMainMenuPresenter(storeViewModel, orderViewModel, dailySalesViewModel, inventoryCountViewModel, itemLookupViewModel)));
        storeViewModel.addObserver(storeMainMenuView);

        WarehouseMainMenuView warehouseMainMenuView = new WarehouseMainMenuView(new WarehouseMainMenuController(new WarehouseMainMenuPresenter(warehouseViewModel, itemLookupViewModel, fulfillViewModel)));
        warehouseViewModel.addObserver(warehouseMainMenuView);

        AdminMainMenuView adminMainMenuView = new AdminMainMenuView(new AdminMainMenuController(new AdminMainMenuPresenter(adminViewModel, newUserViewModel, newFacilityViewModel)));
        adminViewModel.addObserver(adminMainMenuView);

        OrderView orderView = new OrderView(new OrderController(new OrderInteractor(new OrderPresenter(orderViewModel, storeViewModel), orderDbGateway, facilityDbGateway, productDbGateway)));
        orderViewModel.addObserver(orderView);

        FulfillView fulfillView = new FulfillView(new FulfillController(new FulfillPresenter(fulfillViewModel, warehouseViewModel)));
        fulfillViewModel.addObserver(fulfillView);

        NewUserView newUserView = new NewUserView(new NewUserController(new NewUserInteractor(new NewUserPresenter(newUserViewModel, adminViewModel), userDbGateway)));
        newUserViewModel.addObserver(newUserView);

        DailySalesView dailySalesView = new DailySalesView(new DailySalesController(new DailySalesInteractor(new DailySalesPresenter(dailySalesViewModel, storeViewModel), facilityDbGateway, productDbGateway)));
        dailySalesViewModel.addObserver(dailySalesView);

        NewFacilityView newFacilityView = new NewFacilityView(new NewFacilityController(new NewFacilityInteractor(new NewFacilityPresenter(newFacilityViewModel), new FacilityDbGateway())));
        newFacilityViewModel.addObserver(newFacilityView);

        InventoryCountView inventoryCountView = new InventoryCountView(new InventoryCountController(new InventoryCountInteractor(new InventoryCountPresenter(inventoryCountViewModel, storeViewModel), facilityDbGateway)));
        inventoryCountViewModel.addObserver(inventoryCountView);

        ItemLookupView itemLookupView = new ItemLookupView(new ItemLookupController(new ItemLookupInteractor(new ItemLookupPresenter(itemLookupViewModel), productDbGateway, facilityDbGateway)));
        itemLookupViewModel.addObserver(itemLookupView);



        // ***** TEST CODE BELOW *****

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
