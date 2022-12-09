package fulfill;

import java.util.UUID;

public class FulfillRequestModel {
    private final UUID storeID;
    private final UUID warehouseID;
    private final UUID orderID;

    public FulfillRequestModel(UUID storeID, UUID warehouseID, UUID orderID){
        this.storeID = storeID;
        this.warehouseID = warehouseID;
        this.orderID = orderID;
    }

    public UUID getStoreID() {
        return storeID;
    }

    public UUID getWarehouseID() {
        return warehouseID;
    }

    public UUID getOrderID() {
        return orderID;
    }
}
