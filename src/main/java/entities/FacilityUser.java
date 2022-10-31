package entities;

public class FacilityUser extends User {

    private final int facilityID;
    private final FacilityType type;

    public FacilityUser(String username, String password, int facilityID, FacilityType type) {
        super(username, password);
        this.facilityID = facilityID;
        this.type = type;
    }

    public int getFacilityID() {
        return facilityID;
    }

    public FacilityType getType() {
        return type;
    }
}
