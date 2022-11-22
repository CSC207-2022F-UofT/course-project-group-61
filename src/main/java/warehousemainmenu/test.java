package warehousemainmenu;

import itemlookup.ItemLookupViewModel;

public class test {
    public static void main(String[] args) {
        WarehouseMainMenuViewModel viewModel = new WarehouseMainMenuViewModel();
        ItemLookupViewModel itemLookupViewModel = new ItemLookupViewModel();
        WarehouseMainMenuView view = new WarehouseMainMenuView(new WarehouseMainMenuController(new WarehouseMainMenuPresenter(viewModel, itemLookupViewModel)));
        viewModel.addObserver(view);
        viewModel.setVisible(true);


    }
}
