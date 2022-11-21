import adminmainmenu.AdminMainMenuViewModel;
import database.UserDbGateway;
import entities.FacilityType;
import entities.FacilityUser;
import storemainmenu.StoreMainMenuViewModel;
import userlogin.*;
import warehousemainmenu.WarehouseMainMenuViewModel;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        UserLoginViewModel loginViewModel = new UserLoginViewModel();
        StoreMainMenuViewModel storeViewModel = new StoreMainMenuViewModel();
        WarehouseMainMenuViewModel warehouseViewModel = new WarehouseMainMenuViewModel();
        AdminMainMenuViewModel adminViewModel = new AdminMainMenuViewModel();

        UserLoginView loginView = new UserLoginView(new UserLoginController(new UserLoginInteractor(new UserLoginPresenter(loginViewModel, storeViewModel, warehouseViewModel, adminViewModel), new UserDbGateway())));
        loginViewModel.addObserver(loginView);
        loginViewModel.setVisible(true);

        UserDbGateway gateway = new UserDbGateway();
        gateway.fileReset();
        gateway.updateUser(new FacilityUser("Jacob", "Test", UUID.randomUUID(), FacilityType.STORE));

    }
}
