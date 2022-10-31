package entities;

import java.util.HashMap;

public class Facility {
    private final String name;
    private final int facilityID;
    private HashMap<Integer, Integer> inventory;
    private String facilityType;

    public Facility(String name, int facID, HashMap<Integer, Integer> inventory, String facType) {
        this.name = name;
        this.facilityID = facID;
        this.inventory = inventory;
        this.facilityType = facType;
    }

    public String getName() {
        return this.name;
    }

    public int getFacilityID() {
        return this.facilityID;
    }

    public String getFacilityType() {
        return this.facilityType;
    }

    public int getUPCQuantity(int upc) {
        return this.inventory.get(upc);
    }

    public void addProduct(int upc, int quantity) {
        this.inventory.put(upc, this.inventory.get(upc) + quantity);
    }

    public void removeProduct(int upc, int quantity) {
        this.inventory.put(upc, this.inventory.get(upc) - quantity);
    }
}
