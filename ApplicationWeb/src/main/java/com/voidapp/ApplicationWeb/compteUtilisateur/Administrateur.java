package com.voidapp.ApplicationWeb.compteUtilisateur;

import java.util.Date;

public abstract class Administrateur extends Utilisateur {

    /* Constructeur */

    public Administrateur(int id, String mail, String mdp, Civilite civilite, String nom, String prenom, Date dateadhesion, String adressefacturation, Genre genrepref) {
        super(id, mail, mdp, civilite, nom, prenom, adressefacturation, genrepref);
    }
}
