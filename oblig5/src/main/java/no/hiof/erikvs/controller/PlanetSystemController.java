package no.hiof.erikvs.controller;

import io.javalin.http.Context;
import no.hiof.erikvs.model.PlanetSystem;
import no.hiof.erikvs.repository.UniverseRepository;

import java.io.IOException;
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
    // Controller for request made to delete planet
    public void deletePlanet(Context context) throws IOException {
        String planetName = context.pathParam(":planet-id");
        String planetSystemName = context.pathParam(":planet-system-id");
        Planet planet = universeRepository.deletePlanet(planetSystemName, planetName);
        context.json(planet);
    }
    // Controller for request made to create new planet
    public void createPlanet(Context context) throws IOException {
        String planetName = context.formParam("name");
        Double radius = Double.parseDouble(context.formParam("radius"));
        Double mass = Double.parseDouble(context.formParam("mass"));
        Double SemiMajorAxis = Double.parseDouble(context.formParam("semiMajorAxis"));
        Double Eccentricity = Double.parseDouble(context.formParam("eccentricity"));
        Double orbitalPeriod = Double.parseDouble(context.formParam("orbitalPeriod"));
        String pictureUrl = context.formParam("pictureUrl");
        String planetSystemName = context.pathParam(":planet-system-id");
        Planet planet = universeRepository.createPlanet(planetSystemName, planetName, radius, mass, SemiMajorAxis, Eccentricity, orbitalPeriod, pictureUrl);
        context.json("");
    }

    public void updatePlanet(Context context) throws IOException{
        String planetNameNew = context.formParam("name");
        Double radius = Double.parseDouble(context.formParam("radius"));
        Double mass = Double.parseDouble(context.formParam("mass"));
        Double SemiMajorAxis = Double.parseDouble(context.formParam("semiMajorAxis"));
        Double Eccentricity = Double.parseDouble(context.formParam("eccentricity"));
        Double orbitalPeriod = Double.parseDouble(context.formParam("orbitalPeriod"));
        String pictureUrl = context.formParam("pictureUrl");
        String planetSystemName = context.pathParam(":planet-system-id");
        String planetName = context.pathParam("planet-id");
        Planet planet = universeRepository.updatePlanet(planetSystemName, planetName, planetNameNew, radius, mass, SemiMajorAxis, Eccentricity, orbitalPeriod, pictureUrl);
        context.json("");
    }
}
