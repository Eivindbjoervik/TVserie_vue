package org.example.controllers;

import io.javalin.http.Context;
import org.example.model.Episode;
import org.example.model.Produksjon;
import org.example.model.TvSerie;
import org.example.repository.TvSerieRepository;
import org.example.repository.TvSerieJSONRepository;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;

public class EpisodeController {
    private TvSerieJSONRepository tvSerieJSONRepository;

    public EpisodeController(TvSerieJSONRepository tvSerieJSONRepository) {
        this.tvSerieJSONRepository = tvSerieJSONRepository;
    }


    public void getEpisoderISesong(Context context) {
        String tvSerieTittel = context.pathParam("tvserie-id");
        String sesong = context.pathParam("sesong-nr");
        String sortering = context.queryParam("sortering");

        int sesongNr = sesong.isEmpty() ? 1 : Integer.parseInt(sesong);

        ArrayList<Episode> episoder = tvSerieJSONRepository.getEpisoderISesong(tvSerieTittel, sesongNr);

        if (sortering != null) {
            switch (sortering) {
                case "episodenr" -> Collections.sort(episoder);
                case "tittel" -> episoder.sort((e1, e2) -> e1.getTittel().compareTo(e2.getTittel()));
                case "spilletid" -> episoder.sort(Comparator.comparingInt(Produksjon::getSpilletid));
            }
        }

        context.json(episoder);
    }

    public void getEpisode(Context context) {
        String tvSerieTittel = context.pathParam("tvserie-id");
        String sesongNr = context.pathParam("sesong-nr");
        String episodeNr = context.pathParam("episode-nr");

        context.json(tvSerieJSONRepository.getEpisode(tvSerieTittel, Integer.parseInt(sesongNr), Integer.parseInt(episodeNr)));

    }

    public void slettEpisode(Context context) {
        String tvSerieTittel = context.pathParam("tvserie-id");
        String sesongNr = context.pathParam("sesong-nr");
        String episodeNr = context.pathParam("episode-nr");

        tvSerieJSONRepository.slettEpisode(tvSerieTittel, Integer.parseInt(sesongNr), Integer.parseInt(episodeNr));

        context.redirect("/tvserie/" + tvSerieTittel + "/sesong/" + sesongNr);

    }

    public void createEpisode(Context context) throws IOException {
        String tvSerieTittel = context.pathParam("tvserie-id");

        String tittel = context.formParam("tittel");
        int sesongNr = Integer.parseInt(context.formParam("sesongNummer"));
        int episodeNr = Integer.parseInt(context.formParam("episodeNummer"));
        String beskrivelse = context.formParam("beskrivelse");
        int spilletid = Integer.parseInt(context.formParam("spilletid"));
        LocalDate utgivelsesdato = LocalDate.parse(context.formParam("utgivelsesdato"));
        String bildeUrl = context.formParam("bildeUrl");

        Episode episode = new Episode(tittel, sesongNr, episodeNr, beskrivelse, spilletid, utgivelsesdato, bildeUrl);
        tvSerieJSONRepository.opprettEpisode(tvSerieTittel, episode);

        context.redirect("/tvserie/" + tvSerieTittel + "/sesong/" + sesongNr);
    }

    public void oppdaterEpisode(Context context) throws IOException {
        String tvSerieTittel = context.pathParam("tvserie-id");

        String tittel = context.formParam("tittel");
        int sesongNr = Integer.parseInt(context.formParam("sesongNummer"));
        int episodeNr = Integer.parseInt(context.formParam("episodeNummer"));
        String beskrivelse = context.formParam("beskrivelse");
        int spilletid = Integer.parseInt(context.formParam("spilletid"));
        LocalDate utgivelsesdato = LocalDate.parse(context.formParam("utgivelsesdato"));
        String bildeUrl = context.formParam("bildeUrl");

        tvSerieJSONRepository.updateEpisode(tvSerieTittel, tittel, sesongNr, episodeNr, beskrivelse, spilletid, utgivelsesdato, bildeUrl);

        context.redirect("/tvserie/" + tvSerieTittel + "/sesong/" + sesongNr + "/episode/" + episodeNr);
    }
}

