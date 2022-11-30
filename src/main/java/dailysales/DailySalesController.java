package dailysales;

import java.util.HashMap;
import java.util.UUID;

public class DailySalesController {

    private DailySalesInputBoundary inputBoundary;

    public DailySalesController(DailySalesInputBoundary inputBoundary) {

    }

    public void inputDailySales(HashMap<Long, Integer> dailySales) {
        DailySalesRequestModel request = new DailySalesRequestModel(dailySales);
        this.inputBoundary.inputDailySales(request);
    }
}
