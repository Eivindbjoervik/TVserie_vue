package org.example.model;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

// En Episode "er en" produksjon, så derfor har vi satt at den extender Produksjon
// Vi får dermed med alle egenskapene fra Produksjon "gratis"
public class Episode extends Produksjon implements Comparable<Episode> {
    private int episodeNummer, sesongNummer;
    private String bildeUrl;

    public Episode() {}

    public Episode(String tittel, int episodeNummer, int sesongNummer) {
        this(tittel, "", episodeNummer, sesongNummer, 0, LocalDate.MIN, new Person("Unknown", "Unknown"));
    }

    public Episode(String tittel, String beskrivelse, int episodeNummer, int sesongNummer, int spilletid, LocalDate utgivelsesdato, Person regissor) {
        // Kaller superkontruktøren (som tilhører Produksjon), vi "sender" da tittel, beskrivelse, spilletid, utgivelsesdato og regisor videre
        super(tittel, beskrivelse, spilletid, utgivelsesdato, regissor);
        this.episodeNummer = episodeNummer;
        this.sesongNummer = sesongNummer;
    }

    public Episode(String tittel, String beskrivelse, int episodeNummer, int sesongNummer, int spilletid, LocalDate utgivelsesdato, Person regissor, String bildeUrl) {
        // Kaller superkontruktøren (som tilhører Produksjon), vi "sender" da tittel, beskrivelse, spilletid, utgivelsesdato og regisor videre
        super(tittel, beskrivelse, spilletid, utgivelsesdato, regissor, bildeUrl);
        this.episodeNummer = episodeNummer;
        this.sesongNummer = sesongNummer;
    }

    public Episode(String tittel, String beskrivelse, int episodeNummer, int sesongNummer, int spilletid, LocalDate utgivelsesdato, String bildeUrl) {
        super(tittel, beskrivelse, spilletid, utgivelsesdato, new Person("Unknown", "Unknown"), bildeUrl);
        this.episodeNummer = episodeNummer;
        this.sesongNummer = sesongNummer;
    }

    public Episode(String tittel, int sesongNummer, int episodeNummer, String beskrivelse, int spilletid, LocalDate utgivelsesdato, String bildeUrl) {
        super(tittel, beskrivelse, spilletid, utgivelsesdato, new Person("Unknown", "Unknown"), bildeUrl);
        this.episodeNummer = episodeNummer;
        this.sesongNummer = sesongNummer;
    }


    public Episode(int sesongNr, int episodeNr, String tittel, int spilletid) {
    }


    public String getBildeUrl() {
        return bildeUrl;
    }

    public void setBildeUrl(String bildeUrl) {
        this.bildeUrl = bildeUrl;
    }
    public int getEpisodeNummer() {
        return episodeNummer;
    }

    public void setEpisodeNummer(int episodeNummer) {
        this.episodeNummer = episodeNummer;
    }

    public int getSesongNummer() {
        return sesongNummer;
    }

    public void setSesongNummer(int sesongNummer) {
        this.sesongNummer = sesongNummer;
    }

    @Override
    public String toString() {
        return "E" + String.format("%02d", episodeNummer) + "S" + String.format("%02d", sesongNummer) + " - " + super.getTittel() + " - " + super.getSpilletid() + "min";
    }


    @Override
    public int compareTo(@NotNull Episode annenEpisode) {
        return this.episodeNummer - annenEpisode.getEpisodeNummer();
    }
}
