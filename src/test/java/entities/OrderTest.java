package entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;


public class OrderTest {
    public static Order newOrder;
    public static final HashMap<Long, Integer> orderQuantities = new HashMap<>();
    public static final Date creationDate = new Date();

    private UUID warehouseId;
    private UUID storeId;

    @Before
    public void setup(){
        // Puts some basic stuff in the order and builds it
        orderQuantities.put(1L, 10);
        orderQuantities.put(2L, 120);
        warehouseId = UUID.randomUUID();
        storeId = UUID.randomUUID();

        newOrder = new Order(warehouseId, storeId, "tester", orderQuantities, creationDate);
    }

    @Test
    public void gettersTest(){
        // Tests all the basic getters
        Assertions.assertEquals(newOrder.getOrderingUser(), "tester");
        Assertions.assertEquals(newOrder.getStoreID(), storeId);
        Assertions.assertEquals(newOrder.getWarehouseID(), warehouseId);
        Assertions.assertEquals(newOrder.getStatus(), OrderStatus.CREATED);

        // Tests the order quantities, ensures the keys are the same and actual quantities
        HashMap<Long, Integer> givenOrderQuantities = newOrder.getOrderQuantities();
        for(Long key: givenOrderQuantities.keySet()){
            Assertions.assertTrue(orderQuantities.containsKey(key));
            Assertions.assertEquals(orderQuantities.get(key), givenOrderQuantities.get(key));
        }

        // Tests the dates, it doesn't test the fulfilled date as the ordered hasn't been fulfilled yet
        // this is tested in the setters
        Assertions.assertEquals(creationDate, newOrder.getTimestamps().get(OrderStatus.CREATED));

    }

    @Test
    public void settersTest(){
        // Tests the setStatus
        newOrder.setStatus(OrderStatus.FULFILLED);
        Assertions.assertEquals(newOrder.getStatus(), OrderStatus.FULFILLED);

        newOrder.setStatus(OrderStatus.DELAYED);
        Assertions.assertEquals(newOrder.getStatus(), OrderStatus.DELAYED);

        // Tests the order getting fulfilled
        Date fulfilledDate = new Date();
        newOrder.fulfillOrder(fulfilledDate);
        Assertions.assertEquals(newOrder.getTimestamps().get(OrderStatus.FULFILLED), fulfilledDate);
        Assertions.assertEquals(newOrder.getStatus(), OrderStatus.FULFILLED);
    }
}
