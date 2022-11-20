package fulfill;

import java.util.HashMap;

public class FulfillResponseModel {
    private final FulfillStatus status;
    private final HashMap<Long, Boolean> outOfStockItems;

    public FulfillResponseModel(FulfillStatus status, HashMap<Long, Boolean> outOfStockItems){
        this.status = status;
        this.outOfStockItems = outOfStockItems;
    }

    public FulfillStatus getStatus() {
        return status;
    }

    public HashMap<Long, Boolean> getOutOfStockItems() {
        return outOfStockItems;
    }
}
