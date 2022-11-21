package fulfill;

public interface FulfillInputBoundry {
    FulfillResponseModel attemptUpdateInventory(FulfillRequestModel requestModel);
    FulfillResponseModel confirmUpdateInventory(FulfillRequestModel requestModel);
}
