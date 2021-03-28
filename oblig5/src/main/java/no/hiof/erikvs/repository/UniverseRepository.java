package no.hiof.erikvs.repository;
import java.util.ArrayList;
import java.util.HashMap;

import no.hiof.erikvs.model.Planet;
import no.hiof.erikvs.model.PlanetSystem;

public interface UniverseRepository {

    /** 2.4 Define methods for getting data for planet systems for frontend**/

    ArrayList<PlanetSystem> getAllPlanetSystems();
    PlanetSystem getPlanetSystem(String planetSystemName);

    /**2.6 Define methods for getting data for planets for frontend**/
    ArrayList<Planet> getAllPlanets(String planetSystemName, String sortByParam);
    Planet getSinglePlanet(String planetSystemName, String planetName);



}
