package database;

import entities.Facility;
import entities.Order;

import java.util.HashMap;
import java.util.UUID;

public class FacilityDbGateway implements FacilityDb {

    private final String FACILITY_FILE_PATH = "data/facilities.ser";
    private final DBReadWriter db;

    public FacilityDbGateway() {
        this.db = new DBReadWriter(FACILITY_FILE_PATH);
    }

    @Override
    public HashMap<UUID, Facility> getAllFacilities() {
        try {
            return (HashMap<UUID, Facility>) db.read();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Facility getFacility(UUID id) {
        try {
            HashMap<UUID, Facility> facilities = getAllFacilities();
            return facilities.get(id);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateFacility(Facility facility) {
        try {
            HashMap<UUID, Facility> facilities = getAllFacilities();
            facilities.put(facility.getFacilityID(), facility);
            db.write(facilities);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
