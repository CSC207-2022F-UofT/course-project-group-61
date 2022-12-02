package entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

/* Facility (either Store or Warehouse) that contains an inventory of Products. */
public class Facility implements Serializable {
    private final String name;
    private final UUID facilityID;
    private HashMap<Long, Integer> inventory;
    private final HashMap<Long, Integer> inventory;
    private final FacilityType facilityType;

    public Facility(String name, FacilityType facType) {
        this.name = name;
        this.facilityID = UUID.randomUUID(); //.randomUUID mathematically ensures a unique ID
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

    /* Returns quantity of product with given UPC in inventory, if product does not exist in inventory, returns -1. */
    public int getUPCQuantity(long upc) {
        try {
            return this.inventory.get(upc);
        } catch(NullPointerException e) {
            return 0;
        }

    }

    /* Adds quantity to product in inventory, if product doesn't exist, sets product quantity to quantity. */
    public void addProduct(Long upc, int quantity) {
        try {
            this.inventory.put(upc, this.inventory.get(upc) + quantity);
        } catch(NullPointerException e) {
            this.inventory.put(upc, quantity);
        }
    }

    /* Subtracts quantity from product in inventory, if product doesn't exist, sets product quantity to -quantity. */
    public void removeProduct(Long upc, int quantity) {
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
