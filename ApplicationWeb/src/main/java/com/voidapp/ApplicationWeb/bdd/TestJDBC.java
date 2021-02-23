package com.voidapp.ApplicationWeb.bdd;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class TestJDBC {
    /* La liste qui contiendra tous les résultats de nos essais */
    private List<String> messages = new ArrayList<String>();

    public List<String> executerTests( HttpServletRequest request ) {

        /*   Chargement du driver JDBC pour MySQL */
        try {
            messages.add( "Chargement du driver..." );
            Class.forName( "com.mysql.jdbc.Driver" );
            messages.add( "Driver chargé !" );
        } catch ( ClassNotFoundException e ) {
            messages.add( "Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>"
                        + e.getMessage() );
        }

        /* Connexion à la base de données */
        String url = "jdbc:mysql://vps817240.ovh.net:3306/info_team04_schema";
        String utilisateur = "info_team04";
        String motDePasse = "811lrNyM";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            messages.add( "Connexion à la base de données..." );
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            messages.add( "Connexion réussie !" );

            /* Création de l'objet gérant les requêtes */
           /* statement = connexion.createStatement();*/
            /*messages.add( "Objet requête créé !" );*/

            /* Exécution d'une requête d'écriture*/

           /* int statut = statement.executeUpdate( "INSERT INTO Utilisateur (id,email, prenom, nom, adressefacturation) VALUES ('1','jmarc@mail.fr', 'jean', 'marc', '01 Rue du Test');" );*/

        } catch ( SQLException e ) {
            messages.add( "Erreur lors de la connexion : <br/>"
                        + e.getMessage() );
        } finally {
            messages.add( "Fermeture de l'objet ResultSet." );
            if ( resultat != null ) {
                try {
                    resultat.close();
                } catch ( SQLException ignore ) {
                }
            }
            messages.add( "Fermeture de l'objet Statement." );
            if ( statement != null ) {
                try {
                    statement.close();
                } catch ( SQLException ignore ) {
                }
            }
            messages.add( "Fermeture de l'objet Connection." );
            if ( connexion != null ) {
                try {
                    connexion.close();
                } catch ( SQLException ignore ) {
                }
            }
        }
        return messages;
    }
}