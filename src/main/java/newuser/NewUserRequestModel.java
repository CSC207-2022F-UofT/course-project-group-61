package newuser;

import entities.Facility;
import entities.FacilityType;

import java.util.UUID;

public class NewUserRequestModel {

    private String username;
    private String password;
    private UUID facilityID;
    private FacilityType type;

    public NewUserRequestModel(String username, String password, UUID facilityID, FacilityType type){
        this.username = username;
        this.password = password;
        this.facilityID = facilityID;
        this.type = type;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UUID getFacilityID() {
        return facilityID;
    }

    public FacilityType getType() {
        return type;
    }
}
