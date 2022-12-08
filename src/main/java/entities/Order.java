package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/* Order gets placed by Store User to query Warehouses for products, stores all relevant information. */
public class Order implements Serializable {
    // Stores the order id number.
    private final UUID id;

    // Variables to store the ID of the warehouse fulfilling the order and the store receiving it
    final private UUID warehouseID;
    final private UUID storeID;

    // Stores the username of the user that created the Order
    final private String username;
    // Stores the current status of the Order, uses the static public markers above
    private OrderStatus currentStatus;

    // Stores the items in the order as a HashMap where the first int is
    // the UPC code of the product and second int is the quantity of it
    final private HashMap<Long, Integer> orderQuantities;

    // Stores the important timestamps of the order object:
    // the creation date, fulfillment date when it does happen
    private final HashMap<OrderStatus, Date> timestamps = new HashMap<>();

    public Order(UUID warehouseID, UUID storeID, String username, HashMap<Long, Integer> orderQuantites, Date dateCreated){
        // Stores the values
        this.warehouseID = warehouseID;
        this.storeID = storeID;
        this.username = username;

        currentStatus = OrderStatus.CREATED;

        this.orderQuantities = orderQuantites;
        timestamps.put(OrderStatus.CREATED, dateCreated);
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public UUID getWarehouseID() {
        return warehouseID;
    }

    public UUID getStoreID() {
        return storeID;
    }

    public OrderStatus getStatus() {
        return currentStatus;
    }

    public String getOrderingUser() {
        return username;
    }

    public HashMap<Long, Integer> getOrderQuantities() {
        return orderQuantities;
    }

    public HashMap<OrderStatus, Date> getTimestamps() {
        return timestamps;
    }

    public void setStatus(OrderStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public void fulfillOrder(Date dateFulfilled){
        /*
        Marks the order as fulfilled in the timestamps
        It also sets its own new status
         */
        timestamps.put(OrderStatus.FULFILLED, dateFulfilled);
        this.setStatus(OrderStatus.FULFILLED);
    }
}
