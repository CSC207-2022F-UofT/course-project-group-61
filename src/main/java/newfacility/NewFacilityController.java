package newfacility;


import java.util.UUID;

public class NewFacilityController {

    private final NewFacilityInteractor newFacilityInteractor;

    public NewFacilityController() {
        this.newFacilityInteractor = new NewFacilityInteractor();
    }

    //returns the facility id mostly for testing purposes but also maybe that's what the user will be given upon
    //execution of the request
    public UUID newFacility(String name, String facType) {
        return this.newFacilityInteractor.addNewFacility(name, facType);
        //TODO: return logic for presenter
    }
}
