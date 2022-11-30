package storemainmenu;

import order.OrderViewModel;

public class StoreMainMenuPresenter {

    private final StoreMainMenuViewModel viewModel;
    private final OrderViewModel orderViewModel;

    public StoreMainMenuPresenter(StoreMainMenuViewModel viewModel, OrderViewModel orderViewModel) {
        this.viewModel = viewModel;
        this.orderViewModel = orderViewModel;
    }

    /* Sets StoreMainMenuView to be invisible, sets selected button press view model to be visible. */
    public void changeView(ButtonOption option) {
        viewModel.setVisible(false);
        switch (option) {
            case PLACE_ORDER:
                orderViewModel.setVisible(true);
                break;
            case DAILY_SALES:
                System.out.println("daily sales");

                //TODO: set DailySalesView to visible
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
