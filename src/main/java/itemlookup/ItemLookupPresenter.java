package itemlookup;

import adminmainmenu.AdminMainMenuViewModel;
import entities.AdminUser;
import entities.FacilityType;
import entities.FacilityUser;
import entities.User;
import storemainmenu.StoreMainMenuViewModel;
import warehousemainmenu.WarehouseMainMenuViewModel;

import java.util.List;

public class ItemLookupPresenter implements ItemLookupOutputBoundary {

    private ItemLookupViewModel viewModel;

    public ItemLookupPresenter(ItemLookupViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(ItemLookupResponseModel model) {
        //viewModel.setVisible(false);
        List<Object> infoList = model.getInfoList();
        viewModel.viewInfo(infoList);

        /*if (user instanceof AdminUser) {
            adminMainMenuViewModel.setVisible(true);
        } else if (((FacilityUser) user).getType() == FacilityType.STORE) {
            System.out.println("store");
            storeMainMenuViewModel.setVisible(true);
        } else if (((FacilityUser) user).getType() == FacilityType.WAREHOUSE) {
            warehouseMainMenuViewModel.setVisible(true);
        }*/
        //TODO: Store user data somewhere
    }

    @Override
    public void prepareFailView(ItemLookupResponseModel model) {
        viewModel.failed(model.getFailReason());
    }
}
