package com.voidapp.ApplicationWeb.Musique;

public class PochetteAlbum {

    private String id;
    private String title;

    /* Constructeur par défaut */
    public PochetteAlbum() {

    }

    /* Constructeur avec paramètres */
    public PochetteAlbum(String paramId, String paramTitle) {
        this.id = paramId;
        this.title = paramTitle;
    }
    /* Accesseurs */
    public String getId() {
        return this.id;
    }
    public String getTitle() {
        return title;
    }

    /* Mutateurs */
    public void setId(String paramId) {
        this.id = paramId;
    }
    public void setImgPath(String title) {
        this.title = title;
    }
}
