package dailysales;

import java.util.HashMap;
import java.util.UUID;

public class DailySalesController {

    private final DailySalesInteractor dailySalesInteractor = new DailySalesInteractor();

    public DailySalesController() {

    }

    public void updateDailySales(UUID facID, HashMap<Long, Integer> dailySales) {
        this.dailySalesInteractor.updateDailySales(facID, dailySales);
    }
}
