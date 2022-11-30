package newfacility;

import entities.FacilityType;

public class NewFacilityRequestModel {

    private final String name;
    private final FacilityType facType;

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
