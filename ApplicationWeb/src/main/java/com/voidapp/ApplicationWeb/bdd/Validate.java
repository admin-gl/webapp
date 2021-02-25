package com.voidapp.ApplicationWeb.bdd;

import java.sql.*;
import java.util.ResourceBundle;

public class Validate {
    private static ResourceBundle properties;
    private static String resourceBundle = "config";

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