package storemainmenu;

import order.OrderViewModel;

public class test {
    public static void main(String[] args) {
        StoreMainMenuViewModel viewModel = new StoreMainMenuViewModel();
        OrderViewModel orderViewModel = new OrderViewModel();
        StoreMainMenuView view = new StoreMainMenuView(new StoreMainMenuController(new StoreMainMenuPresenter(viewModel, orderViewModel)));
        viewModel.addObserver(view);
        viewModel.setVisible(true);

    }
}
