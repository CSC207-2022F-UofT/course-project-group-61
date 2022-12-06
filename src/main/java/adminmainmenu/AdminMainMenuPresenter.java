package adminmainmenu;

import itemlookup.ItemLookupViewModel;
import newfacility.NewFacilityViewModel;
import newitem.NewItemViewModel;
import newuser.NewUserViewModel;

public class AdminMainMenuPresenter {

    private final AdminMainMenuViewModel viewModel;
    private final NewUserViewModel newUserViewModel;
    private final NewFacilityViewModel newFacilityViewModel;
    private final NewItemViewModel newItemViewModel;
    private final ItemLookupViewModel itemLookupViewModel;

    public AdminMainMenuPresenter(AdminMainMenuViewModel viewModel, NewUserViewModel newUserViewModel, NewFacilityViewModel newFacilityViewModel, NewItemViewModel newItemViewModel, ItemLookupViewModel itemLookupViewModel) {
        this.viewModel = viewModel;
        this.newUserViewModel = newUserViewModel;
        this.newFacilityViewModel = newFacilityViewModel;
        this.newItemViewModel = newItemViewModel;
        this.itemLookupViewModel = itemLookupViewModel;
    }

    /* Sets AdminMainMenuView to be invisible, sets selected button press view model to be visible. */
    public void changeView(ButtonOption option) {
        viewModel.setVisible(false);
        switch (option) {
            case NEW_FACILITY:
                newFacilityViewModel.setVisible(true);
                break;
            case NEW_ITEM:
                newItemViewModel.setVisible(true);
                break;
            case NEW_USER:
                newUserViewModel.setVisible(true);
                break;
            case ITEM_LOOKUP:
                itemLookupViewModel.setVisible(true);
                break;
        }
    }
}
