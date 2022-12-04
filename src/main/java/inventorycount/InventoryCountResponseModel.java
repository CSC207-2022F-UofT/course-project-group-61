package inventorycount;

import java.util.HashMap;

public class InventoryCountResponseModel {
    public HashMap<Long, Integer> count;

    public InventoryCountResponseModel(HashMap<Long, Integer> count){
        this.count = count;
    }

}
