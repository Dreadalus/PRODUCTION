package no.hiof.erikvs.repository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import no.hiof.erikvs.model.Planet;
import no.hiof.erikvs.model.PlanetSystem;

public interface UniverseRepository {

    /**4-2.4 Define methods for getting data for planet systems for frontend**/


    ArrayList<PlanetSystem> getAllPlanetSystems();
    PlanetSystem getPlanetSystem(String planetSystemName);

    /**4-2.6 Define methods for getting data for planets for frontend**/
    ArrayList<Planet> getAllPlanets(String planetSystemName, String sortByParam);
    Planet getSinglePlanet(String planetSystemName, String planetName);

    /**5-2.3a**/

    Planet deletePlanet(String planetSystemName, String planetName) throws IOException;

    Planet createPlanet(String planetSystemName, String planetName, double radius, double mass, double SemiMajorAxis, double Eccentricity, double orbitalPeriod, String pictureUrl) throws IOException;

    Planet updatePlanet(String planetSystemName, String planetName, String PlanetNameNew, double radius, double mass, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl) throws IOException;

}
