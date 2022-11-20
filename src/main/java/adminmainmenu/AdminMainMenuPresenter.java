package adminmainmenu;

public class AdminMainMenuPresenter {

    private AdminMainMenuViewModel viewModel;

    public AdminMainMenuPresenter(AdminMainMenuViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void changeView(ButtonOption option) {
        viewModel.setVisible(false);
        switch (option) {
            case NEW_FACILITY:
                System.out.println("new facility");
                //TODO: set NewFacilityView to visible
                break;
            case NEW_ITEM:
                System.out.println("new item");

                //TODO: set NewItemView to visible
                break;
            case NEW_USER:
                System.out.println("new user");

                //TODO: set NewUserView to visible
                break;
            case ITEM_LOOKUP:
                System.out.println("item lookup");

                //TODO: set OrderView to visible
                break;
        }
    }
}
