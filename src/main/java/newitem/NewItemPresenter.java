package newitem;

import adminmainmenu.AdminMainMenuViewModel;

public class NewItemPresenter implements NewItemOutputBoundary {


    private final NewItemViewModel viewModel;
    private final AdminMainMenuViewModel adminMainMenuViewModel;

    public NewItemPresenter(NewItemViewModel viewModel, AdminMainMenuViewModel adminMainMenuViewModel) {

        this.viewModel = viewModel;
        this.adminMainMenuViewModel = adminMainMenuViewModel;
    }

    @Override
    public void prepareSuccessView(NewItemResponseModel model) {
        viewModel.changeStatus(model.getStatus());
    }

    @Override
    public void prepareFailView(NewItemResponseModel model) {
        viewModel.changeStatus(model.getStatus());
    }

    @Override
    public void returnToMainMenu() {
        viewModel.setVisible(false);
        adminMainMenuViewModel.setVisible(true);
    }
}