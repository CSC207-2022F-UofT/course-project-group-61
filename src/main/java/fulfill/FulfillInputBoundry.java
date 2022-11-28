package fulfill;

public interface FulfillInputBoundry {
    FulfillResponseModel attemptUpdateInventory(FulfillRequestModel requestModel);
    FulfillResponseModel confirmUpdateInventory(FulfillRequestModel requestModel);
    FulfillResponseModel newSelectedOrder(FulfillRequestModel requestModel);
    FulfillResponseModel backToMainMenu(FulfillRequestModel requestModel);
}
