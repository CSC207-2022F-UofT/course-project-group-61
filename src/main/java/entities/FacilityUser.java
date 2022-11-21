package entities;

import java.util.UUID;

public class FacilityUser extends User {

    private final UUID facilityID;
    private final FacilityType type;

    public FacilityUser(String username, String password, UUID facilityID, FacilityType type) {
        super(username, password);
        this.facilityID = facilityID;
        this.type = type;
    }

    public UUID getFacilityID() {
        return facilityID;
    }

    public FacilityType getType() {
        return type;
    }
}
