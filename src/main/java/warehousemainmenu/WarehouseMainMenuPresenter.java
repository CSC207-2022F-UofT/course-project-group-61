package warehousemainmenu;

import fulfill.FulfillViewModel;
import inventorycount.InventoryCountView;
import inventorycount.InventoryCountViewModel;
import itemlookup.ItemLookupViewModel;

public class WarehouseMainMenuPresenter {

    private final WarehouseMainMenuViewModel viewModel;
    private final FulfillViewModel fulfillViewModel;
    private final ItemLookupViewModel itemLookupViewModel;
    private final InventoryCountViewModel inventoryCountViewModel;

    public WarehouseMainMenuPresenter(WarehouseMainMenuViewModel viewModel, ItemLookupViewModel itemLookupViewModel, FulfillViewModel fulfillViewModel, InventoryCountViewModel inventoryCountViewModel) {
        this.viewModel = viewModel;
        this.itemLookupViewModel = itemLookupViewModel;
        this.fulfillViewModel = fulfillViewModel;
        this.inventoryCountViewModel = inventoryCountViewModel;
    }

    /* Sets WarehouseMainMenuView to be invisible, sets selected button press view model to be visible. */
    public void changeView(ButtonOption option) {
        viewModel.setVisible(false);
        switch (option) {
            case FULFILL_ORDER:
                viewModel.setVisible(false);
                fulfillViewModel.setVisible(true);
                break;
            case INV_COUNT:
                System.out.println("inv count");
                viewModel.setVisible(false);
                inventoryCountViewModel.setVisible(true);
                break;
            case GEN_REPORT:
                System.out.println("gen report");

                //TODO: set GenerateReportView to visible
                break;
            case ITEM_LOOKUP:
                System.out.println("item lookup");
                viewModel.setVisible(false);
                itemLookupViewModel.setVisible(true);
                break;
        }
    }
}
