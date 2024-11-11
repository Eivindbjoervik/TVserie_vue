package org.example.model;

import org.example.controllers.EpisodeController;
import org.example.controllers.TvSerieController;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.vue.VueComponent;
import org.example.repository.TvSerieCSVRepository;
import org.example.repository.TvSerieDataRepository;
import org.example.repository.TvSerieJSONRepository;
import org.example.repository.TvSerieRepository;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.enableWebjars();
            config.vue.vueAppName = "app";
        }).start(1337);

        app.before("/", ctx -> ctx.redirect("/tvserie"));

        app.get("/tvserie", new VueComponent("tvserie-overview"));
        app.get("/tvserie/{tvserie-id}/createepisode", new VueComponent("episode-create"));
        app.get("/tvserie/{tvserie-id}/sesong/{sesong-nr}", new VueComponent("tvserie-detail"));
        app.get("/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}", new VueComponent("episode-detail"));
        app.get("/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}/updateepisode", new VueComponent("episode-update"));



        TvSerieJSONRepository tvSerieRepository = new TvSerieJSONRepository("src/tvshows_10.json");
        TvSerieController tvSerieController = new TvSerieController(tvSerieRepository);
        EpisodeController episodeController = new EpisodeController(tvSerieRepository);



        app.get("api/tvserie", new Handler() {
            @Override
            public void handle(Context ctx) throws Exception {
                tvSerieController.getAlleTvSerier(ctx);
            }
        });


        app.get("api/tvserie/{tvserie-id}", tvSerieController::getTVSerie);
        app.get("api/tvserie/{tvserie-id}/sesong/{sesong-nr}", episodeController::getEpisoderISesong);
        app.get("api/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}", episodeController::getEpisode);
        app.get("/api/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}/deleteepisode", episodeController::slettEpisode);
        app.post("/api/tvserie/{tvserie-id}/createepisode", episodeController::createEpisode);
        app.post("/api/tvserie/{tvserie-id}/sesong/{sesong-nr}/episode/{episode-nr}/updateepisode", episodeController::oppdaterEpisode);
    }
}
