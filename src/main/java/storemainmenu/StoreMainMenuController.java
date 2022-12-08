package storemainmenu;

import database.FacilityDbGateway;
public class StoreMainMenuController {

    StoreMainMenuPresenter presenter;
    FacilityDbGateway facilityDB;
    public StoreMainMenuController(StoreMainMenuPresenter presenter, FacilityDbGateway facilityDB) {
        this.presenter = presenter;
        this.facilityDB = facilityDB;
    }

    /* Calls presenter.changeView based on what button sends input through the view. */
    public void chooseAction(ButtonOption option) {
        presenter.changeView(option);
    }

    public FacilityDbGateway getFacilityDB() {
        return facilityDB;
    }
}
