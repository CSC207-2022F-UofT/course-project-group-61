package warehousemainmenu;

import database.FacilityDbGateway;

public class WarehouseMainMenuController {

    WarehouseMainMenuPresenter presenter;
    FacilityDbGateway facilityDB;
    public WarehouseMainMenuController(WarehouseMainMenuPresenter presenter, FacilityDbGateway facilityDB) {
        this.presenter = presenter;
        this.facilityDB = facilityDB;
    }

    /* Calls presenter.changeView based on what button sends input through the view. */
    public void chooseAction(ButtonOption option) {
        presenter.changeView(option);
    }


    /* Calls the presenter telling them to logout of the main menu view */
    public void logout() {
        presenter.logout();
    }

    public FacilityDbGateway getFacilityDB() {
        return facilityDB;

    }
}
