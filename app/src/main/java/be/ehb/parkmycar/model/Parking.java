package be.ehb.parkmycar.model;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Parking implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String proprietaire_beheersmaatschappij, nom_naam,recordid, favorite;
    private double nombre_de_places_aantal_plaatsen;
    private String coordonnes_coordinaten;

    public Parking() {
    }

    public Parking(String proprietaire_beheersmaatschappij, String nom_naam, double nombre_de_places_aantal_plaatsen, String recordid, String favorite, String coordonnes_coordinaten) {
        this.proprietaire_beheersmaatschappij = proprietaire_beheersmaatschappij;

        this.nom_naam = nom_naam;
        this.nombre_de_places_aantal_plaatsen = nombre_de_places_aantal_plaatsen;
        this.recordid = recordid;
        this.favorite = favorite;
        this.coordonnes_coordinaten = coordonnes_coordinaten;
    }

    public String getProprietaire_beheersmaatschappij() {
        return proprietaire_beheersmaatschappij;
    }

    public void setProprietaire_beheersmaatschappij(String proprietaire_beheersmaatschappij) {
        this.proprietaire_beheersmaatschappij = proprietaire_beheersmaatschappij;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom_naam() {
        return nom_naam;
    }

    public void setNom_naam(String nom_naam) {
        this.nom_naam = nom_naam;
    }

    public double getNombre_de_places_aantal_plaatsen() {
        return nombre_de_places_aantal_plaatsen;
    }

    public void setNombre_de_places_aantal_plaatsen(double nombre_de_places_aantal_plaatsen) {
        this.nombre_de_places_aantal_plaatsen = nombre_de_places_aantal_plaatsen;
    }

    public String getCoordonnes_coordinaten() {
        return coordonnes_coordinaten;
    }

    public void setCoordonnes_coordinaten(String coordonnes_coordinaten) {
        this.coordonnes_coordinaten = coordonnes_coordinaten;
    }

   /* @Override
    public String toString() {
        return "Parking{" +
                "proprietaire_beheersmaatschappij='" + proprietaire_beheersmaatschappij + '\'' +
                ", recordid='" + recordid + '\'' +
                ", nom_naam='" + nom_naam + '\'' +
                ", nombre_de_places_aantal_plaatsen='" + nombre_de_places_aantal_plaatsen + '\'' +
                ", coordonnes_coordinaten=" + coordonnes_coordinaten +
                '}';
    }*/
}
