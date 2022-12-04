package storemainmenu;

import inventorycount.InventoryCountViewModel;
import order.OrderViewModel;
import dailysales.DailySalesViewModel;
import itemlookup.ItemLookupViewModel;

public class StoreMainMenuPresenter {


    private StoreMainMenuViewModel viewModel;
    private OrderViewModel orderViewModel;
    private DailySalesViewModel dailySalesViewModel;

    private final InventoryCountViewModel inventoryCountViewModel;
    private final ItemLookupViewModel itemLookupViewModel;

    public StoreMainMenuPresenter(StoreMainMenuViewModel viewModel, OrderViewModel orderViewModel, DailySalesViewModel dailySalesViewModel, InventoryCountViewModel inventoryCountViewModel, ItemLookupViewModel itemLookupViewModel) {
        this.viewModel = viewModel;
        this.orderViewModel = orderViewModel;
        this.dailySalesViewModel = dailySalesViewModel;
        this.inventoryCountViewModel = inventoryCountViewModel;
        this.itemLookupViewModel = itemLookupViewModel;
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
                System.out.println("inv count");
                inventoryCountViewModel.setVisible(true);
                viewModel.setVisible(false);
                break;
            case GEN_REPORT:
                System.out.println("gen report");

                //TODO: set GenerateReportView to visible
                break;
            case ITEM_LOOKUP:
                System.out.println("item lookup");
                viewModel.setVisible(false);
                itemLookupViewModel.setVisible(true);
                //TODO: set ItemLookupView to visible
                break;
        }
    }
}
