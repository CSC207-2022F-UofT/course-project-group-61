package newfacility;

import database.FacilityDb;
import entities.Facility;

import java.util.*;

public class NewFacilityInteractor implements NewFacilityInputBoundary{

    private final FacilityDb facilityDb;
    NewFacilityOutputBoundary presenter;
    public NewFacilityInteractor(NewFacilityOutputBoundary presenter, FacilityDb facilityDb) {
        this.presenter = presenter;
        this.facilityDb = facilityDb;
    }

    public NewFacilityResponseModel addNewFacility(NewFacilityRequestModel request) {
        Facility newFacility = new Facility(request.getName(), request.getFacType());
        HashMap<UUID, Facility> facilities = facilityDb.getAllFacilities();
        NewFacilityResponseModel response;
        boolean repeatName = false;
        for (Facility facility : facilities.values()) {
            if (Objects.equals(facility.getName(), newFacility.getName())) {
                repeatName = true;
                break;
            }
        }
        if (repeatName) {
            response = new NewFacilityResponseModel(null, FailReason.REPEAT_NAME);
            presenter.prepareFailView(response);
        } else {
            facilityDb.updateFacility(newFacility);
            List<Object> responseList = new ArrayList<>();
            responseList.add(newFacility.getName());
            responseList.add(newFacility.getFacilityID());
            responseList.add(newFacility.getFacilityType());
            response = new NewFacilityResponseModel(responseList, null);
            presenter.prepareSuccessView(response);
        }
        return response;
    }

    @Override
    public void returnToMainMenu() {
        presenter.returnToMainMenu();
    }

}
