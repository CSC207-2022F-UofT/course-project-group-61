package warehousemainmenu;

import itemlookup.ItemLookupViewModel;

public class WarehouseMainMenuPresenter {

    private WarehouseMainMenuViewModel viewModel;
    private ItemLookupViewModel itemLookupViewModel;

    public WarehouseMainMenuPresenter(WarehouseMainMenuViewModel viewModel, ItemLookupViewModel itemLookupViewModel) {
        this.viewModel = viewModel;
        this.itemLookupViewModel = itemLookupViewModel;
    }

    public void changeView(ButtonOption option) {
        viewModel.setVisible(false);
        switch (option) {
            case FULFILL_ORDER:
                System.out.println("fulfill order");
                //TODO: set FulfillView to visible
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
