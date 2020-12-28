package be.ehb.parkmycar.model;

import android.net.Uri;

import java.io.Serializable;

public class Parking implements Serializable {

private String proprietaire_beheersmaatschappij,recordid, nom_naam, nombre_de_places_aantal_plaatsen;
private Uri coordonnes_coordinaten;

    public Parking() {
    }

    public Parking(String proprietaire_beheersmaatschappij, String nom_naam, String nombre_de_places_aantal_plaatsen,String recordid,   Uri coordonnes_coordinaten) {
        this.proprietaire_beheersmaatschappij = proprietaire_beheersmaatschappij;
        this.recordid = recordid;
        this.nom_naam = nom_naam;
        this.nombre_de_places_aantal_plaatsen = nombre_de_places_aantal_plaatsen;
        this.coordonnes_coordinaten = coordonnes_coordinaten;
    }

    public String getProprietaire_beheersmaatschappij() {
        return proprietaire_beheersmaatschappij;
    }

    public void setProprietaire_beheersmaatschappij(String proprietaire_beheersmaatschappij) {
        this.proprietaire_beheersmaatschappij = proprietaire_beheersmaatschappij;
    }

    public String getNom_naam() {
        return nom_naam;
    }

    public void setNom_naam(String nom_naam) {
        this.nom_naam = nom_naam;
    }

    public String getNombre_de_places_aantal_plaatsen() {
        return nombre_de_places_aantal_plaatsen;
    }

    public void setNombre_de_places_aantal_plaatsen(String nombre_de_places_aantal_plaatsen) {
        this.nombre_de_places_aantal_plaatsen = nombre_de_places_aantal_plaatsen;
    }

    public Uri getCoordonnes_coordinaten() {
        return coordonnes_coordinaten;
    }

    public void setCoordonnes_coordinaten(Uri coordonnes_coordinaten) {
        this.coordonnes_coordinaten = coordonnes_coordinaten;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "proprietaire_beheersmaatschappij='" + proprietaire_beheersmaatschappij + '\'' +
                ", recordid='" + recordid + '\'' +
                ", nom_naam='" + nom_naam + '\'' +
                ", nombre_de_places_aantal_plaatsen='" + nombre_de_places_aantal_plaatsen + '\'' +
                ", coordonnes_coordinaten=" + coordonnes_coordinaten +
                '}';
    }
}
