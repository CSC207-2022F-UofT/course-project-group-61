package adminmainmenu;

import entities.UserSession;
import itemlookup.ItemLookupPresenter;

import itemlookup.ItemLookupViewModel;
import newfacility.NewFacilityViewModel;
import newitem.NewItemViewModel;
import newuser.NewUserViewModel;
import userlogin.UserLoginViewModel;

public class AdminMainMenuPresenter {

    private final AdminMainMenuViewModel viewModel;
    private final NewUserViewModel newUserViewModel;
    private final NewFacilityViewModel newFacilityViewModel;
    private final NewItemViewModel newItemViewModel;
    private final ItemLookupViewModel itemLookupViewModel;
    private final UserLoginViewModel loginViewModel;

    public AdminMainMenuPresenter(AdminMainMenuViewModel viewModel, NewUserViewModel newUserViewModel, NewFacilityViewModel newFacilityViewModel, NewItemViewModel newItemViewModel, ItemLookupViewModel itemLookupViewModel, UserLoginViewModel loginViewModel) {
        this.viewModel = viewModel;
        this.newUserViewModel = newUserViewModel;
        this.newFacilityViewModel = newFacilityViewModel;
        this.newItemViewModel = newItemViewModel;
        this.itemLookupViewModel = itemLookupViewModel;
        this.loginViewModel = loginViewModel;
    }

    /* Sets AdminMainMenuView to be invisible, sets selected button press view model to be visible. */
    public void changeView(ButtonOption option) {
        viewModel.setVisible(false);
        switch (option) {
            case NEW_FACILITY:
                System.out.println("new facility");
                newFacilityViewModel.setVisible(true);
                break;
            case NEW_ITEM:
                System.out.println("new item");
                newItemViewModel.setVisible(true);
                break;
            case NEW_USER:
                System.out.println("new user");

                newUserViewModel.setVisible(true);
                break;
            case ITEM_LOOKUP:
                System.out.println("item lookup");
                itemLookupViewModel.setVisible(true);

                break;
        }
    }

    /* Logs out of the main menu view to the login screen again */
    public void logout() {
        viewModel.setVisible(false);
        loginViewModel.setVisible(true);

        UserSession.setUserSession(null);
    }
}
