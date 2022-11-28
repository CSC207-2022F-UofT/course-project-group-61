package fulfillorder;

import database.FacilityDb;
import database.FacilityDbGateway;

import database.OrderDb;
import database.OrderDbGateway;
import entities.Facility;
import entities.Order;
import fulfill.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.Console;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class FulfillOrderTest {

    private static FulfillController controller;

    private static OrderDbGateway orderDb;
    private static FacilityDbGateway facilityDb;

    private static Facility store;
    private static Facility warehouse;

    @Before
    public void setup(){
        controller = new FulfillController(new FulfillPresenter(new FulfillViewModel()));

        facilityDb = new FacilityDbGateway();
        facilityDb.fileReset();

        store = createStore(1L, 0, 2L, 0, "Store1");
        warehouse = createWarehouse(1L, 20, 2L, 30, "Warehouse1");

        facilityDb.updateFacility(store);
        facilityDb.updateFacility(warehouse);

        orderDb = new OrderDbGateway();
        orderDb.fileReset();
    }

    @Test
    public void TestAttemptingFulfillOrder(){
        Order order = createOrder(1L, 10, 2L, 20, "Tester1", warehouse.getFacilityID(), store.getFacilityID());
        orderDb.updateOrder(order);

        FulfillResponseModel output = controller.attemptFulfill(store.getFacilityID(), warehouse.getFacilityID(), order.getId());

        Assertions.assertEquals(FulfillStatus.SUCCESS, output.getStatus());
        Assertions.assertNull(output.getOutOfStockItems());

        // Tests the order has been properly marked as fulfilled
        Order newOrder = orderDb.getOrder(order.getId());

        Assertions.assertEquals(Order.FULFILLED, newOrder.getStatus());
        Assertions.assertNotNull(newOrder.getTimestamps().get(Order.FULFILLED));

        // Tests the store and warehouse's inventories have been properly updated
        Facility newStore = facilityDb.getFacility(store.getFacilityID());
        Facility newWarehouse = facilityDb.getFacility(warehouse.getFacilityID());

        Assertions.assertEquals(10, newStore.getUPCQuantity(1L));
        Assertions.assertEquals(20, newStore.getUPCQuantity(2L));

        Assertions.assertEquals(10, newWarehouse.getUPCQuantity(1L));
        Assertions.assertEquals(10, newWarehouse.getUPCQuantity(2L));
    }

    @Test
    public void TestConfirmFulfillOrder(){
        // Tests unfillable order
        Order order = createOrder(1L, 30, 2L, 40, "Tester2", warehouse.getFacilityID(), store.getFacilityID());
        orderDb.updateOrder(order);

        FulfillResponseModel firstOutput = controller.attemptFulfill(store.getFacilityID(), warehouse.getFacilityID(), order.getId());

        Assertions.assertEquals(FulfillStatus.OUT_OF_STOCK, firstOutput.getStatus());
        HashMap<Long, Boolean> expectedOutofStock = new HashMap<>();
        expectedOutofStock.put(1L, true);
        expectedOutofStock.put(2L, true);
        Assertions.assertEquals(expectedOutofStock, firstOutput.getOutOfStockItems());

        // Tests the store and warehouse's inventories have not been updated
        Facility newStore = facilityDb.getFacility(store.getFacilityID());
        Facility newWarehouse = facilityDb.getFacility(warehouse.getFacilityID());

        Assertions.assertEquals(0, newStore.getUPCQuantity(1L));
        Assertions.assertEquals(0, newStore.getUPCQuantity(2L));

        Assertions.assertEquals(20, newWarehouse.getUPCQuantity(1L));
        Assertions.assertEquals(30, newWarehouse.getUPCQuantity(2L));

        // Tests the order has not been updated
        Order newOrder = orderDb.getOrder(order.getId());

        Assertions.assertEquals(Order.CREATED, newOrder.getStatus());

        // Tests confirming fulfillment
        FulfillResponseModel secondOutput = controller.confirmFulfill(store.getFacilityID(), warehouse.getFacilityID(), order.getId());

        Assertions.assertEquals(FulfillStatus.SUCCESS, secondOutput.getStatus());
        Assertions.assertNull(secondOutput.getOutOfStockItems());

        // Tests the order has been properly marked as fulfilled
        newOrder = orderDb.getOrder(order.getId());

        Assertions.assertEquals(Order.FULFILLED, newOrder.getStatus());
        Assertions.assertNotNull(newOrder.getTimestamps().get(Order.FULFILLED));

        // Tests the store and warehouse's inventories have been properly updated
        newStore = facilityDb.getFacility(store.getFacilityID());
        newWarehouse = facilityDb.getFacility(warehouse.getFacilityID());

        Assertions.assertEquals(20, newStore.getUPCQuantity(1L));
        Assertions.assertEquals(30, newStore.getUPCQuantity(2L));

        Assertions.assertEquals(0, newWarehouse.getUPCQuantity(1L));
        Assertions.assertEquals(0, newWarehouse.getUPCQuantity(2L));

    }

    private Facility createWarehouse(long UPC1, int stock1, long UPC2, int stock2, String name){
        Facility warehouse = new Facility(name, "WAREHOUSE");
        warehouse.addProduct(UPC1, stock1);
        warehouse.addProduct(UPC2, stock2);
        return warehouse;
    }

    private Facility createStore(long UPC1, int stock1, long UPC2, int stock2, String name){
        Facility store = new Facility(name, "STORE");
        store.addProduct(UPC1, stock1);
        store.addProduct(UPC2, stock2);
        return store;
    }

    private Order createOrder(long UPC1, int stock1, long UPC2, int stock2, String user, UUID warehouseID, UUID storeID){
        HashMap<Long, Integer> orderQuantities = new HashMap<>();
        orderQuantities.put(UPC1, stock1);
        orderQuantities.put(UPC2, stock2);
        return new Order(warehouseID, storeID, user, orderQuantities, new Date());
    }

}
