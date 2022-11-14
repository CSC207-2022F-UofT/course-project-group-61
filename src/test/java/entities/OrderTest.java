package entities;
import org.junit.*;
import org.junit.jupiter.api.Assertions;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;


public class OrderTest {
    public static Order newOrder;
    public static HashMap<Integer, Integer> orderQuantities = new HashMap<>();
    public static Date creationDate = new Date(2022, 10, 31, 0, 0);

    private UUID warehouseID;
    private UUID storeID;

    @Before
    public void createTest(){
        // Puts some basic stuff in the order and builds it
        orderQuantities.put(1, 10);
        orderQuantities.put(2, 120);

        warehouseID = UUID.randomUUID();
        storeID = UUID.randomUUID();

        newOrder = new Order(warehouseID, storeID, "tester", orderQuantities, creationDate);
    }

    @Test
    public void gettersTest(){
        // Tests all the basic getters
        Assertions.assertEquals(newOrder.getOrderingUser(), "tester");
        Assertions.assertEquals(newOrder.getStoreID(), storeID);
        Assertions.assertEquals(newOrder.getWarehouseID(), warehouseID);
        Assertions.assertEquals(newOrder.getStatus(), Order.CREATED);

        // Tests the order quantities, ensures the keys are the same and actual quantities
        HashMap<Integer, Integer> givenOrderQuantities = newOrder.getOrderQuantities();
        for(int key: givenOrderQuantities.keySet()){
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
