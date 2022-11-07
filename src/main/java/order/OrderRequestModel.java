package order;

import java.util.Date;
import java.util.HashMap;

public class OrderRequestModel {

    private final int storeID;
    private final String requestedBy;
    private final HashMap<Integer, Integer> orderQuantities;
    private final Date dateRequested;

    public OrderRequestModel(int storeID, String requestedBy, HashMap<Integer, Integer> orderQuantities, Date dateRequested) {
        this.storeID = storeID;
        this.requestedBy = requestedBy;
        this.orderQuantities = orderQuantities;
        this.dateRequested = dateRequested;
    }

    public int getStoreID() {
        return storeID;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public HashMap<Integer, Integer> getOrderQuantities() {
        return orderQuantities;
    }

    public Date getDateRequested() {
        return dateRequested;
    }
}
