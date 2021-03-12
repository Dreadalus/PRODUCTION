package no.hiof.erikvs.repository;
import java.util.ArrayList;
import no.hiof.erikvs.model.PlanetSystem;

public interface UniverseRepository {

    /** 2.4 Define methods for getting data for frontend**/
    ArrayList<PlanetSystem> getAllPlanetSystems();
    PlanetSystem getPlanetSystem(String planetSystemName);
}
