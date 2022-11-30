package dailysales;

import database.FacilityDb;
import database.FacilityDbGateway;
import entities.Facility;
import entities.FacilityUser;
import entities.UserSession;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DailySalesInteractor implements DailySalesInputBoundary {

    private final FacilityDb facilityDb;

    public DailySalesInteractor(FacilityDb facilityDb) {
        this.facilityDb = facilityDb;
    }

    public DailySalesResponseModel inputDailySales(DailySalesRequestModel request) {
        UUID facID = ((FacilityUser) UserSession.getUserSession()).getFacilityID();
        Facility facility = facilityDb.getFacility(facID);

        for (long upc: request.getDailySales().keySet()) {
            facility.removeProduct(upc, request.getDailySales().get(upc));
        }

        facilityDb.updateFacility(facility);
        //TODO
        return new DailySalesResponseModel();
    }



}
