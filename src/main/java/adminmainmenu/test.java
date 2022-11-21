package adminmainmenu;

import newuser.*;
import database.*;
import entities.*;
import java.util.UUID;

public class test {
    public static void main(String[] args) {

        AdminMainMenuViewModel viewModel = new AdminMainMenuViewModel();
        NewUserViewModel newUserViewModel = new NewUserViewModel();

        UserDbGateway userDb = new UserDbGateway();
        userDb.fileReset();
        userDb.updateUser(new FacilityUser("Kevin", "Test", UUID.randomUUID(), FacilityType.STORE));

        AdminMainMenuView view = new AdminMainMenuView(new AdminMainMenuController(new AdminMainMenuPresenter(viewModel, newUserViewModel)));
        viewModel.addObserver(view);
        viewModel.setVisible(true);

        NewUserView newUserView = new NewUserView(new NewUserController(new NewUserInteractor(new NewUserPresenter(newUserViewModel, viewModel), new UserDbGateway())));
        newUserViewModel.addObserver(newUserView);
    }
}
