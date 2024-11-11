package org.example.controllers;

import io.javalin.http.Context;
import org.example.model.Episode;
import org.example.repository.TvSerieJSONRepository;
import org.example.repository.TvSerieRepository;

import java.time.LocalDate;
import java.util.Objects;

public class TvSerieController {
    private TvSerieRepository tvSerieRepository;

    public TvSerieController(TvSerieRepository tvSerieRepository) {
        this.tvSerieRepository = tvSerieRepository;
    }

    public void getAlleTvSerier(Context ctx) {
        ctx.json(tvSerieRepository.getTVSerier());
    }

    public void getTVSerie(Context context) {
        String tvSerieId = context.pathParam("tvserie-id");

        context.json(tvSerieRepository.getTvSerie(tvSerieId));
    }



}
