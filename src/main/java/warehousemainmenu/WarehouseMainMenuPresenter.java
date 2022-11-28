package warehousemainmenu;

import itemlookup.ItemLookupViewModel;
import fulfill.FulfillViewModel;

public class WarehouseMainMenuPresenter {

    private WarehouseMainMenuViewModel viewModel;
    private ItemLookupViewModel itemLookupViewModel;

    public WarehouseMainMenuPresenter(WarehouseMainMenuViewModel viewModel, ItemLookupViewModel itemLookupViewModel, FulfillViewModel fulfillViewModel) {
        this.viewModel = viewModel;
        this.itemLookupViewModel = itemLookupViewModel;
        this.fulfillViewModel = fulfillViewModel;
    }

    public void changeView(ButtonOption option) {
        viewModel.setVisible(false);
        switch (option) {
            case FULFILL_ORDER:
                viewModel.setVisible(false);
                fulfillViewModel.setVisible(true);
                break;
            case INV_COUNT:
                System.out.println("inv count");

                //TODO: set InventoryCountView to visible
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
