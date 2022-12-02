package userlogin;

import adminmainmenu.AdminMainMenuViewModel;
import entities.AdminUser;
import entities.FacilityType;
import entities.FacilityUser;
import entities.User;
import storemainmenu.StoreMainMenuViewModel;
import warehousemainmenu.WarehouseMainMenuViewModel;

public class UserLoginPresenter implements UserLoginOutputBoundary {

    private final UserLoginViewModel viewModel;
    private final StoreMainMenuViewModel storeModel;
    private final WarehouseMainMenuViewModel warehouseModel;
    private final AdminMainMenuViewModel adminModel;

    public UserLoginPresenter(UserLoginViewModel viewModel,
                              StoreMainMenuViewModel storeModel,
                              WarehouseMainMenuViewModel warehouseModel,
                              AdminMainMenuViewModel adminModel) {
        this.viewModel = viewModel;
        this.storeModel = storeModel;
        this.warehouseModel = warehouseModel;
        this.adminModel = adminModel;
    }

    @Override
    public void prepareSuccessView(UserLoginResponseModel model) {
        viewModel.setVisible(false);
        User user = model.getUser();
        if (user instanceof AdminUser) {
            adminModel.setVisible(true);
        } else if (((FacilityUser) user).getType() == FacilityType.STORE) {
            //System.out.println("store");
            storeModel.setVisible(true);
        } else if (((FacilityUser) user).getType() == FacilityType.WAREHOUSE) {
            warehouseModel.setVisible(true);
        }
    }

    @Override
    public void prepareFailView(UserLoginResponseModel model) {
        viewModel.failed(model.getStatus());
    }
}
