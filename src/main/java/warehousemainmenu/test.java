package warehousemainmenu;

import fulfill.FulfillViewModel;

public class test {
    public static void main(String[] args) {
        WarehouseMainMenuViewModel viewModel = new WarehouseMainMenuViewModel();
        WarehouseMainMenuView view = new WarehouseMainMenuView(new WarehouseMainMenuController(new WarehouseMainMenuPresenter(viewModel, new FulfillViewModel())));
        viewModel.addObserver(view);
        viewModel.setVisible(true);


    }
}
