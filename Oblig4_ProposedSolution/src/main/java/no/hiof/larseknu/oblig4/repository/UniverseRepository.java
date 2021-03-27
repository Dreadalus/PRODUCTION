package no.hiof.larseknu.oblig4.repository;

import no.hiof.larseknu.oblig4.model.Planet;
import no.hiof.larseknu.oblig4.model.PlanetSystem;

import java.util.ArrayList;

public interface UniverseRepository {
    ArrayList<PlanetSystem> getAllPlanetSystems();
    PlanetSystem getPlanetSystem(String planetSystemName);

    ArrayList<Planet> getAllPlanets(String planetSystemName);
    Planet getPlanet(String planetSystemName, String planetName);
}
