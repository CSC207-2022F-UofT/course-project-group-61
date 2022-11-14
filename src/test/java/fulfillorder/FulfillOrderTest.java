package fulfillorder;

import database.FacilityDbGateway;

import entities.Facility;
import entities.Order;
import fulfill.FulfillController;
import fulfill.FulfillInteractor;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public class FulfillOrderTest {

    private static FulfillController controller;
    private static FulfillInteractor interactor;
    private static Order order;

    private static Facility store;
    private static Facility warehouse;

    @Before
    public void setup(){
        controller = new FulfillController();
        interactor = new FulfillInteractor();

        FacilityDbGateway facilityDb = new FacilityDbGateway();
        facilityDb.fileReset();

        HashMap<Integer, Integer> storeInventory = new HashMap<>();
        storeInventory.put(1, 0);
        storeInventory.put(2, 0);
        store = new Facility("Store1", storeInventory, "STORE");

        HashMap<Integer, Integer> warehouseInventory = new HashMap<>();
        warehouseInventory.put(1, 20);
        warehouseInventory.put(2, 30);
        warehouse = new Facility("Warehouse1", warehouseInventory, "WAREHOUSE");

        facilityDb.updateFacility(store);
        facilityDb.updateFacility(warehouse);

        HashMap<Integer, Integer> orderQuantities = new HashMap<>();
        orderQuantities.put(1, 10);
        orderQuantities.put(2, 20);
        Date creationDate = new Date();
        order = new Order(warehouse.getFacilityID(), store.getFacilityID(), "Tester1", orderQuantities, creationDate);
    }

    @Test
    public void TestAddingOrder() throws IOException {
        // Tests that the program will not throw any exceptions, if it does the test fails automatically
        controller.addOrder(order);
    }

    @Test
    public void TestAttemptingFulfillOrder(){
        interactor.addOrder(order);

        // Tests the interactor returns the correct value
        HashMap<Integer, Boolean> expectedValues = new HashMap<>();
        expectedValues.put(1, false);
        expectedValues.put(2, false);

        HashMap<Integer, Boolean> returnedValues = interactor.attemptUpdateInventory();

        Assertions.assertEquals(returnedValues, expectedValues);

        // Tests that the order was marked as fulfilled
        Date returnedDate = order.getTimestamps().get(Order.FULFILLED);
        Assertions.assertNotEquals(null, returnedDate);

        // Tests store and warehouse inventories were properly updated
        // Since the interactor updates the store in the database we must pull up the "currentStore" from the database
        FacilityDbGateway facilityDatabase = new FacilityDbGateway();
        Facility currentStore = facilityDatabase.getFacility(store.getFacilityID());
        Facility currentWarehouse = facilityDatabase.getFacility(warehouse.getFacilityID());

        Assertions.assertEquals(10, currentStore.getUPCQuantity(1));
        Assertions.assertEquals(20, currentStore.getUPCQuantity(2));
        Assertions.assertEquals(10, currentWarehouse.getUPCQuantity(1));
        Assertions.assertEquals(10, currentWarehouse.getUPCQuantity(2));
    }

    @Test
    public void TestConfirmFulfillOrder(){
        //  Creates a new interactor to try and make an order that cannot be fully fulfilled
        FulfillInteractor currentInteractor = new FulfillInteractor();

        // Creates the new order that has too many requested items, as in the warehouse cannot provide it.
        HashMap<Integer, Integer> newOrderQuantities = new HashMap<>();
        newOrderQuantities.put(1, 50);
        newOrderQuantities.put(2, 50);
        Date currentDate = new Date();
        Order currentOrder = new Order(warehouse.getFacilityID(), store.getFacilityID(), "User2", newOrderQuantities, currentDate);

        currentInteractor.addOrder(currentOrder);
        HashMap<Integer, Boolean> returnedValue = currentInteractor.attemptUpdateInventory();

        HashMap<Integer, Boolean> expectedValue = new HashMap<>();
        expectedValue.put(1, true);
        expectedValue.put(2, true);

        Assertions.assertEquals(expectedValue, returnedValue);

        currentInteractor.confirmUpdateInventory();

        // Tests that inventories were properly updated
        FacilityDbGateway facilityDatabase = new FacilityDbGateway();
        Facility currentStore = facilityDatabase.getFacility(store.getFacilityID());
        Facility currentWarehouse = facilityDatabase.getFacility(warehouse.getFacilityID());

        Assertions.assertEquals(20, currentStore.getUPCQuantity(1));
        Assertions.assertEquals(30, currentStore.getUPCQuantity(2));
        Assertions.assertEquals(0, currentWarehouse.getUPCQuantity(1));
        Assertions.assertEquals(0, currentWarehouse.getUPCQuantity(2));
    }

}
