package database;

import entities.Facility;

import java.util.HashMap;
import java.util.UUID;

public interface FacilityDb {

    HashMap<UUID, Facility> getAllFacilities();

    Facility getFacility(UUID id);

    boolean updateFacility(Facility facility);
}
