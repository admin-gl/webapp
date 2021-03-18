package com.voidapp.ApplicationWeb.Musique;

public class Album {

    private String id;
    private String titre;
    private String artiste;
    private String style;

    public Album(){}

    public Album(String id, String titre, String artist, String style){
        this.id = id;
        this.artiste = artist;
        this.style = style;
        this.titre = titre;
    }

    public String getArtiste() {
        return artiste;
    }

    public String getId() {
        return id;
    }

    public String getStyle() {
        return style;
    }

    public String getTitre() {
        return titre;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
