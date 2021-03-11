package no.hiof.erikvs.repository;
import java.util.ArrayList;
import no.hiof.erikvs.model.PlanetSystem;

public interface UniverseRepository {

    ArrayList<PlanetSystem> getAllPlanetSystems();
    PlanetSystem getPlanetSystem(String planetSystemName);
}
