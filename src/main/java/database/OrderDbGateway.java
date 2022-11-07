package database;

import entities.Order;

import java.util.HashMap;
import java.util.UUID;

public class OrderDbGateway implements OrderDb {

    private final String ORDER_FILE_PATH = "data/orders.ser";
    private final DBReadWriter db;

    public OrderDbGateway() {
        this.db = new DBReadWriter(ORDER_FILE_PATH);
    }

    @Override
    public HashMap<UUID, Order> getAllOrders() {
        try {
            return (HashMap<UUID, Order>) db.read();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateOrder(Order order) {
        try {
            HashMap<UUID, Order> orders = getAllOrders();
            orders.put(order.getId(), order);
            db.write(orders);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Order getOrder(UUID id) {
        try {
            HashMap<UUID, Order> orders = getAllOrders();
            return orders.get(id);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
