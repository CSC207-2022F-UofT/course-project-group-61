package entities;


import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FacilityTest {


    public static Facility facility;

    @Before
    public void setup() {
        facility = new Facility("fac1", FacilityType.STORE);
        facility.addProduct(123456789123L, 13);
    }

    @Test
    public void getFacilityInfo() {
        assertEquals(facility.getName(), "fac1");
        assertEquals(facility.getFacilityType(), FacilityType.STORE);
        assertEquals(facility.getUPCQuantity(123456789123L), 13);
    }

    @Test
    public void adjustInventory() {
        facility.addProduct(123456789123L, 3);
        assertEquals(facility.getUPCQuantity(123456789123L), 16);
        facility.removeProduct(123456789123L, 7);
        assertEquals(facility.getUPCQuantity(123456789123L), 9);
    }

}
