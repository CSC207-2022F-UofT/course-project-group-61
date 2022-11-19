package userlogin;

import entities.AdminUser;
import entities.FacilityType;
import entities.FacilityUser;
import entities.User;

public class UserLoginPresenter implements UserLoginOutputBoundary {

    private UserLoginViewModel viewModel;

    public UserLoginPresenter(UserLoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(UserLoginResponseModel model) {
        viewModel.setVisible(false);
        User user = model.getUser();
        if (user instanceof AdminUser) {
            //TODO: Set admin main menu view to visible
        } else if (((FacilityUser) user).getType() == FacilityType.STORE) {
            //TODO: Set store main menu view to visible
        } else if (((FacilityUser) user).getType() == FacilityType.WAREHOUSE) {
            //TODO: Set warehouse main menu view to visible
        }
        //TODO: Store user data somewhere
    }

    @Override
    public void prepareFailView(UserLoginResponseModel model) {
        viewModel.failed(model.getStatus());
    }
}
