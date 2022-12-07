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
import newitem.*;
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

import java.util.UUID;

public class Main {
//TODO: maybe instead of passing in all the views which is messy, make 1 object with all the views and getters / final public objects
    public static void main(String[] args) {
        FacilityDbGateway facilityDbGateway = new FacilityDbGateway();
        OrderDbGateway orderDbGateway = new OrderDbGateway();
        ProductDbGateway productDbGateway = new ProductDbGateway();
        UserDbGateway userDbGateway = new UserDbGateway();

        tester();

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
        NewItemViewModel newItemViewModel = new NewItemViewModel();

        UserLoginView loginView = new UserLoginView(new UserLoginController(new UserLoginInteractor(new UserLoginPresenter(loginViewModel, storeViewModel, warehouseViewModel, adminViewModel), userDbGateway)));
        loginViewModel.addObserver(loginView);
        loginViewModel.setVisible(true);

        StoreMainMenuView storeMainMenuView = new StoreMainMenuView(new StoreMainMenuController(new StoreMainMenuPresenter(storeViewModel, orderViewModel, dailySalesViewModel, inventoryCountViewModel, itemLookupViewModel, loginViewModel)));
        storeViewModel.addObserver(storeMainMenuView);

        WarehouseMainMenuView warehouseMainMenuView = new WarehouseMainMenuView(new WarehouseMainMenuController(new WarehouseMainMenuPresenter(warehouseViewModel, itemLookupViewModel, fulfillViewModel, inventoryCountViewModel, loginViewModel)));
        warehouseViewModel.addObserver(warehouseMainMenuView);

        AdminMainMenuView adminMainMenuView = new AdminMainMenuView(new AdminMainMenuController(new AdminMainMenuPresenter(adminViewModel, newUserViewModel, newFacilityViewModel, newItemViewModel, itemLookupViewModel, loginViewModel)));
        adminViewModel.addObserver(adminMainMenuView);

        OrderView orderView = new OrderView(new OrderController(new OrderInteractor(new OrderPresenter(orderViewModel, storeViewModel), orderDbGateway, facilityDbGateway, productDbGateway)));
        orderViewModel.addObserver(orderView);

        FulfillView fulfillView = new FulfillView(new FulfillController(new FulfillPresenter(fulfillViewModel, warehouseViewModel)));
        fulfillViewModel.addObserver(fulfillView);

        NewUserView newUserView = new NewUserView(new NewUserController(new NewUserInteractor(new NewUserPresenter(newUserViewModel, adminViewModel), userDbGateway, facilityDbGateway)));
        newUserViewModel.addObserver(newUserView);

        DailySalesView dailySalesView = new DailySalesView(new DailySalesController(new DailySalesInteractor(new DailySalesPresenter(dailySalesViewModel, storeViewModel), facilityDbGateway, productDbGateway)));
        dailySalesViewModel.addObserver(dailySalesView);

        NewFacilityView newFacilityView = new NewFacilityView(new NewFacilityController(new NewFacilityInteractor(new NewFacilityPresenter(newFacilityViewModel), new FacilityDbGateway())));
        newFacilityViewModel.addObserver(newFacilityView);

        InventoryCountView inventoryCountView = new InventoryCountView(new InventoryCountController(new InventoryCountInteractor(new InventoryCountPresenter(inventoryCountViewModel, storeViewModel), facilityDbGateway)));
        inventoryCountViewModel.addObserver(inventoryCountView);

        NewItemView newItemView = new NewItemView(new NewItemController(new NewItemInteractor(new NewItemPresenter(newItemViewModel), new ProductDbGateway())));
        newItemViewModel.addObserver(newItemView);

        ItemLookupView itemLookupView = new ItemLookupView(new ItemLookupController(new ItemLookupInteractor(new ItemLookupPresenter(itemLookupViewModel,storeViewModel), productDbGateway, facilityDbGateway)));
        itemLookupViewModel.addObserver(itemLookupView);
    }

    private static void tester(){
        FacilityDbGateway facilityDbGateway = new FacilityDbGateway();
        OrderDbGateway orderDbGateway = new OrderDbGateway();
        ProductDbGateway productDbGateway = new ProductDbGateway();
        UserDbGateway userDbGateway = new UserDbGateway();

        if(userDbGateway.getAllUsers().size() == 0){
            userDbGateway.fileReset();
            orderDbGateway.fileReset();
            productDbGateway.fileReset();
            facilityDbGateway.fileReset();

            userDbGateway.updateUser(new AdminUser("Admin", "Password"));
        }
    }
}
