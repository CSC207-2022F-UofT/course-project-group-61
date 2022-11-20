package fulfill;

public interface FulfillOutputBoundry {
    void prepareSuccessView(FulfillResponseModel model);

    void prepareOutOfStockView(FulfillResponseModel model);
}
