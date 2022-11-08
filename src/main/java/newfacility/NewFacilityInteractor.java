package newfacility;

import database.FacilityDbGateway;
import entities.Facility;

import java.util.UUID;

public class NewFacilityInteractor {

    FacilityDbGateway readWriter = new FacilityDbGateway();
    public NewFacilityInteractor() {

    }

    public UUID addNewFacility(String name, String facType) {
        Facility newFacility = new Facility(name, facType);
        this.readWriter.updateFacility(newFacility);
        return newFacility.getFacilityID();
    }

}
