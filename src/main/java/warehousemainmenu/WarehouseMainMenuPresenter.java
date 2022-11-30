package warehousemainmenu;

import fulfill.FulfillViewModel;

public class WarehouseMainMenuPresenter {

    private final WarehouseMainMenuViewModel viewModel;

    private final FulfillViewModel fulfillViewModel;

    public WarehouseMainMenuPresenter(WarehouseMainMenuViewModel viewModel, FulfillViewModel fulfillViewModel) {
        this.viewModel = viewModel;
        this.fulfillViewModel = fulfillViewModel;
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

                //TODO: set InventoryCountView to visible
                break;
            case GEN_REPORT:
                System.out.println("gen report");

                //TODO: set GenerateReportView to visible
                break;
            case ITEM_LOOKUP:
                System.out.println("item lookup");

                //TODO: set ItemLookupView to visible
                break;
        }
    }
}
