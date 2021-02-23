package com.voidapp.ApplicationWeb.compteUtilisateur;

import java.util.ArrayList;

public class Système {

    public ArrayList<Utilisateur> BdDClients;

    public int lastId;

    public Système() {
        this.BdDClients = new ArrayList<>();
        this.lastId = 0;
    }



}
