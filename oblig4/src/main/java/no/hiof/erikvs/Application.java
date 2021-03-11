package no.hiof.erikvs;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.rendering.vue.VueComponent;
import no.hiof.erikvs.controller.PlanetSystemController;
import no.hiof.erikvs.repository.UniverseDataRepository;
import org.jetbrains.annotations.NotNull; //@NotNull lets you know if the context is null - is not needed to function

public class Application {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start();

     /*   //TODO: Task 2.2 Hello World
     app.get("/", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception { //@NotNull lets you know if the context is null - is not needed to function
                ctx.result("Hello World in Javalin!");
            }
        });*/

        app.config.enableWebjars();

        UniverseDataRepository universeRepository = new UniverseDataRepository();
        PlanetSystemController planetSystemController = new PlanetSystemController(universeRepository);



        app.get("/planet-system", new VueComponent("planet-system-overview")); //ID REF 1/3
        //TODO:
        app.get("/api/planet-system/:planet-system-overview", new Handler() { // ID REF 2/3
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetSystemController.getAllPlanetSystems(ctx);


            }
        });

        //TODO: individual planet
        app.get("/planet-system/:planet-system-id", new VueComponent("planet-system-detail")); //ID REF 1/3

        app.get("/api/planet-system/:planet-system-id", new Handler() { // ID REF 2/3
            @Override
            public void handle(@NotNull Context ctx) throws Exception {
                planetSystemController.getPlanetSystem(ctx);


            }
        });


        //TODO: 2.6

        // app.get("/planet-system/:planet-system-id", new VueComponent("system-detail"));

       // app.get("/planet-system/:planet-id", new VueComponent("planet-detail"));


    }
}
