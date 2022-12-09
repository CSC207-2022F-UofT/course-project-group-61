package fulfill;

import warehousemainmenu.WarehouseMainMenuViewModel;

public class FulfillPresenter implements FulfillOutputBoundry{
    private final FulfillViewModel viewModel;

    private final WarehouseMainMenuViewModel warehouseMainMenuViewModel;

    public FulfillPresenter(FulfillViewModel viewModel, WarehouseMainMenuViewModel warehouseMainMenuViewModel){
        this.viewModel = viewModel;
        this.warehouseMainMenuViewModel = warehouseMainMenuViewModel;
    }

    public void prepareSuccessView(FulfillResponseModel requestModel){
        viewModel.successfulFulfillment();
    }

    public void prepareOutOfStockView(FulfillResponseModel model){
        viewModel.failedFulfillment(model.getOutOfStockItems());
    }

    public void prepareNewOrderView(FulfillResponseModel model){
        viewModel.addNewOrder(model.getOrder());
    }

    public void prepareWarehouseMainMenuView(FulfillResponseModel model){
        viewModel.setVisible(false);
        warehouseMainMenuViewModel.setVisible(true);
    }
}
