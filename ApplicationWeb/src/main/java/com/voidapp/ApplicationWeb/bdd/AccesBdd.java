package com.voidapp.ApplicationWeb.bdd;

import com.voidapp.ApplicationWeb.compteUtilisateur.Utilisateur;


import java.sql.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class AccesBdd {

    private static ResourceBundle properties;
    private static String resourceBundle = "config";

    public static Connection Connect() throws SQLException, ClassNotFoundException {
        properties = ResourceBundle.getBundle(resourceBundle);
            Class.forName(properties.getString("DB_DRIVER"));
            Connection connexion = null;
            String url = properties.getString("JDBC_URL");
            String utilisateur = properties.getString("DB_LOGIN");
            String motDePasse = properties.getString("DB_PASSWORD");
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            return connexion;
    }

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
        boolean st =false;
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select * from Utilisateur where email=? and mdp=?;");
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

    public static String getLName(String email){
        String lname = "";
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select nom from Utilisateur where email=?;");
            ps.setString(1, email);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                lname = rs.getString("nom");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    return lname;
    }

    public static String getFName(String email){
        String lname = "";
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select prenom from Utilisateur where email=?;");
            ps.setString(1, email);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                lname = rs.getString("prenom");
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return lname;
    }

    public static void chMail(String oldemail, String newemail){
        try {
            Connection c = Connect();
            PreparedStatement ps = c.prepareStatement("update Utilisateur set email=? where email=?;");
            ps.setString(1, newemail);
            ps.setString(2, oldemail);
            ps.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void chMdp(String email, String newmdp){
        try {
            Connection c = Connect();
            PreparedStatement ps = c.prepareStatement("update Utilisateur set mdp=? where email=?;");
            ps.setString(1, Hasher.encode(newmdp));
            ps.setString(2, "glenan.bolou@gmail.com");
            ps.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }


}
