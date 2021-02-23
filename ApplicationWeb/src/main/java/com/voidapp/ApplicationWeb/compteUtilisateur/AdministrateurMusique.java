package com.voidapp.ApplicationWeb.compteUtilisateur;

import java.util.Date;

public class AdministrateurMusique extends Administrateur {

    public AdministrateurMusique(int id, String mail, String mdp, Civilite civilite, String nom, String prenom, Date dateadhesion, String adressefacturation, Genre genrepref) {
        super(id, mail, mdp, civilite, nom, prenom, dateadhesion, adressefacturation, genrepref);
        this.statut=Statut.AdministrateurMusique;
    }
}