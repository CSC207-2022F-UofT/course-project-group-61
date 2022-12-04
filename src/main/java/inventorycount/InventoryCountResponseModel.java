package inventorycount;

import java.util.HashMap;

public class InventoryCountResponseModel {
    private HashMap<Long, Integer> count;

    public InventoryCountResponseModel(HashMap<Long, Integer> count){
        this.count = count;
    }

    public HashMap<Long,Integer> getInventoryCount(){
        return this.count;
    }

}
