package inventorycount;
import java.util.HashMap;

public class InventoryCountController {

    private final InventoryCountInputBoundary inputBoundary;

    public InventoryCountController(InventoryCountInputBoundary inputBoundary){

        this.inputBoundary = inputBoundary;

    }


    public void submitCount(HashMap<Long, Integer> count){

        this.inputBoundary.updateInventoryCount(new InventoryCountRequestModel(count));
        // TODO: return logic for presenter, void for now

    }

    public HashMap<Long, Integer> getCurrentInventoryCount(){
        return inputBoundary.getCurrentInventoryCount().count;
    }

    public void returnToMainMenu(){
        inputBoundary.returnToMainMenu();}





}
