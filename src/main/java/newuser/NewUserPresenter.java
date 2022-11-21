package newuser;

import adminmainmenu.AdminMainMenuViewModel;
import entities.User;
import database.UserDb;

public class NewUserPresenter implements NewUserOutputBoundary{

    private NewUserViewModel viewModel;
    private final AdminMainMenuViewModel adminMainMenuViewModel;

    public NewUserPresenter(NewUserViewModel viewModel, AdminMainMenuViewModel adminMainMenuViewModel){
        this.viewModel = viewModel;
        this.adminMainMenuViewModel = adminMainMenuViewModel;
    }

    @Override
    public void prepareSuccessView(NewUserResponseModel model){
        viewModel.setVisible(false);
        adminMainMenuViewModel.setVisible(true);
    }

    @Override
    public void prepareFailView(NewUserResponseModel model){
        viewModel.failed(model.getStatus());
    }
}

