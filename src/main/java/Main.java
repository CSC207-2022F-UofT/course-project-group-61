import database.UserDbGateway;
import entities.FacilityType;
import entities.FacilityUser;
import entities.User;
import storemainmenu.StoreMainMenuController;
import storemainmenu.StoreMainMenuPresenter;
import storemainmenu.StoreMainMenuView;
import storemainmenu.StoreMainMenuViewModel;
import userlogin.*;

public class Main {

    public static void main(String[] args) {
        UserLoginViewModel viewModel = new UserLoginViewModel();
        StoreMainMenuViewModel storeModel = new StoreMainMenuViewModel();
        UserLoginView userLoginView = new UserLoginView(new UserLoginController(new UserLoginInteractor(new UserLoginPresenter(viewModel, storeModel), new UserDbGateway())));
        viewModel.addObserver(userLoginView);
        viewModel.setVisible(true);

        StoreMainMenuView storeMainMenuView = new StoreMainMenuView(new StoreMainMenuController(new StoreMainMenuPresenter(storeModel)));
        storeModel.addObserver(storeMainMenuView);

        UserDbGateway gateway = new UserDbGateway();
        gateway.fileReset();
        gateway.updateUser(new FacilityUser("Jacob", "Test", 123, FacilityType.STORE));

    }
}
