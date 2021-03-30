package no.hiof.erikvs;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.rendering.vue.VueComponent;
import no.hiof.erikvs.controller.PlanetSystemController;
//import no.hiof.erikvs.repository.UniverseCSVRepository; //TODO: remember to have this when you run CSV
import no.hiof.erikvs.repository.UniverseJSONRepository;
import org.jetbrains.annotations.NotNull; //@NotNull lets you know if the context is null - is not needed to function

import java.io.File;
import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start();

    /**4-2.2 Hello World in Javalin**/
     /* app.get("/", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception { //@NotNull lets you know if the context is null - is not needed to function
                ctx.result("Hello World in Javalin!");
            }
        });*/

       app.config.enableWebjars();

        UniverseJSONRepository universeJSONRepository = new UniverseJSONRepository(new File("src/main/resources/planets_100.json")); // to create information
        PlanetSystemController planetSystemController = new PlanetSystemController(universeJSONRepository);

        /*UniverseCSVRepository universeCSVRepository = new UniverseCSVRepository(new File("src/main/resources/planets_100.csv"));
        PlanetSystemController planetSystemController = new PlanetSystemController(universeCSVRepository);*/

        // testing method 5-2.1d
      /*try {
            universeJSONRepository.writeToJSONFile(universeJSONRepository.planetSystemHashMap,"src/main/resources/planets_100.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(universeJSONRepository.planetSystemHashMap);*/


        /** All planet systems **/
        app.get("/", new VueComponent("planet-system-overview"));
        app.get("/api/planet-system/", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetSystemController.getAllPlanetSystems(ctx);
            }
        });

        /** A given planet system **/
        app.get("/planet-system/:planet-system-id", new VueComponent("planet-system-detail"));
        app.get("/api/planet-system/:planet-system-id", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetSystemController.getPlanetSystem(ctx);
            }
        });

        /** All planets **/
        app.get("/api/planet-system/:planet-system-id/planets", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetSystemController.getAllPlanets(ctx);

            }
        });

        /** A given planet **/
        app.get("/planet-system/:planet-system-id/planets/:planet-id", new VueComponent("planet-detail"));
        app.get("/api/planet-system/:planet-system-id/planets/:planet-id", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetSystemController.getSinglePlanet(ctx);
            }
        });
    }
}
