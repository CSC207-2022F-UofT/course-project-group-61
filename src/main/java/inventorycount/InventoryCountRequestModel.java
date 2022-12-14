package inventorycount;

import java.util.HashMap;

public class InventoryCountRequestModel {
    private final HashMap<Long, Integer> count;

    public InventoryCountRequestModel(HashMap<Long, Integer> count){
        this.count = count;
    }

    public HashMap<Long, Integer> getInventoryCount() {
        return this.count;
    }
}
