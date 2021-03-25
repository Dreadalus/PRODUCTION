package no.hiof.erikvs.controller;

import io.javalin.http.Context;
import no.hiof.erikvs.model.PlanetSystem;
import no.hiof.erikvs.repository.UniverseRepository;
import java.util.ArrayList;
import no.hiof.erikvs.model.Planet;

public class PlanetSystemController {
    private UniverseRepository universeRepository;

    public PlanetSystemController(UniverseRepository universeRepository) {
        this.universeRepository = universeRepository;
    }

    // Controller for requests made regarding overview of planet systems - my root
    public void getAllPlanetSystems(Context context){
        ArrayList<PlanetSystem> Galaxy = universeRepository.getAllPlanetSystems();
        context.json(Galaxy);
    }

    // Controller for requests made regarding planet system
    public void getPlanetSystem(Context context){
        String planetSystemName = context.pathParam(":planet-system-id");
        PlanetSystem planetSystem = universeRepository.getPlanetSystem(planetSystemName);
        context.json(planetSystem);
    }

    // Controller for requests made regarding planets in a given planet system and sorting
    public void getAllPlanets(Context context) {
        String planetSystemName = context.pathParam(":planet-system-id");
        String sortByParam = context.queryParam("sort_by");
        ArrayList<Planet> planetList = universeRepository.getAllPlanets(planetSystemName, sortByParam);
        context.json(planetList);
    }

    // Controller for requests made regarding specific planet
    public void getSinglePlanet(Context context){
        String planetName = context.pathParam(":planet-id");
        String planetSystemName = context.pathParam(":planet-system-id");
        Planet planet = universeRepository.getSinglePlanet(planetSystemName, planetName);
        context.json(planet);
    }
}
