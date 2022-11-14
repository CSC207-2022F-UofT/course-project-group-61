package entities;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.UUID;
public class UserTest {

    private final UUID facID = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
    // UUID has some special rule to generate a UUID (required characters or length?)
    // This UUID (from https://www.tutorialspoint.com/java/util/uuid_fromstring.htm) works in the fromString method.
    public final FacilityUser facilityUser = new FacilityUser("testUser1", "CSC207", facID, FacilityType.STORE);

    @Test
    public void getFacilityUserInfo() {
        assertEquals("testUser1", facilityUser.getUsername());
        assertEquals("CSC207", facilityUser.getPassword());
        assertEquals(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"), facilityUser.getFacilityID());
        assertEquals(FacilityType.STORE, facilityUser.getType());
    }

    @Test
    public void setPassword() {
        facilityUser.setPassword("CSC209");
        assertEquals("CSC209", facilityUser.getPassword());
    }
}
