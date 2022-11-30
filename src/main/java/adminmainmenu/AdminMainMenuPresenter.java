package adminmainmenu;

import newfacility.NewFacilityViewModel;
import newuser.NewUserViewModel;

public class AdminMainMenuPresenter {

    private final AdminMainMenuViewModel viewModel;
    private final NewUserViewModel newUserViewModel;
    private final NewFacilityViewModel newFacilityViewModel;

    public AdminMainMenuPresenter(AdminMainMenuViewModel viewModel, NewUserViewModel newUserViewModel, NewFacilityViewModel newFacilityViewModel) {
        this.viewModel = viewModel;
        this.newUserViewModel = newUserViewModel;
        this.newFacilityViewModel = newFacilityViewModel;
    }

    public void changeView(ButtonOption option) {
        viewModel.setVisible(false);
        switch (option) {
            case NEW_FACILITY:
                System.out.println("new facility");
                newFacilityViewModel.setVisible(true);
                break;
            case NEW_ITEM:
                System.out.println("new item");

                //TODO: set NewItemView to visible
                break;
            case NEW_USER:
                System.out.println("new user");

                newUserViewModel.setVisible(true);
                break;
            case ITEM_LOOKUP:
                System.out.println("item lookup");

                //TODO: set OrderView to visible
                break;
        }
    }
}
