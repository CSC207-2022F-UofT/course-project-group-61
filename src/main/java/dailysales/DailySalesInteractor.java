package dailysales;

import database.FacilityDbGateway;
import entities.Facility;

import java.util.HashMap;
import java.util.UUID;

public class DailySalesInteractor {

    private final FacilityDbGateway readWriter = new FacilityDbGateway();

    public DailySalesInteractor() {

    }

    public void updateDailySales(UUID facID, HashMap<Long, Integer> dailySales) {
        Facility facility = readWriter.getFacility(facID);

        for(long upc: dailySales.keySet()) {
            facility.removeProduct(upc, dailySales.get(upc));

        }

        readWriter.updateFacility(facility);

    }



}
