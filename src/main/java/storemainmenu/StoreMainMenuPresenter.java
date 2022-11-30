package storemainmenu;

import dailysales.DailySalesViewModel;
import entities.Order;
import order.OrderViewModel;

public class StoreMainMenuPresenter {

    private StoreMainMenuViewModel viewModel;
    private OrderViewModel orderViewModel;
    private DailySalesViewModel dailySalesViewModel;

    public StoreMainMenuPresenter(StoreMainMenuViewModel viewModel, OrderViewModel orderViewModel, DailySalesViewModel dailySalesViewModel) {
        this.viewModel = viewModel;
        this.orderViewModel = orderViewModel;
        this.dailySalesViewModel = dailySalesViewModel;
    }

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
