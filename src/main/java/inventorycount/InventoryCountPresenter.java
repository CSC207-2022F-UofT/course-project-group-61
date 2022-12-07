package inventorycount;

import entities.FacilityType;
import entities.FacilityUser;
import entities.User;
import entities.UserSession;
import storemainmenu.StoreMainMenuViewModel;
import warehousemainmenu.WarehouseMainMenuViewModel;

public class InventoryCountPresenter implements InventoryCountOutputBoundary{

    private final InventoryCountViewModel viewModel;
    private final StoreMainMenuViewModel storeMainMenuViewModel;
    private final WarehouseMainMenuViewModel warehouseMainMenuViewModel;

    public InventoryCountPresenter(InventoryCountViewModel inventoryCountViewModel, StoreMainMenuViewModel storeMainMenuViewModel, WarehouseMainMenuViewModel warehouseMainMenuViewModel){
        this.viewModel = inventoryCountViewModel;
        this.storeMainMenuViewModel = storeMainMenuViewModel;
        this.warehouseMainMenuViewModel = warehouseMainMenuViewModel;
    }

    public void returnToMainMenu(){
        viewModel.setVisible(false);
        FacilityUser facilityUser = (FacilityUser) UserSession.getUserSession();
        FacilityType facilityType = facilityUser.getType();
        if (facilityType == FacilityType.STORE){
            storeMainMenuViewModel.setVisible(true);
        }else if (facilityType == FacilityType.WAREHOUSE){
            warehouseMainMenuViewModel.setVisible(true);
        }



    }


}
