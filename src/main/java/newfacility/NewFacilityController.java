package newfacility;


import java.util.UUID;

public class NewFacilityController {

    private final NewFacilityInteractor newFacilityInteractor;

    public NewFacilityController(String name, String facType) {
        this.newFacilityInteractor = new NewFacilityInteractor(name, facType);
    }

    //returns the facility id mostly for testing purposes but also maybe that's what the user will be given upon
    //execution of the request
    public UUID newFacility() {
        return this.newFacilityInteractor.addNewFacility();
        //TODO: return logic for presenter
    }
}
