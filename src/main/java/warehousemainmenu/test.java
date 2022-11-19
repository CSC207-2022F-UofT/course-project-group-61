package warehousemainmenu;

public class test {
    public static void main(String[] args) {
        WarehouseMainMenuViewModel viewModel = new WarehouseMainMenuViewModel();
        WarehouseMainMenuView view = new WarehouseMainMenuView(new WarehouseMainMenuController(new WarehouseMainMenuPresenter(viewModel)));
        viewModel.addObserver(view);
        viewModel.setVisible(true);


    }
}
