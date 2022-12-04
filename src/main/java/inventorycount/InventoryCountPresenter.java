package inventorycount;

import storemainmenu.StoreMainMenuViewModel;

public class InventoryCountPresenter {

    private final InventoryCountViewModel viewModel;
    private final StoreMainMenuViewModel storeMainMenuViewModel;

    public InventoryCountPresenter(InventoryCountViewModel inventoryCountViewModel, StoreMainMenuViewModel storeMainMenuViewModel){
        this.viewModel = inventoryCountViewModel;
        this.storeMainMenuViewModel = storeMainMenuViewModel;
    }

    public void returnToMainMenu(){
        viewModel.setVisible(false);
        storeMainMenuViewModel.setVisible(true);
    }


}
