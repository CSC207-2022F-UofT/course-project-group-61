package database;

import entities.Facility;
import entities.Order;

import java.io.EOFException;
import java.io.IOException;
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
        } catch (EOFException eof) {
            HashMap<UUID, Facility> tempMap = new HashMap<UUID, Facility>();
            try {
                this.db.write(tempMap);
                return (HashMap<UUID, Facility>) db.read();
            } catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
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
