package no.hiof.erikvs.oblig5.controller;

import io.javalin.http.Context;
import no.hiof.erikvs.oblig5.repository.UniverseRepository;
import no.hiof.erikvs.oblig5.model.Planet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PlanetController {
    private UniverseRepository universeRepository;

    public PlanetController(UniverseRepository universeRepository) {
        this.universeRepository = universeRepository;
    }

    public void getPlanets(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        String sortBy = context.queryParam("sort_by");

        ArrayList<Planet> planets = universeRepository.getAllPlanets(planetSystemName);

       /* if (sortBy.equals("name"))
            Collections.sort(planets);*/

        switch (sortBy) {
            case "name" -> Collections.sort(planets);
            case "mass" -> Collections.sort(planets, new Comparator<Planet>() {
                @Override
                public int compare(Planet planet1, Planet planet2) {
                    if (planet1.getMass() > planet2.getMass())
                        return 1;
                    else if(planet1.getMass() < planet2.getMass())
                        return -1;

                    return 0;
                }
            });
            case "radius" -> Collections.sort(planets, Comparator.comparing(Planet::getRadius));
        }

        context.json(planets);
    }

    public void getPlanet(Context context) {
        String planetSystemName = context.pathParam("planet-system-id");
        String planetName = context.pathParam("planet-id");

        context.json(universeRepository.getPlanet(planetSystemName, planetName));

    }
}
