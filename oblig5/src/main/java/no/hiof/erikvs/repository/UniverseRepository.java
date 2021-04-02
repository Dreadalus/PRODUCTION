package no.hiof.erikvs.repository;
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


    // I assume we know the system name since the option to create first appears when we are in a system
    Planet addPlanet(String planetName, double radius, double mass, double SemiMajorAxis, double Eccentricity, double orbitalPeriod, String pictureUrl);

    // I assume we know system name and planet name and all data pertaining to a given planet - Implementation would be a loop check.
    Planet editPlanet();

    // I assume we know system name and planet name and all data pertaining to a given planet - Implementation would be .remove(index which should be known)
    Planet deletePlanet();



  /*  planet name ;
    planet radius ;
    planet mass ;
    planet semiMajor ;
    planet eccentricity ;
    planet orbital ;
    planet pic ;*/



}
