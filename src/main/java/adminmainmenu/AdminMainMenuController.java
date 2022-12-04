package adminmainmenu;

public class AdminMainMenuController {

    AdminMainMenuPresenter presenter;
    public AdminMainMenuController(AdminMainMenuPresenter presenter) {
        this.presenter = presenter;
    }

    /* Calls presenter.changeView based on what button sends input through the view. */
    public void chooseAction(ButtonOption option) {
        presenter.changeView(option);
    }
}
