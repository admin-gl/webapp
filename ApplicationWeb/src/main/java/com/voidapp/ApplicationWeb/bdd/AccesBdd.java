package com.voidapp.ApplicationWeb.bdd;

import com.voidapp.ApplicationWeb.compteUtilisateur.Utilisateur;


import java.sql.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class AccesBdd {

    private static ResourceBundle properties;
    private static String resourceBundle = "config";

    public static String AjoutUtilisateur( Utilisateur user ) {
        String message="";
        properties = ResourceBundle.getBundle(resourceBundle);

        /*   Chargement du driver JDBC pour MySQL */
        try {
            Class.forName( properties.getString("DB_DRIVER") );
        } catch ( ClassNotFoundException e ) {
            message="erreur chargement du driver";
        }

        /* Connexion à la base de données */
        String url = properties.getString("JDBC_URL");
        String utilisateur = properties.getString("DB_LOGIN");
        String motDePasse = properties.getString("DB_PASSWORD");
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            statement = connexion.createStatement();

            /* Exécution d'une requête d'écriture*/


            String requete ="INSERT INTO Utilisateur (email,prenom,nom,adresse,mdp) VALUES ('"+user.getMail()+"','"+user.getPrenom()+"','"+user.getNom()+"','"+user.getAdressefacturation()+"','"+user.getMdp()+"');";
            int statut = statement.executeUpdate(requete);

        } catch ( SQLException e ) {
            message=message+"erreur dans la requete";
        } finally {
            if ( resultat != null ) {
                try {
                    resultat.close();
                } catch ( SQLException ignore ) {
                }
            }
            if ( statement != null ) {
                try {
                    statement.close();
                } catch ( SQLException ignore ) {
                }
            }
            if ( connexion != null ) {
                try {
                    connexion.close();
                } catch ( SQLException ignore ) {
                }
            }
        }
        return message;
    }

    public static boolean checkUser(String email,String mdp) {
        properties = ResourceBundle.getBundle(resourceBundle);
        boolean st =false;
        try {

            Class.forName(properties.getString("DB_DRIVER"));

            String url = properties.getString("JDBC_URL");
            String utilisateur = properties.getString("DB_LOGIN");
            String motDePasse = properties.getString("DB_PASSWORD");
            Connection connexion = null;
            Statement statement = null;
            ResultSet resultat = null;
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            PreparedStatement ps = connexion.prepareStatement("select * from Utilisateur where email=? and mdp=?");
            ps.setString(1, email);
            ps.setString(2, mdp);
            ResultSet rs =ps.executeQuery();
            st = rs.next();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return st;
    }

}
