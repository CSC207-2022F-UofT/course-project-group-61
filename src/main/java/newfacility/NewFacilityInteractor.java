package newfacility;

import database.FacilityDbGateway;
import entities.Facility;

import java.util.UUID;

public class NewFacilityInteractor {

    FacilityDbGateway readWriter = new FacilityDbGateway();
    private final Facility newFacility;

    public NewFacilityInteractor(String name, String facType) {
        this.newFacility = new Facility(name, facType);
    }

    public UUID addNewFacility() {
        this.readWriter.updateFacility(this.newFacility);
        return this.newFacility.getFacilityID();
    }

}
