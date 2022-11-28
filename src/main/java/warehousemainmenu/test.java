package warehousemainmenu;

import itemlookup.ItemLookupViewModel;
import fulfill.FulfillViewModel;

public class test {
    public static void main(String[] args) {
        WarehouseMainMenuViewModel viewModel = new WarehouseMainMenuViewModel();
        ItemLookupViewModel itemLookupViewModel = new ItemLookupViewModel();
        FullfillViewModel fullfillViewModel = new FullfillViewModel();
        WarehouseMainMenuView view = new WarehouseMainMenuView(new WarehouseMainMenuController(new WarehouseMainMenuPresenter(viewModel, itemLookupViewModel, fulfillViewModel)));
        viewModel.addObserver(view);
        viewModel.setVisible(true);


    }
}
