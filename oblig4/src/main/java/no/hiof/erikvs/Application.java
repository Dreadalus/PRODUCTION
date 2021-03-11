package no.hiof.erikvs;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull; //@NotNull lets you know if the context is null - is not needed to function

public class Application {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start();

        //TODO: Task 2.2 Hello World
       /* app.get("/", new Handler() {
            @Override
            public void handle(@NotNull Context ctx) throws Exception { //@NotNull lets you know if the context is null - is not needed to function
                ctx.result("Hello World in Javalin!");
            }
        });*/
    }
}
