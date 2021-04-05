package no.hiof.erikvs;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.rendering.vue.VueComponent;
import no.hiof.erikvs.controller.PlanetSystemController;
import no.hiof.erikvs.repository.UniverseCSVRepository;
import no.hiof.erikvs.repository.UniverseJSONRepository;
import org.jetbrains.annotations.NotNull; //@NotNull lets you know if the context is null - is not needed to function

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

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

        /** For running with JSONrepo **/
        UniverseJSONRepository universeJSONRepository = new UniverseJSONRepository(new File("src/main/resources/planets_100.json")); // to create information
        PlanetSystemController planetSystemController = new PlanetSystemController(universeJSONRepository);

        /** For thread functionality with JSON **/
        Thread universeJSONRepositoryThread = new Thread(universeJSONRepository);
        universeJSONRepositoryThread.start();

        /** For running with CSVrepo **/
        /* UniverseCSVRepository universeCSVRepository = new UniverseCSVRepository(new File("src/main/resources/planets_100.csv"));
        PlanetSystemController planetSystemController = new PlanetSystemController(universeCSVRepository);*/


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
        /** Create planet**/
        app.get("/planet-system/:planet-system-id/planets/create", new VueComponent("planet-create"));
        app.post("/api/planet-system/:planet-system-id/planets/create", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetSystemController.createPlanet(ctx);
                ctx.redirect("/planet-system/" + ctx.pathParam("planet-system-id"), 302); // javalin documentation under context, adding status code is good practice
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

        /** Update planet**/
        app.get("/planet-system/:planet-system-id/planets/:planet-id/update", new VueComponent("planet-update"));
        app.post("/api/planet-system/:planet-system-id/planets/:planet-id/update", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetSystemController.updatePlanet(ctx);
                ctx.redirect("/planet-system/" + ctx.pathParam("planet-system-id"), 302);
            }
        });

        /** Delete planet**/
        app.get("/planet-system/:planet-system-id/planets/:planet-id/delete", new VueComponent("planet-detail"));
        app.get("/api/planet-system/:planet-system-id/planets/:planet-id/delete", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetSystemController.deletePlanet(ctx);
                ctx.redirect("/planet-system/" + ctx.pathParam("planet-system-id"), 302);
            }
        });
    }
}
