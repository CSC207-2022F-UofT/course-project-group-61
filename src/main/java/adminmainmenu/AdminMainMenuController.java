package adminmainmenu;

public class AdminMainMenuController {

    AdminMainMenuPresenter presenter;
    public AdminMainMenuController(AdminMainMenuPresenter presenter) {
        this.presenter = presenter;
        //this.presenter.updateView(option);
    }

    public void chooseAction(ButtonOption option) {
        presenter.changeView(option);
    }









    /*private final StoreMainMenuInputBoundary inputBoundary;

    public StoreMainMenuController(StoreMainMenuInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    *//*
    * place order
    * daily sales
    * inventory count
    * generate report
    * item lookup
    * *//*

    public StoreMainMenuResponseModel placeOrder() {
        StoreMainMenuRequestModel request = new StoreMainMenuRequestModel(buttonOption.PLACE_ORDER);
        return inputBoundary.option(request);
    }

    public StoreMainMenuResponseModel dailySales() {
        StoreMainMenuRequestModel request = new StoreMainMenuRequestModel(buttonOption.DAILY_SALES);
        return inputBoundary.option(request);
    }

    public StoreMainMenuResponseModel invCount() {
        StoreMainMenuRequestModel request = new StoreMainMenuRequestModel(buttonOption.INV_COUNT);
        return inputBoundary.option(request);
    }

    public StoreMainMenuResponseModel genReport() {
        StoreMainMenuRequestModel request = new StoreMainMenuRequestModel(buttonOption.GEN_REPORT);
        return inputBoundary.option(request);
    }

    public StoreMainMenuResponseModel itemLookup() {
        StoreMainMenuRequestModel request = new StoreMainMenuRequestModel(buttonOption.ITEM_LOOKUP);
        return inputBoundary.option(request);
    }*/
}
