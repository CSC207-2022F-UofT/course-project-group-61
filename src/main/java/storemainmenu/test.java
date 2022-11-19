package storemainmenu;

public class test {
    public static void main(String[] args) {
        StoreMainMenuViewModel viewModel = new StoreMainMenuViewModel();
        StoreMainMenuView view = new StoreMainMenuView(new StoreMainMenuController(new StoreMainMenuPresenter(viewModel)));
        viewModel.addObserver(view);
        viewModel.setVisible(true);


    }
}
