package newfacility;

import entities.FacilityType;

import java.util.UUID;

public class NewFacilityRequestModel {

    private String name;
    private FacilityType facType;

    public NewFacilityRequestModel(String name, FacilityType facilityType) {
        this.name = name;
        this.facType = facilityType;
    }

    public String getName() {
        return name;
    }

    public FacilityType getFacType() {
        return facType;
    }
}
