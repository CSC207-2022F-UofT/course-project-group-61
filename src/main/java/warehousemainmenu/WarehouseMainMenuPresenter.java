package warehousemainmenu;

import entities.UserSession;
import fulfill.FulfillViewModel;
import inventorycount.InventoryCountViewModel;
import itemlookup.ItemLookupViewModel;
import userlogin.UserLoginViewModel;

public class WarehouseMainMenuPresenter {

    private final WarehouseMainMenuViewModel viewModel;
    private final FulfillViewModel fulfillViewModel;
    private final ItemLookupViewModel itemLookupViewModel;
    private final InventoryCountViewModel inventoryCountViewModel;
    private final UserLoginViewModel loginViewModel;

    public WarehouseMainMenuPresenter(WarehouseMainMenuViewModel viewModel, ItemLookupViewModel itemLookupViewModel, FulfillViewModel fulfillViewModel, InventoryCountViewModel inventoryCountViewModel, UserLoginViewModel loginViewModel) {
        this.viewModel = viewModel;
        this.itemLookupViewModel = itemLookupViewModel;
        this.fulfillViewModel = fulfillViewModel;
        this.inventoryCountViewModel = inventoryCountViewModel;
        this.loginViewModel = loginViewModel;
    }

    /* Sets WarehouseMainMenuView to be invisible, sets selected button press view model to be visible. */
    public void changeView(ButtonOption option) {
        viewModel.setVisible(false);
        switch (option) {
            case FULFILL_ORDER:
                fulfillViewModel.setVisible(true);
                break;
            case INV_COUNT:
                viewModel.setVisible(false);
                inventoryCountViewModel.setVisible(true);
                break;
            case ITEM_LOOKUP:
                itemLookupViewModel.setVisible(true);
                break;
        }
    }

    /* Logs out of the main menu view to the login screen again */
    public void logout() {
        viewModel.setVisible(false);
        loginViewModel.setVisible(true);

        UserSession.setUserSession(null);
    }
}
