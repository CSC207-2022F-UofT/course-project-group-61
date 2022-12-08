package newfacility;

import adminmainmenu.AdminMainMenuViewModel;
import database.FacilityDbGateway;
import entities.Facility;
import entities.FacilityType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
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

    private NewFacilityController newFacilityController;
    private FacilityDbGateway facilityDbGateway;
    private UUID newFacilityID;

    public boolean checkAttributes(Facility facility, String expName, UUID expFacID, FacilityType expFacType, Long sampUPC, Integer expUPCQuant) {
        return Objects.equals(facility.getName(), expName) & Objects.equals(facility.getFacilityID(), expFacID) &
                Objects.equals(facility.getFacilityType(), expFacType) & facility.getUPCQuantity(sampUPC) == expUPCQuant;
    }

    @Before
    public void setup() {
        facilityDbGateway = new FacilityDbGateway();
        facilityDbGateway.fileReset();
        this.newFacilityController = new NewFacilityController(new NewFacilityInteractor(new NewFacilityPresenter(new NewFacilityViewModel(), new AdminMainMenuViewModel()), facilityDbGateway));
        List<Object> facility = newFacilityController.newFacility("Store1", FacilityType.STORE).getInfoList();
        this.newFacilityID = (UUID) facility.get(1);
    }

    @Test
    public void testWriteToEmpty() {
        HashMap<UUID, Facility> facilities = facilityDbGateway.getAllFacilities();
        assertEquals(facilities.size(), 1);
        assertTrue(checkAttributes(facilities.get(this.newFacilityID), "Store1", this.newFacilityID, FacilityType.STORE,
                123456789123L, 0));
        facilityDbGateway.fileReset();
    }

    @Test
    public void testWritetoNonEmpty() {
        UUID secondFacilityID = (UUID) newFacilityController.newFacility("Warehouse1", FacilityType.WAREHOUSE).getInfoList().get(1);
        HashMap<UUID, Facility> facilities = facilityDbGateway.getAllFacilities();
        assertEquals(facilities.size(), 2);
        assertTrue(checkAttributes(facilities.get(this.newFacilityID), "Store1", this.newFacilityID, FacilityType.STORE,
                123456789123L, 0));
        assertTrue(checkAttributes(facilities.get(secondFacilityID), "Warehouse1", secondFacilityID, FacilityType.WAREHOUSE,
                123456789123L, 0));
        facilityDbGateway.fileReset();
    }
}
