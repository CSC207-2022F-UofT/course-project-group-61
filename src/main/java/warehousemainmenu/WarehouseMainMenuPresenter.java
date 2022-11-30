package warehousemainmenu;

import fulfill.FulfillViewModel;

public class WarehouseMainMenuPresenter {

    private WarehouseMainMenuViewModel viewModel;

    private FulfillViewModel fulfillViewModel;

    public WarehouseMainMenuPresenter(WarehouseMainMenuViewModel viewModel, FulfillViewModel fulfillViewModel) {
        this.viewModel = viewModel;
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

                //TODO: set ItemLookupView to visible
                break;
        }
    }
}
