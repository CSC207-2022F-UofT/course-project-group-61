package entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

public class Facility implements Serializable {
    private final String name;
    private final UUID facilityID;
    private HashMap<Long, Integer> inventory;
    private FacilityType facilityType;

    public Facility(String name, FacilityType facType) {
        this.name = name;
        this.facilityID = UUID.randomUUID();
        this.inventory = new HashMap<>();
        this.facilityType = facType;
    }

    public String getName() {
        return this.name;
    }

    public UUID getFacilityID() {
        return this.facilityID;
    }

    public FacilityType getFacilityType() {
        return this.facilityType;
    }

    public Integer getUPCQuantity(Long upc) {
        try {
            return this.inventory.get(upc);
        } catch(NullPointerException e) {
            return null;
        }

    }

    public void addProduct(Long upc, int quantity) {
        try {
            this.inventory.put(upc, this.inventory.get(upc) + quantity);
        } catch(NullPointerException e) {
            this.inventory.put(upc, quantity);
        }
    }

    public void removeProduct(Long upc, int quantity) {
        /*this.inventory.put(upc, this.inventory.get(upc) - quantity);*/

        try {
            this.inventory.put(upc, this.inventory.get(upc) - quantity);
        } catch(NullPointerException e) {
            this.inventory.put(upc, -quantity);
        }
    }

    public HashMap<Long, Integer> getInventory(){
        return inventory;
    }
}
