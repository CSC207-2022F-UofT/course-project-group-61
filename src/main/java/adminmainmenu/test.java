package adminmainmenu;

public class test {
    public static void main(String[] args) {
        AdminMainMenuViewModel viewModel = new AdminMainMenuViewModel();
        AdminMainMenuView view = new AdminMainMenuView(new AdminMainMenuController(new AdminMainMenuPresenter(viewModel)));
        viewModel.addObserver(view);
        viewModel.setVisible(true);


    }
}
