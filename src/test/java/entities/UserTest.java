package entities;

import org.junit.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    public UUID facilityId;
    public final FacilityUser facilityUser;

    public UserTest() {
        facilityId = UUID.randomUUID();
        facilityUser = new FacilityUser("testUser1", "CSC207", facilityId, FacilityType.STORE);
    }

    @Test
    public void getFacilityUserInfo() {
        assertEquals("testUser1", facilityUser.getUsername());
        assertEquals("CSC207", facilityUser.getPassword());
        assertEquals(facilityId, facilityUser.getFacilityID());
        assertEquals(FacilityType.STORE, facilityUser.getType());
    }

    @Test
    public void setPassword() {
        facilityUser.setPassword("CSC209");
        assertEquals("CSC209", facilityUser.getPassword());
    }
}
