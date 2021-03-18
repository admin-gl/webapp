package com.voidapp.ApplicationWeb.formulaire;

import com.voidapp.ApplicationWeb.Musique.Album;
import com.voidapp.ApplicationWeb.Musique.Musique;

public class SearchResult {
    public Musique[] musiques;
    public Album[] albums;

    public SearchResult(Musique[] musiques, Album[] albums){
        this.albums = albums;
        this.musiques = musiques;
    }
}
