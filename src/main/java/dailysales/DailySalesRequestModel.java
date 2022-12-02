package dailysales;

import java.util.HashMap;
import java.util.UUID;

public class DailySalesRequestModel {
    private HashMap<Long, Integer> dailySales;

    public DailySalesRequestModel(HashMap<Long, Integer> dailySales) {
        this.dailySales = dailySales;
    }

    public HashMap<Long, Integer> getDailySales() {
        return dailySales;
    }
}
