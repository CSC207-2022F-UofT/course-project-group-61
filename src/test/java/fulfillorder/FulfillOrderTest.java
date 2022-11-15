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

        store = new Facility("Store1", "STORE");
        store.addProduct(1L, 0);
        store.addProduct(2L, 0);

        warehouse = new Facility("Warehouse1", "WAREHOUSE");
        warehouse.addProduct(1L, 20);
        warehouse.addProduct(2L, 30);

        facilityDb.updateFacility(store);
        facilityDb.updateFacility(warehouse);

        HashMap<Long, Integer> orderQuantities = new HashMap<>();
        orderQuantities.put(1L, 10);
        orderQuantities.put(2L, 20);
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
        HashMap<Long, Boolean> expectedValues = new HashMap<>();
        expectedValues.put(1L, false);
        expectedValues.put(2L, false);

        HashMap<Long, Boolean> returnedValues = interactor.attemptUpdateInventory();

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
        HashMap<Long, Integer> newOrderQuantities = new HashMap<>();
        newOrderQuantities.put(1L, 50);
        newOrderQuantities.put(2L, 50);
        Date currentDate = new Date();
        Order currentOrder = new Order(warehouse.getFacilityID(), store.getFacilityID(), "User2", newOrderQuantities, currentDate);

        currentInteractor.addOrder(currentOrder);
        HashMap<Long, Boolean> returnedValue = currentInteractor.attemptUpdateInventory();

        HashMap<Long, Boolean> expectedValue = new HashMap<>();
        expectedValue.put(1L, true);
        expectedValue.put(2L, true);

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
