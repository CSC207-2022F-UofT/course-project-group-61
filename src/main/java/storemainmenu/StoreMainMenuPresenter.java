package storemainmenu;

public class StoreMainMenuPresenter {

    private StoreMainMenuViewModel viewModel;

    public StoreMainMenuPresenter(StoreMainMenuViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void changeView(ButtonOption option) {
        viewModel.setVisible(false);
        switch (option) {
            case PLACE_ORDER:
                //TODO: set OrderView to visible
                break;
            case DAILY_SALES:
                //TODO: set OrderView to visible
                break;
            case INV_COUNT:
                //TODO: set OrderView to visible
                break;
            case GEN_REPORT:
                //TODO: set OrderView to visible
                break;
            case ITEM_LOOKUP:
                //TODO: set OrderView to visible
                break;
        }
    }
}
