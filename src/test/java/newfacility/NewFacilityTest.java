package newfacility;

import database.FacilityDbGateway;
import entities.Facility;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
Things to test:
- read/write to database, all data maintained; no extra data included
- test can write to empty
- test can write to non-empty
- test repeat facilityID
 */
public class NewFacilityTest {

    private UUID newFacilityID;

    public boolean checkAttributes(Facility facility, String expName, UUID expFacID, String expFacType, Long sampUPC, int expUPCQuant) {
        return Objects.equals(facility.getName(), expName) & Objects.equals(facility.getFacilityID(), expFacID) &
                Objects.equals(facility.getFacilityType(), expFacType) & facility.getUPCQuantity(sampUPC) == expUPCQuant;
    }

    @Before
    public void setup() {
        NewFacilityController newFacilityController = new NewFacilityController("Store1", "Store");
        this.newFacilityID = newFacilityController.newFacility();
    }

    @Test
    public void testWriteToEmpty() {
        FacilityDbGateway facilityDbGateway = new FacilityDbGateway();
        HashMap<UUID, Facility> facilities = facilityDbGateway.getAllFacilities();


        assertEquals(facilities.size(), 1);
        assertTrue(checkAttributes(facilities.get(this.newFacilityID), "Store1", this.newFacilityID, "Store",
                123456789123L, -1));

    }
}
