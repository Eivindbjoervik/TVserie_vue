package org.example.model;


import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Produksjon {
    private String tittel, beskrivelse;
    private int spilletid;
    private LocalDate utgivelsesdato;
    private Person regissor;
    private ArrayList<Rolle> rolleListe = new ArrayList<>();

    private String bildeUrl;

    public Produksjon() {}
    public Produksjon(String tittel, String beskrivelse, int spilletid, LocalDate utgivelsesdato, Person regissor) {
        this.tittel = tittel;
        this.beskrivelse = beskrivelse;
        this.spilletid = spilletid;
        this.utgivelsesdato = utgivelsesdato;
        this.regissor = regissor;
        rolleListe = new ArrayList<>();
    }

    public Produksjon(String tittel, String beskrivelse, int spilletid, LocalDate utgivelsesdato, Person regissor, String bildeUrl) {
        this.tittel = tittel;
        this.beskrivelse = beskrivelse;
        this.spilletid = spilletid;
        this.utgivelsesdato = utgivelsesdato;
        this.regissor = regissor;
        this.bildeUrl = bildeUrl;
        rolleListe = new ArrayList<>();
    }

    public void setRolleListe(ArrayList<Rolle> rolleListe) {
        this.rolleListe = rolleListe;
    }

    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public int getSpilletid() {
        return spilletid;
    }

    public void setSpilletid(int spilletid) {
        this.spilletid = spilletid;
    }

    public LocalDate getUtgivelsesdato() {
        return utgivelsesdato;
    }

    public void setUtgivelsesdato(LocalDate utgivelsesdato) {
        this.utgivelsesdato = utgivelsesdato;
    }

    public Person getRegissor() {
        return regissor;
    }

    public void setRegissor(Person regissor) {
        this.regissor = regissor;
    }

    public String getBildeUrl() {
        return bildeUrl;
    }

    public void setBildeUrl(String bildeUrl) {
        this.bildeUrl = bildeUrl;
    }

    public ArrayList<Rolle> getRolleListe() {
        // Vi lager en kopi av listen vi har, slik at den interne listen ikke kan manipuleres direkte utenfor klassen (innkapsling)
        return rolleListe;
    }

    public void leggTilEnRolle(Rolle enRolle) {

        rolleListe.add(enRolle);
    }

    public void leggTilFlereRoller(ArrayList<Rolle> flereRoller) {
        // Vi legger alle elementene fra rolleListen vi sender inn som parameter, til rolleListen som hører til denne produksjonen
        rolleListe.addAll(flereRoller);
    }

    @Override
    public String toString() {
        return tittel + " " + utgivelsesdato.getYear();
    }
}
