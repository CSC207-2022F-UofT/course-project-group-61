package database;

import entities.Facility;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class FacilityDbGateway implements FacilityDb {

    private final DBReadWriter db;

    public FacilityDbGateway() {
        String FACILITY_FILE_PATH = "data/facilities.ser";
        this.db = new DBReadWriter(FACILITY_FILE_PATH);
    }

    @Override
    public HashMap<UUID, Facility> getAllFacilities() {
        try {
            return (HashMap<UUID, Facility>) db.read();
        } catch (EOFException eof) {
            HashMap<UUID, Facility> tempMap = new HashMap<>();
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
        HashMap<UUID, Facility> facilities = getAllFacilities();
        return facilities.get(id);
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

    //for testing purposes
    public void fileReset() {
        try {
            HashMap<UUID, Facility> newHash = new HashMap<>();
            db.write(newHash);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
