package storemainmenu;

import entities.UserSession;
import inventorycount.InventoryCountViewModel;
import order.OrderViewModel;
import dailysales.DailySalesViewModel;
import itemlookup.ItemLookupViewModel;
import userlogin.UserLoginViewModel;

public class StoreMainMenuPresenter {


    private final StoreMainMenuViewModel viewModel;
    private final OrderViewModel orderViewModel;
    private final DailySalesViewModel dailySalesViewModel;

    private final InventoryCountViewModel inventoryCountViewModel;
    private final ItemLookupViewModel itemLookupViewModel;
    private final UserLoginViewModel loginViewModel;

    public StoreMainMenuPresenter(StoreMainMenuViewModel viewModel, OrderViewModel orderViewModel, DailySalesViewModel dailySalesViewModel, InventoryCountViewModel inventoryCountViewModel, ItemLookupViewModel itemLookupViewModel, UserLoginViewModel loginViewModel) {
        this.viewModel = viewModel;
        this.orderViewModel = orderViewModel;
        this.dailySalesViewModel = dailySalesViewModel;
        this.inventoryCountViewModel = inventoryCountViewModel;
        this.itemLookupViewModel = itemLookupViewModel;
        this.loginViewModel = loginViewModel;
    }

    /* Sets StoreMainMenuView to be invisible, sets selected button press view model to be visible. */
    public void changeView(ButtonOption option) {
        viewModel.setVisible(false);
        switch (option) {
            case PLACE_ORDER:
                orderViewModel.setVisible(true);
                break;
            case DAILY_SALES:
                dailySalesViewModel.setVisible(true);
                break;
            case INV_COUNT:
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
