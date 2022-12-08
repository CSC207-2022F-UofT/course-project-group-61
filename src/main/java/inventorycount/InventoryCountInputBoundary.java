package inventorycount;

public interface InventoryCountInputBoundary {
    void updateInventoryCount(InventoryCountRequestModel count);

    InventoryCountResponseModel getCurrentInventoryCount();

    void returnToMainMenu();
}
