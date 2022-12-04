package inventorycount;

import java.util.HashMap;

public interface InventoryCountInputBoundary {
    void updateInventoryCount(InventoryCountRequestModel count);

    InventoryCountResponseModel getCurrentInventoryCount();

    void returnToMainMenu();
}
