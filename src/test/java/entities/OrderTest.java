package entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;


public class OrderTest {
    public static Order newOrder;
    public static HashMap<Long, Integer> orderQuantities = new HashMap<>();
    public static Date creationDate = new Date(2022, 10, 31, 0, 0);

    public UUID warehouseId;
    public UUID storeId;

    @Before
    public void createTest(){
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
        Assertions.assertEquals(newOrder.getStatus(), Order.CREATED);

        // Tests the order quantities, ensures the keys are the same and actual quantities
        HashMap<Long, Integer> givenOrderQuantities = newOrder.getOrderQuantities();
        for(Long key: givenOrderQuantities.keySet()){
            Assertions.assertTrue(orderQuantities.containsKey(key));
            Assertions.assertEquals(orderQuantities.get(key), givenOrderQuantities.get(key));
        }

        // Tests the dates, it doesn't test the fulfilled date as the ordered hasn't been fulfilled yet
        // this is tested in the setters
        Assertions.assertEquals(creationDate, newOrder.getTimestamps().get(Order.CREATED));

    }

    @Test
    public void settersTest(){
        // Tests the setStatus
        newOrder.setStatus(Order.FULFILLED);
        Assertions.assertEquals(newOrder.getStatus(), Order.FULFILLED);

        newOrder.setStatus(Order.DELAYED);
        Assertions.assertEquals(newOrder.getStatus(), Order.DELAYED);

        // Tests the order getting fulfilled
        Date fulfilledDate = new Date(2023, 10, 31, 0, 0);
        newOrder.fulfillOrder(fulfilledDate);
        Assertions.assertEquals(newOrder.getTimestamps().get(Order.FULFILLED), fulfilledDate);
        Assertions.assertEquals(newOrder.getStatus(), Order.FULFILLED);
    }
}
