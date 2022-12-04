package inventorycount;


import database.FacilityDbGateway;
import entities.Facility;
import entities.FacilityUser;

import java.util.HashMap;
import java.util.UUID;

import entities.UserSession;

public class InventoryCountInteractor {

    private final FacilityDbGateway facilityDbGateway;
    private final InventoryCountPresenter presenter;



    public InventoryCountInteractor(InventoryCountPresenter presenter, FacilityDbGateway facilityDbGateway){
        this.presenter = presenter;
        this.facilityDbGateway = facilityDbGateway;

    }

    public void updateInventoryCount(HashMap<Long, Integer> newInventoryCount){
        UUID facID = ((FacilityUser) UserSession.getUserSession()).getFacilityID();

        // pull facility entity
        Facility facility = facilityDbGateway.getFacility(facID);

        // update inventory
        for (Long upc : newInventoryCount.keySet()){

            int newCount = newInventoryCount.get(upc);

            int currentCount = facility.getUPCQuantity(upc);

            int countDelta = newCount - currentCount;

            facility.addProduct(upc, countDelta);

        }

        // save entity
        facilityDbGateway.updateFacility(facility);

    }

    public HashMap<Long, Integer> getCurrentInventoryCount(){
        UUID facID = ((FacilityUser) UserSession.getUserSession()).getFacilityID();

        Facility facility = facilityDbGateway.getFacility(facID);
        

        return facility.getInventory();
    }

    public void returnToMainMenu(){presenter.returnToMainMenu();}



}
