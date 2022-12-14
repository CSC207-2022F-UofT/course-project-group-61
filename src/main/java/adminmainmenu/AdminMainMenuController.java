package adminmainmenu;

public class AdminMainMenuController {

    private final AdminMainMenuPresenter presenter;
    public AdminMainMenuController(AdminMainMenuPresenter presenter) {
        this.presenter = presenter;
    }

    /* Calls presenter.changeView based on what button sends input through the view. */
    public void chooseAction(ButtonOption option) {
        presenter.changeView(option);
    }

    /* Calls the presenter telling them to logout of the main menu view */
    public void logout() {
        presenter.logout();
    }
}
