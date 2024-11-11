package org.example.model;

import java.time.LocalDate;

// En film "er en" produksjon, så derfor har vi satt at den extender Produksjon
// Vi får dermed med alle egenskapene fra Produksjon "gratis"
public class Film extends Produksjon {

    public Film(String tittel, String beskrivelse, int spilletid, LocalDate utgivelsesdato, Person regissor) {
        // Kaller superkontruktøren (som tilhører Produksjon), vi "sender" da tittel, beskrivelse, spilletid, utgivelsesdato og regisor videre
        super(tittel, beskrivelse, spilletid, utgivelsesdato, regissor);
    }

    public Film(){}

    @Override
    public String toString() {
        return getTittel() + (getUtgivelsesdato() != LocalDate.MIN ? " (" + getUtgivelsesdato().getYear() + ")" :"");// + (super.getSpilletid() > 0 ? super.getSpilletid() + " minutter" : "");
    }
}