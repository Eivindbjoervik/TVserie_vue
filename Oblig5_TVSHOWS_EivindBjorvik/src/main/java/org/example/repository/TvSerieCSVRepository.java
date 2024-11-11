package org.example.repository;
import org.example.model.Episode;
import org.example.model.Person;
import org.example.model.TvSerie;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class TvSerieCSVRepository implements TvSerieRepository{
    private String fileName;
    private Map<String, TvSerie> tvSeries;

    public TvSerieCSVRepository(String fileName) throws IOException {
        this(new File(fileName));
    }

    public TvSerieCSVRepository(File file) throws IOException {
        this.fileName = file.getName();
        this.tvSeries = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length != 15) {
                    continue;
                }

                String tvTittel = parts[0];
                TvSerie tvSerie = tvSeries.getOrDefault(tvTittel, new TvSerie(tvTittel));

                String tvBeskrivelse = parts[1];
                LocalDate tvUtgivelsesdato = LocalDate.parse(parts[2]);
                String tvBildeUrl = parts[3];
                tvSerie.setBeskrivelse(tvBeskrivelse);
                tvSerie.setUtgivelsesdato(tvUtgivelsesdato);
                tvSerie.setBildeUrl(tvBildeUrl);

                String epTittel = parts[4];
                String epBeskrivelse = parts[5];
                int epNummer = Integer.parseInt(parts[6]);
                int epSesongNummer = Integer.parseInt(parts[7]);
                int epSpilletid = Integer.parseInt(parts[8]);
                LocalDate epUtgivelsesdato = LocalDate.parse(parts[9]);
                String epBildeUrl = parts[10];
                Episode episode = new Episode(epTittel, epBeskrivelse, epNummer, epSesongNummer, epSpilletid, epUtgivelsesdato, epBildeUrl);
                tvSerie.leggTilEpisode(episode);

                String regFornavn = parts[11];
                String regEtternavn = parts[12];
                LocalDate regFodselsdato = LocalDate.parse(parts[13]);
                String regBildeUrl = null;
                Person regissor = new Person(regFornavn, regEtternavn, regFodselsdato, regBildeUrl);
                episode.setRegissor(regissor);

                tvSeries.put(tvTittel, tvSerie);

            }
        }
    }

    public void skrivTilFil(File file) throws IOException {
        try (PrintWriter writer = new PrintWriter(file)) {
            for (TvSerie tvSerie : tvSeries.values()) {
                writer.print(tvSerie.getTittel());
                writer.print(";");
                writer.print(tvSerie.getBeskrivelse());
                writer.print(";");
                writer.print(tvSerie.getUtgivelsesdato());
                writer.print(";");
                writer.print(tvSerie.getBildeUrl());
                writer.print(";");

                for (Episode episode : tvSerie.getEpisoder()) {
                    writer.print(episode.getTittel());
                    writer.print(";");
                    writer.print(episode.getBeskrivelse());
                    writer.print(";");
                    writer.print(episode.getEpisodeNummer());
                    writer.print(";");
                    writer.print(episode.getSesongNummer());
                    writer.print(";");
                    writer.print(episode.getSpilletid());
                    writer.print(";");
                    writer.print(episode.getUtgivelsesdato());
                    writer.print(";");
                    writer.print(episode.getBildeUrl());
                    writer.print(";");

                    Person regissor = episode.getRegissor();
                    if (regissor != null) {
                        writer.print(regissor.getFornavn());
                        writer.print(";");
                        writer.print(regissor.getEtternavn());
                        writer.print(";");
                        writer.print(regissor.getFodselsDato());
                        writer.print(";");
                    } else {
                        writer.print(";;;");
                    }
                }

                writer.println();
            }
        }
    }

    @Override
    public ArrayList<TvSerie> getTVSerier() {
        return new ArrayList<>(tvSeries.values());
    }

    @Override
    public TvSerie getTvSerie(String tvSerieTittel) {
        return tvSeries.get(tvSerieTittel);
    }

    @Override
    public ArrayList<Episode> getEpisoderISesong(String tvSerieTittel, int sesongNr) {
        TvSerie tvSerie = getTvSerie(tvSerieTittel);
        if (tvSerie != null) {
            return tvSerie.hentEpisoderISesong(sesongNr);
        } else {
            return null;
        }
    }

    @Override
    public Episode getEpisode(String tvSerieTittel, int sesongNr, int episodeNr) {
        TvSerie tvSerie = getTvSerie(tvSerieTittel);
        if (tvSerie != null) {
            return tvSerie.getEpisode(sesongNr, episodeNr);
        } else {
            return null;
        }}

    @Override
    public void opprettEpisode(String tvSerieTittel, Episode episode) {
    }

    @Override
    public Episode updateEpisode(String tvSerieTittel, String tittel, int sesongNr, int episodeNr, String beskrivelse, int spilletid, LocalDate utgivelsesdato, String bildeUrl) {
        return null;
    }

    @Override
    public Episode slettEpisode(String tvSerieTittel, int sesongNr, int episodeNr) {
        return null;
    }


}
