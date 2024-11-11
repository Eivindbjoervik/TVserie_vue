package org.example.repository;

import org.example.model.Episode;
import org.example.model.TvSerie;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface TvSerieRepository {
    ArrayList<TvSerie> getTVSerier();

    TvSerie getTvSerie(String tvSerieId);

    ArrayList<Episode> getEpisoderISesong(String tvSerieTittel, int sesongNr);

    Episode getEpisode(String tvSerieTittel, int sesongNr, int episodeNr);

    void opprettEpisode(String tvSerieTittel, Episode episode) throws IOException;

    Episode updateEpisode(String tvSerieTittel, String tittel, int sesongNr, int episodeNr, String beskrivelse, int spilletid, LocalDate utgivelsesdato, String bildeUrl) throws IOException;
    Episode slettEpisode(String tvSerieTittel, int sesongNr, int episodeNr);

}
