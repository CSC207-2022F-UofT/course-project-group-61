package inventorycount;

import entities.Facility;

import java.util.HashMap;
import java.util.Map;

public class InventoryCountController {

    private final InventoryCountInteractor inventoryCountInteractor;

    public InventoryCountController(InventoryCountInteractor interactor){

        this.inventoryCountInteractor = interactor;

    }


    public void submitCount(HashMap<Long, Integer> count){

        this.inventoryCountInteractor.updateInventoryCount(count);
        // TODO: return logic for presenter, void for now

    }

    public HashMap<Long, Integer> getCurrentInventoryCount(){
        return inventoryCountInteractor.getCurrentInventoryCount();
    }

    public void returnToMainMenu(){inventoryCountInteractor.returnToMainMenu();}





}
