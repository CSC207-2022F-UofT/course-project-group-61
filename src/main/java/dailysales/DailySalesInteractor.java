package dailysales;

import database.FacilityDb;
import database.FacilityDbGateway;
import database.ProductDb;
import entities.Facility;
import entities.FacilityUser;
import entities.UserSession;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DailySalesInteractor implements DailySalesInputBoundary {

    private final DailySalesOutputBoundary presenter;
    private final FacilityDb facilityDb;
    private final ProductDb productDb;

    public DailySalesInteractor(DailySalesOutputBoundary presenter, FacilityDb facilityDb, ProductDb productDb) {
        this.presenter = presenter;
        this.facilityDb = facilityDb;
        this.productDb = productDb;
    }

    public DailySalesResponseModel inputDailySales(DailySalesRequestModel request) {
        UUID facID = ((FacilityUser) UserSession.getUserSession()).getFacilityID();
        Facility facility = facilityDb.getFacility(facID);

        for (long upc: request.getDailySales().keySet()) {
            facility.removeProduct(upc, request.getDailySales().get(upc));
        }

        facilityDb.updateFacility(facility);
        presenter.prepareSuccessView();
        return new DailySalesResponseModel();
    }

    @Override
    public boolean upcExists(Long upc) {
        return productDb.getProduct(upc) != null;
    }
    @Override
    public String getProductName(Long upc) {
        return productDb.getProduct(upc).getName();
    }

}
