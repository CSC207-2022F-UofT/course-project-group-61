package entities;

import java.util.HashMap;
import java.util.UUID;

public class Facility {
    private final String name;
    private final UUID facilityID;
    private HashMap<Long, Integer> inventory;
    private String facilityType;

    public Facility(String name, String facType) {
        this.name = name;
        this.facilityID = UUID.randomUUID();
        this.inventory = new HashMap<Long, Integer>();
        this.facilityType = facType;
    }

    public Facility(String name, HashMap<Long, Integer> inventory, String facType) {
        this.name = name;
        this.facilityID = UUID.randomUUID();
        this.inventory = inventory;
        this.facilityType = facType;
    }

    public String getName() {
        return this.name;
    }

    public UUID getFacilityID() {
        return this.facilityID;
    }

    public String getFacilityType() {
        return this.facilityType;
    }

    public int getUPCQuantity(Long upc) {
        return this.inventory.get(upc);
    }

    public void addProduct(Long upc, int quantity) {
        this.inventory.put(upc, this.inventory.get(upc) + quantity);
    }

    public void removeProduct(Long upc, int quantity) {
        this.inventory.put(upc, this.inventory.get(upc) - quantity);
    }
}
