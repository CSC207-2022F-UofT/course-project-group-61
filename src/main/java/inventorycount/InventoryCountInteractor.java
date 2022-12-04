package inventorycount;


import database.FacilityDbGateway;
import entities.Facility;
import entities.FacilityUser;

import java.util.HashMap;
import java.util.UUID;

import entities.UserSession;

public class InventoryCountInteractor implements InventoryCountInputBoundary{

    private final FacilityDbGateway facilityDbGateway;
    private final InventoryCountOutputBoundary outputBoundary;



    public InventoryCountInteractor(InventoryCountOutputBoundary presenter, FacilityDbGateway facilityDbGateway){
        this.outputBoundary = presenter;
        this.facilityDbGateway = facilityDbGateway;

    }

    public void updateInventoryCount(InventoryCountRequestModel inventoryCountRequestModel){
        UUID facID = ((FacilityUser) UserSession.getUserSession()).getFacilityID();
        HashMap<Long, Integer> newInventoryCount = inventoryCountRequestModel.getInventoryCount();

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

    public InventoryCountResponseModel getCurrentInventoryCount(){
        UUID facID = ((FacilityUser) UserSession.getUserSession()).getFacilityID();

        Facility facility = facilityDbGateway.getFacility(facID);

        return new InventoryCountResponseModel(facility.getInventory());
    }

    public void returnToMainMenu(){
        outputBoundary.returnToMainMenu();}



}
