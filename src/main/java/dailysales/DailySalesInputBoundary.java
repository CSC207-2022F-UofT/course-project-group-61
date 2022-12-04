package dailysales;

public interface DailySalesInputBoundary {

    DailySalesResponseModel inputDailySales(DailySalesRequestModel model);

    boolean upcExists(Long upc);

    String getProductName(Long upc);

    void returnToMenu();
}
