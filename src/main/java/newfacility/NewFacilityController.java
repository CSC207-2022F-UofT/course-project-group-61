package newfacility;


import entities.FacilityType;

public class NewFacilityController {

    private final NewFacilityInputBoundary inputBoundary;

    public NewFacilityController(NewFacilityInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    //returns the facility id mostly for testing purposes but also maybe that's what the user will be given upon
    //execution of the request
    public NewFacilityResponseModel newFacility(String name, FacilityType facType) {
        NewFacilityRequestModel request = new NewFacilityRequestModel(name, facType);
        return inputBoundary.addNewFacility(request);
    }

    public void returnToMainMenu() {
        inputBoundary.returnToMainMenu();
    }
}
