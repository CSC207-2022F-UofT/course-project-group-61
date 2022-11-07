package inventorycount;


import database.FacilityDb;
import database.FacilityDbGateway;
import entities.Facility;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class InventoryCountInteractor {

    private final FacilityDb facilityDb;
    private final UUID facID;

    public InventoryCountInteractor(UUID facID){

        this.facilityDb = new FacilityDbGateway();

        this.facID = facID;

    }

    public void updateInventoryCount(HashMap<Integer, Integer> newInventoryCount){
        // pull facility entity
        Facility facility = facilityDb.getFacility(facID);

        // update inventory
        for (int upc : newInventoryCount.keySet()){

            int newCount = newInventoryCount.get(upc);

            int currentCount = facility.getUPCQuantity(upc);

            int countDelta = newCount - currentCount;

            facility.addProduct(upc, countDelta);

        }

        // save entity
        facilityDb.updateFacility(facility);

    }

    public HashMap<Integer, Integer> getCurrentCount(){
        Facility facility = facilityDb.getFacility(facID);

        return facility.getInventory();
    }

}
