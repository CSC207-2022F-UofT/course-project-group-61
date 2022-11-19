import database.UserDbGateway;
import entities.FacilityType;
import entities.FacilityUser;
import entities.User;
import userlogin.*;

public class Main {

    public static void main(String[] args) {
        UserLoginViewModel viewModel = new UserLoginViewModel();
        UserLoginView view = new UserLoginView(new UserLoginController(new UserLoginInteractor(new UserLoginPresenter(viewModel), new UserDbGateway())));
        viewModel.addObserver(view);
        viewModel.setVisible(true);

        UserDbGateway gateway = new UserDbGateway();
        gateway.fileReset();
        gateway.updateUser(new FacilityUser("Jacob", "Test", 123, FacilityType.STORE));

    }
}
