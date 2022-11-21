package inventorycount;

import javax.naming.InsufficientResourcesException;
import java.util.HashMap;
import java.util.UUID;

public class InventoryCountController {

    private final InventoryCountInteractor inventoryCountInteractor;

    public InventoryCountController(UUID facID){

        this.inventoryCountInteractor = new InventoryCountInteractor(facID);

    }


    public void submitCount(HashMap<Long, Integer> count){

        this.inventoryCountInteractor.updateInventoryCount(count);
        // TODO: return logic for presenter, void for now

    }

    public HashMap<Long, Integer> getCurrentCount(){
        return inventoryCountInteractor.getCurrentCount();
    }



}
