package dailysales;

import java.util.HashMap;

public class DailySalesRequestModel {
    private final HashMap<Long, Integer> dailySales;

    public DailySalesRequestModel(HashMap<Long, Integer> dailySales) {
        this.dailySales = dailySales;
    }

    public HashMap<Long, Integer> getDailySales() {
        return dailySales;
    }
}
