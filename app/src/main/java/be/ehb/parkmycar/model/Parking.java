package be.ehb.parkmycar.model;

import android.net.Uri;

import java.io.Serializable;

public class Parking implements Serializable {

private String naam, beheerMaatschapij, aantalPlaatsen;
private Uri data ;

    public Parking() {
    }

    public Parking(String naam, String beheerMaatschapij, String aantalPlaatsen, Uri data) {
        this.naam = naam;
        this.beheerMaatschapij = beheerMaatschapij;
        this.aantalPlaatsen = aantalPlaatsen;
        this.data = data;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeheerMaatschapij() {
        return beheerMaatschapij;
    }

    public void setBeheerMaatschapij(String beheerMaatschapij) {
        this.beheerMaatschapij = beheerMaatschapij;
    }

    public String getAantalPlaatsen() {
        return aantalPlaatsen;
    }

    public void setAantalPlaatsen(String aantalPlaatsen) {
        this.aantalPlaatsen = aantalPlaatsen;
    }

    public Uri getData() {
        return data;
    }

    public void setData(Uri data) {
        this.data = data;
    }
}
