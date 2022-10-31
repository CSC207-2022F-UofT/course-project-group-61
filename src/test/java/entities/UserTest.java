package entities;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    public final FacilityUser facilityUser = new FacilityUser("testUser1", "CSC207", 1, FacilityType.STORE);

    @Test
    public void getFacilityUserInfo() {
        assertEquals("testUser1", facilityUser.getUsername());
        assertEquals("CSC207", facilityUser.getPassword());
        assertEquals(1, facilityUser.getFacilityID());
        assertEquals(FacilityType.STORE, facilityUser.getType());
    }

    @Test
    public void setPassword() {
        facilityUser.setPassword("CSC209");
        assertEquals("CSC209", facilityUser.getPassword());
    }
}
