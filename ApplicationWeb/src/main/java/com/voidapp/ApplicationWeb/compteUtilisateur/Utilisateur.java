package com.voidapp.ApplicationWeb.compteUtilisateur;


import com.voidapp.ApplicationWeb.bdd.AjoutBdd;

import java.util.Date;

public class Utilisateur {
    public int maxId=0;
    int id;
    String mail;
    String mdp;
    Civilite civilite;
    Statut statut;
    String nom;
    String prenom;
    java.sql.Date dateadhesion;
    String adressefacturation;
    Genre genrepref;

    public Utilisateur() {
    }

    public Utilisateur(int id, String mail, String mdp, Civilite civilite, String nom, String prenom, String adressefacturation, Genre genrepref) {
        this.id = id;
        this.mail = mail;
        this.mdp = mdp;
        this.civilite = civilite;
        this.statut = Statut.Utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.dateadhesion =new java.sql.Date(new Date().getTime());
        this.adressefacturation = adressefacturation;
        this.genrepref = genrepref;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Civilite getCivilite() {
        return civilite;
    }

    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateadhesion() {
        return dateadhesion;
    }

    public void setDateadhesion(java.sql.Date dateadhesion) {
        this.dateadhesion = dateadhesion;
    }

    public String getAdressefacturation() {
        return adressefacturation;
    }

    public void setAdressefacturation(String adressefacturation) {
        this.adressefacturation = adressefacturation;
    }

    public Genre getGenrepref() {
        return genrepref;
    }

    public void setGenrepref(Genre genrepref) {
        this.genrepref = genrepref;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public enum Statut{
        Utilisateur, AdministrateurClient, AdministrateurMusique
    }

    public enum Civilite {
        madame, monsieur
    }

    public enum Genre {
        House, Pop, Classique, Jazz, Métal
    }

    public String AddUser(){
        AjoutBdd requete = new AjoutBdd();
        String reponse = requete.AjoutUtilisateur(this);
        setMaxId(maxId+1);
        return reponse;
    }

}
