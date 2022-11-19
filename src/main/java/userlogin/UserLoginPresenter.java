package userlogin;

import entities.AdminUser;
import entities.FacilityType;
import entities.FacilityUser;
import entities.User;
import storemainmenu.StoreMainMenuViewModel;

public class UserLoginPresenter implements UserLoginOutputBoundary {

    private UserLoginViewModel viewModel;
    private StoreMainMenuViewModel storeModel;

    public UserLoginPresenter(UserLoginViewModel viewModel, StoreMainMenuViewModel storeModel) {
        this.viewModel = viewModel;
        this.storeModel = storeModel;
    }

    @Override
    public void prepareSuccessView(UserLoginResponseModel model) {
        viewModel.setVisible(false);
        User user = model.getUser();
        if (user instanceof AdminUser) {
            //TODO: Set admin main menu view to visible
        } else if (((FacilityUser) user).getType() == FacilityType.STORE) {
            storeModel.setVisible(true);
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
