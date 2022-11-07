package newfacility;

import database.FacilityDbGateway;
import entities.Facility;

import java.util.HashMap;
import java.util.UUID;

public class NewFacilityInteractor {

    FacilityDbGateway readWriter = new FacilityDbGateway();
    private HashMap<UUID, Facility> facilities = readWriter.getAllFacilities();
    private Facility newFacility;

    public NewFacilityInteractor(String name, String facType) {
        this.newFacility = new Facility(name, facType);
    }


}
