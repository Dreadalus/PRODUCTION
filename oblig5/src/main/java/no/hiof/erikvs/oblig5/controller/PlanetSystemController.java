package no.hiof.erikvs.oblig5.controller;

import io.javalin.http.Context;
import no.hiof.erikvs.oblig5.model.PlanetSystem;
import no.hiof.erikvs.oblig5.repository.UniverseRepository;

import java.util.ArrayList;


public class PlanetSystemController {
    private UniverseRepository universeRepository;

    public PlanetSystemController(UniverseRepository universeRepository) {
        this.universeRepository = universeRepository;
    }

    public void getAllPlanetSystems(Context context) {
        ArrayList<PlanetSystem> planetSystems = universeRepository.getAllPlanetSystems();

        context.json(planetSystems);
    }

    public void getPlanetSystem(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");

        context.json(universeRepository.getPlanetSystem(planetSystemName));
    }
}
