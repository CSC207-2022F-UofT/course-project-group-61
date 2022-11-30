package storemainmenu;

import dailysales.DailySalesViewModel;
import order.OrderViewModel;

public class test {
    public static void main(String[] args) {
        StoreMainMenuViewModel viewModel = new StoreMainMenuViewModel();
        OrderViewModel orderViewModel = new OrderViewModel();
        DailySalesViewModel dailySalesViewModel = new DailySalesViewModel();
        StoreMainMenuView view = new StoreMainMenuView(new StoreMainMenuController(new StoreMainMenuPresenter(viewModel, orderViewModel, dailySalesViewModel)));
        viewModel.addObserver(view);
        viewModel.setVisible(true);

    }
}
