package database;

import entities.Order;

import java.util.HashMap;
import java.util.UUID;

public interface OrderDb {

    HashMap<UUID, Order> getAllOrders();

    Order getOrder(UUID id);

    boolean updateOrder(Order order);
}
