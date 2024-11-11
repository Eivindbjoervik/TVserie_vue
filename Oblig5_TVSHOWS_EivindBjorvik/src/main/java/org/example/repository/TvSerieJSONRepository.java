package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.model.Episode;
import org.example.model.TvSerie;
import org.example.model.WriteToFileThread;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class TvSerieJSONRepository implements TvSerieRepository{
    private String fileName;
    private List<TvSerie> tvSeries = new ArrayList<>();



    public TvSerieJSONRepository(String fileName) throws IOException {
        this.fileName = fileName;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File file = new File("src/tvshows_10.json");
        tvSeries = objectMapper.readValue(file, new TypeReference<List<TvSerie>>() {
        });
    }

    public TvSerieJSONRepository(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        tvSeries = objectMapper.readValue(file, new TypeReference<List<TvSerie>>(){});
    }


    public void readDataFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/tvshows_10.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        TypeReference<List<TvSerie>> typeReference = new TypeReference<List<TvSerie>>(){};
        List<TvSerie> tvSeries = mapper.readValue(file, typeReference);
        this.tvSeries.clear();
        this.tvSeries.addAll(tvSeries);
    }


    public void writeToFile(String fileName, ArrayList<TvSerie> tvSeries) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        new WriteToFileThread("src/surr.json").start();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), tvSeries);
    }
    @Override
    public ArrayList<TvSerie> getTVSerier() {
        return new ArrayList<>(tvSeries);
    }

    public Episode slettEpisode(String tvSerieTittel, int sesongNr, int episodeNr) {
        getTvSerie(tvSerieTittel).deleteEpisode(getEpisode(tvSerieTittel,sesongNr,episodeNr));
        try { writeToFile("src/surr.json", new ArrayList<>(tvSeries));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void opprettEpisode(String tvSerieTittel, Episode episode) throws IOException {
        TvSerie tvSerie = getTvSerie(tvSerieTittel);
        tvSerie.leggTilEpisode(episode);
        writeToFile("src/surr.json", new ArrayList<>(tvSeries));
    }



    public Episode updateEpisode(String tvSerieTittel, String tittel, int sesongNr, int episodeNr, String beskrivelse, int spilletid, LocalDate utgivelsesdato, String bildeUrl) throws IOException {
        TvSerie tvSerie = getTvSerie(tvSerieTittel);
        if (tvSerie != null) {
            Episode episode = tvSerie.finnEpisode(sesongNr, episodeNr);
            if (episode != null) {
                episode.setTittel(tittel);
                episode.setBeskrivelse(beskrivelse);
                episode.setSpilletid(spilletid);
                episode.setUtgivelsesdato(utgivelsesdato);
                episode.setBildeUrl(bildeUrl);
                writeToFile("src/surr.json", new ArrayList<>(tvSeries));

            }
        }
        return null;
    }



    @Override
    public TvSerie getTvSerie(String tvSerieTittel) {
        for (TvSerie tvSerie: tvSeries) {
            if (tvSerie.getTittel().equals(tvSerieTittel))
                return tvSerie;
        }

        return null;
    }

    @Override
    public ArrayList<Episode> getEpisoderISesong(String tvSerieTittel, int sesongNr) {
        return getTvSerie(tvSerieTittel).hentEpisoderISesong(sesongNr);
    }

    @Override
    public Episode getEpisode(String tvSerieTittel, int sesongNr, int episodeNr) {
        return getTvSerie(tvSerieTittel).getEpisode(sesongNr, episodeNr);
    }



}

