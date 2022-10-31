package entities;


import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

public class FacilityTest {

    public static HashMap<Integer, Integer> testMap = new HashMap<>();

    public static Facility facility;

    @Before
    public void setup() {
        testMap.put(0001, 13);
        facility = new Facility("fac1", 13, testMap, "Store");
    }

    @Test
    public void getFacilityInfo() {
        assertEquals(facility.getName(), "fac1");
        assertEquals(facility.getFacilityID(), 13);
        assertEquals(facility.getFacilityType(), "Store");
        assertEquals(facility.getUPCQuantity(0001), 13);
    }

    @Test
    public void adjustInventory() {
        facility.addProduct(0001, 3);
        assertEquals(facility.getUPCQuantity(0001), 16);
        facility.removeProduct(0001, 7);
        assertEquals(facility.getUPCQuantity(0001), 9);
    }

}
