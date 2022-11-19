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
                System.out.println("place order");
                //TODO: set OrderView to visible
                break;
            case DAILY_SALES:
                System.out.println("daily sales");

                //TODO: set OrderView to visible
                break;
            case INV_COUNT:
                System.out.println("inv count");

                //TODO: set OrderView to visible
                break;
            case GEN_REPORT:
                System.out.println("gen report");

                //TODO: set OrderView to visible
                break;
            case ITEM_LOOKUP:
                System.out.println("item lookup");

                //TODO: set OrderView to visible
                break;
        }
    }
}
