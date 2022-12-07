package itemlookup;

import adminmainmenu.AdminMainMenuViewModel;
import entities.*;
import storemainmenu.StoreMainMenuViewModel;
import warehousemainmenu.WarehouseMainMenuViewModel;

import java.util.ArrayList;
import java.util.List;

public class ItemLookupPresenter implements ItemLookupOutputBoundary {

    private final ItemLookupViewModel viewModel;
    private final StoreMainMenuViewModel storeMainMenuViewModel;
    private final WarehouseMainMenuViewModel warehouseMainMenuViewModel;
    private final AdminMainMenuViewModel adminMainMenuViewModel;

    public ItemLookupPresenter(ItemLookupViewModel viewModel, StoreMainMenuViewModel storeMainMenuViewModel, WarehouseMainMenuViewModel warehouseMainMenuViewModel, AdminMainMenuViewModel adminMainMenuViewModel) {
        this.viewModel = viewModel;
        this.storeMainMenuViewModel = storeMainMenuViewModel;
        this.warehouseMainMenuViewModel = warehouseMainMenuViewModel;
        this.adminMainMenuViewModel = adminMainMenuViewModel;
    }


    @Override
    public void prepareSuccessView(ItemLookupResponseModel model) {
        List<Object> infoList = model.getInfoList();
        List<String> stringList = new ArrayList<>();
        for (Object o : infoList) {
            stringList.add(o.toString());
        }
        viewModel.viewInfo(stringList);
        //TODO: Store user data somewhere
    }

    @Override
    public void prepareFailView(ItemLookupResponseModel model) {
        viewModel.failed(model.getFailReason());
    }

    @Override
    public void returnToMenu() {
        viewModel.setVisible(false);
        try{
            FacilityUser facilityUser = (FacilityUser) UserSession.getUserSession();
            FacilityType facilityType = facilityUser.getType();
            if (facilityType == FacilityType.STORE){
                storeMainMenuViewModel.setVisible(true);
            }else if (facilityType == FacilityType.WAREHOUSE){
                warehouseMainMenuViewModel.setVisible(true);
            }
        }catch (ClassCastException classCastException){
            adminMainMenuViewModel.setVisible(true);
        }


    }
}
