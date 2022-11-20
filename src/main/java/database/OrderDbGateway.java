package database;

import entities.Facility;
import entities.Order;

import java.io.EOFException;
import java.io.IOException;
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
        } catch (EOFException eof) {
            HashMap<UUID, Order> tempMap = new HashMap<UUID, Order>();
            try {
                this.db.write(tempMap);
                return (HashMap<UUID, Order>) db.read();
            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
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

    //for testing purposes
    public void fileReset() {
        try {
            HashMap<UUID, Order> newHash = new HashMap<UUID, Order>();
            db.write(newHash);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
