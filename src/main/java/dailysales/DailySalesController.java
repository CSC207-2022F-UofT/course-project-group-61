package dailysales;

import java.util.HashMap;
import java.util.UUID;

public class DailySalesController {

    private final DailySalesInputBoundary interactor;

    public DailySalesController(DailySalesInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void inputDailySales(HashMap<Long, Integer> dailySales) {
        DailySalesRequestModel request = new DailySalesRequestModel(dailySales);
        this.interactor.inputDailySales(request);
    }

    public boolean upcExists(Long upc) {
        return interactor.upcExists(upc);
    }
    public String getProductName(Long upc) {
        return interactor.getProductName(upc);
    }
}
