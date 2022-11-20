package userlogin;

import adminmainmenu.AdminMainMenuViewModel;
import entities.AdminUser;
import entities.FacilityType;
import entities.FacilityUser;
import entities.User;
import storemainmenu.StoreMainMenuViewModel;
import warehousemainmenu.WarehouseMainMenuViewModel;

public class UserLoginPresenter implements UserLoginOutputBoundary {

    private UserLoginViewModel viewModel;
    private final StoreMainMenuViewModel storeMainMenuViewModel;
    private final WarehouseMainMenuViewModel warehouseMainMenuViewModel;
    private final AdminMainMenuViewModel adminMainMenuViewModel;

    public UserLoginPresenter(UserLoginViewModel viewModel,
                              StoreMainMenuViewModel storeMainMenuViewModel,
                              WarehouseMainMenuViewModel warehouseMainMenuViewModel,
                              AdminMainMenuViewModel adminMainMenuViewModel) {
        this.viewModel = viewModel;
        this.storeMainMenuViewModel = storeMainMenuViewModel;
        this.warehouseMainMenuViewModel = warehouseMainMenuViewModel;
        this.adminMainMenuViewModel = adminMainMenuViewModel;
    }

    @Override
    public void prepareSuccessView(UserLoginResponseModel model) {
        viewModel.setVisible(false);
        User user = model.getUser();
        if (user instanceof AdminUser) {
            adminMainMenuViewModel.setVisible(true);
        } else if (((FacilityUser) user).getType() == FacilityType.STORE) {
            storeMainMenuViewModel.setVisible(true);
        } else if (((FacilityUser) user).getType() == FacilityType.WAREHOUSE) {
            warehouseMainMenuViewModel.setVisible(true);
        }
        //TODO: Store user data somewhere
    }

    @Override
    public void prepareFailView(UserLoginResponseModel model) {
        viewModel.failed(model.getStatus());
    }
}
