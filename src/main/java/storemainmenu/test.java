package storemainmenu;

import itemlookup.ItemLookupViewModel;
import order.OrderViewModel;

public class test {
    public static void main(String[] args) {
        StoreMainMenuViewModel viewModel = new StoreMainMenuViewModel();
        OrderViewModel orderViewModel = new OrderViewModel();
        ItemLookupViewModel itemLookupViewModel = new ItemLookupViewModel();
        StoreMainMenuView view = new StoreMainMenuView(new StoreMainMenuController(new StoreMainMenuPresenter(viewModel, orderViewModel, itemLookupViewModel)));
        viewModel.addObserver(view);
        viewModel.setVisible(true);

    }
}
