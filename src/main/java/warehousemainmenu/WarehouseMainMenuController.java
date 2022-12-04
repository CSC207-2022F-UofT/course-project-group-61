package warehousemainmenu;

public class WarehouseMainMenuController {

    WarehouseMainMenuPresenter presenter;
    public WarehouseMainMenuController(WarehouseMainMenuPresenter presenter) {
        this.presenter = presenter;
    }

    /* Calls presenter.changeView based on what button sends input through the view. */
    public void chooseAction(ButtonOption option) {
        presenter.changeView(option);
    }
}
