package newfacility;

public interface NewFacilityOutputBoundary {

    void prepareSuccessView(NewFacilityResponseModel model);

    void prepareFailView(NewFacilityResponseModel model);

    void returnToMainMenu();
}
