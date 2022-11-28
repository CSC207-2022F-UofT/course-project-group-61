package fulfill;

public interface FulfillOutputBoundry {
    void prepareSuccessView(FulfillResponseModel model);

    void prepareOutOfStockView(FulfillResponseModel model);

    void prepareNewOrderView(FulfillResponseModel model);

    void prepareWarehouseMainMenuView(FulfillResponseModel model);
}
