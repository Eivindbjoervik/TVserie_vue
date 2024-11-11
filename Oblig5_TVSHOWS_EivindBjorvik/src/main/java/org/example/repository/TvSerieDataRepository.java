package org.example.repository;

import org.example.model.Episode;
import org.example.model.Person;
import org.example.model.Rolle;
import org.example.model.TvSerie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TvSerieDataRepository implements TvSerieRepository {
    private final ArrayList<TvSerie> tvSerier = new ArrayList<>();

    public TvSerieDataRepository() {
        generateTvSerieStrangerThings();
        generateTvSerieDaysOfOurLives();
    }

    @Override
    public ArrayList<TvSerie> getTVSerier() {
        return new ArrayList<>(tvSerier);
    }

    @Override
    public TvSerie getTvSerie(String tvSerieTittel) {
        for (TvSerie tvSerie: tvSerier) {
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


    private void generateTvSerieDaysOfOurLives() {
        TvSerie daysOfOurLives = new TvSerie("Days of our Lives", "Time goes by...", LocalDate.of(1965, 11, 8), "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7Zm7epVFEovMEVLpM6FvrjhaNXn.jpg");

        Person regisor = new Person("Herb", "Stein");

        LocalDate sisteDato = LocalDate.of(1965, 11, 8);

        int sesongNummer = 1;
        int episodeNummer = 1;
        int innevaerendeAar = sisteDato.getYear();

        // Går gjennom å lager 13518 episoder
        for (int e = 1; e <= 13518; e++){
            // Lager en ny episode
            //daysOfOurLives(new Episode("Generisk Episodetittel", "Red Wedding", episodeNummer, sesongNummer, 60, sisteDato, regisor));
            daysOfOurLives.leggTilEpisode(new Episode("Generisk Episodetittel", "Red Wedding", episodeNummer, sesongNummer, 60, sisteDato, regisor));
            episodeNummer++;

            // Undersøker om vi er kommet til fredag
            if (sisteDato.getDayOfWeek().getValue() == 5)
                // Hvis vi har det, øk med 3 dager (vi hopper over helgen)
                sisteDato = sisteDato.plusDays(3);
            else
                // HVis ikke, gå til neste dag
                sisteDato = sisteDato.plusDays(1);

            // Undersøker om vi har kommet til et nytt år
            if (innevaerendeAar != sisteDato.getYear()) {
                // Har vi det, øker vi innevarendeAar med året vi har kommet til, øker sesong med 1 og starter episode på 1
                innevaerendeAar = sisteDato.getYear();
                sesongNummer++;
                episodeNummer = 1;
            }
        }

        tvSerier.add(daysOfOurLives);
    }

    private void generateTvSerieStrangerThings() {
        TvSerie strangerThings = new TvSerie("Stranger Things",
                "Når en ung gutt forsvinner, avdekker en liten by et mysterium som involverer hemmelige eksperimenter, skremmende, overnaturlige krefter, samt ei merkverdig, lita jente.",
                LocalDate.of(2016,6,15),
                "https://image.tmdb.org/t/p/w500/49WJfeN0moxb9IPfGn8AIqMGskD.jpg");

        Person mattDufferRegissor = new Person("Matt", "Duffer");
        Rolle joyceByers = new Rolle("Joyce", "Byers", new Person("Fiona", "Ryder"));
        Rolle jimHopper = new Rolle("Jim", "Hopper", new Person("David", "Harbour"));
        Rolle mikeWheeler = new Rolle("Mike", "Wheeler", new Person("Finn", "Wolfhard"));
        Rolle eleven = new Rolle("Eleven", "", new Person("Millie", "Bobby Brown"));

        ArrayList<Rolle> fullCast = new ArrayList<>(List.of(joyceByers, jimHopper, mikeWheeler, eleven));
        ArrayList<Rolle> coreCast = new ArrayList<>(List.of(mikeWheeler, eleven));

        Episode e1s1 = new Episode("Chapter One: The Vanishing of Will Byers", "Beskrivelse...", 1, 1, 47, LocalDate.of(2016, 7, 15), mattDufferRegissor, "https://image.tmdb.org/t/p/w500/exT4NW9EdXG1qLZHKJnRpq3gh1H.jpg");
        Episode e2s1 = new Episode("Chapter Two: The Weirdo on Maple Street", "Beskrivelse...", 2, 1, 55, LocalDate.of(2016, 7, 15), mattDufferRegissor, "https://image.tmdb.org/t/p/w500/exT4NW9EdXG1qLZHKJnRpq3gh1H.jpg");
        Episode e3s1 = new Episode("Chapter Three: Holly Jolly", "Beskrivelse...", 3, 1, 51, LocalDate.of(2016, 7, 15), mattDufferRegissor, "https://image.tmdb.org/t/p/w500/exT4NW9EdXG1qLZHKJnRpq3gh1H.jpg");
        Episode e4s1 = new Episode("Chapter Four: The Body", "Beskrivelse...", 4, 1, 47, LocalDate.of(2016, 7, 15), mattDufferRegissor, "https://image.tmdb.org/t/p/w500/exT4NW9EdXG1qLZHKJnRpq3gh1H.jpg");

        e1s1.leggTilFlereRoller(coreCast);
        e2s1.leggTilFlereRoller(coreCast);
        e3s1.leggTilFlereRoller(fullCast);
        e4s1.leggTilFlereRoller(fullCast);

        // Legger til episodene i TVSerien
        strangerThings.leggTilEpisode(e1s1);
        strangerThings.leggTilEpisode(e2s1);
        strangerThings.leggTilEpisode(e3s1);
        strangerThings.leggTilEpisode(e4s1);

        Random randomTallGenerator = new Random();

        for (int sesongNummmer = 2; sesongNummmer < 5; sesongNummmer++) {
            for (int episodeNummer = 1; episodeNummer <= 20; episodeNummer++) {
                strangerThings.leggTilEpisode(new Episode("Generisk Tittel", "Noe skjer...", episodeNummer, sesongNummmer, randomTallGenerator.nextInt(11)+20, LocalDate.now(), mattDufferRegissor, "https://www.themoviedb.org/t/p/w227_and_h127_bestv2/lNS6qycyucewz3duTr1tf1LU688.jpg"));
            }
        }

        tvSerier.add(strangerThings);
    }



}
