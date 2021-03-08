package com.voidapp.ApplicationWeb.bdd;

import com.voidapp.ApplicationWeb.Musique.Musique;
import com.voidapp.ApplicationWeb.compteUtilisateur.Utilisateur;

import java.io.*;
import java.sql.*;
import java.util.ResourceBundle;

public class AccesBdd {

    private static ResourceBundle properties;
    private static final String resourceBundle = "config";

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
        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
             Statement statement = connexion.createStatement()) {

            /* Exécution d'une requête d'écriture*/


            String requete = "INSERT INTO Utilisateur (email,prenom,nom,adresse,mdp) VALUES ('" + user.getMail() + "','" + user.getPrenom() + "','" + user.getNom() + "','" + user.getAdressefacturation() + "','" + user.getMdp() + "');";
            statement.executeUpdate(requete);

        } catch (SQLException e) {
            message = message + "erreur dans la requete";
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
            Connection connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            PreparedStatement ps = connexion.prepareStatement("select * from Utilisateur where email=? and mdp=?");
            ps.setString(1, email);
            ps.setString(2, mdp);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
            rs.close();
            connexion.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return st;
    }

    public static void writeMusic(String nom, String artiste, String form, String absoluteMusPath, String absoluteImgPath){
        properties = ResourceBundle.getBundle(resourceBundle);
        String SQL = "INSERT INTO musique (nom, artiste, form) VALUES (?, ?, ?);";
        String url = properties.getString("JDBC_URL") + "?serverTimezone=UTC";
        String utilisateur = properties.getString("DB_LOGIN");
        String motDePasse = properties.getString("DB_PASSWORD");

        try {
            Class.forName(properties.getString("DB_DRIVER"));
            // set parameter;
            Connection conn = DriverManager.getConnection(url, utilisateur, motDePasse);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            System.out.println("connecté");
            pstmt.setString(1, nom);
            pstmt.setString(2, artiste);
            pstmt.setString(3, form);
            System.out.println(pstmt.toString());
            pstmt.executeUpdate();

            writeBlob(conn, absoluteMusPath, absoluteImgPath);

            conn.close();

        } catch (SQLException | FileNotFoundException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeBlob(Connection conn, String absoluteMusPath, String absoluteImgPath) throws SQLException, FileNotFoundException {

        String idGetteur = "SELECT id FROM musique WHERE id = (SELECT MAX(id) FROM musique)";
        PreparedStatement pstmt = conn.prepareStatement(idGetteur);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            int id = rs.getInt("id");
            String SQLUpdate;
            File temp;
            FileInputStream input;
            if(absoluteImgPath != null) {
                SQLUpdate = "UPDATE musique SET image = ? WHERE id = ?";
                pstmt = conn.prepareStatement(SQLUpdate);
                temp = new File(absoluteImgPath);
                input = new FileInputStream(temp);
                pstmt.setBinaryStream(1, input);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }
            SQLUpdate = "UPDATE musique SET musique = ? WHERE id = ?";
            pstmt = conn.prepareStatement(SQLUpdate);
            temp = new File(absoluteMusPath);
            input = new FileInputStream(temp);
            pstmt.setBinaryStream(1, input);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        }
    }

    public static Musique readMusic(int musiqueId, File mus, File img) {
        // update sql
        properties = ResourceBundle.getBundle(resourceBundle);
        String selectSQL = "SELECT * FROM musique WHERE id=?";
        ResultSet rs = null;
        String url = properties.getString("JDBC_URL");
        String utilisateur = properties.getString("DB_LOGIN");
        String motDePasse = properties.getString("DB_PASSWORD");

        try {

            Class.forName(properties.getString("DB_DRIVER"));
            Connection conn = DriverManager.getConnection(url, utilisateur, motDePasse);
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            // set parameter;
            pstmt.setInt(1, musiqueId);
            rs = pstmt.executeQuery();

            // write binary stream into file

            FileOutputStream outputMus = new FileOutputStream(mus);
            FileOutputStream outputImg = new FileOutputStream(img);
            Musique fin = null;
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String artiste = rs.getString("artiste");
                String format = rs.getString("form");
                InputStream inputMus = rs.getBinaryStream("musique");
                InputStream inputImg = rs.getBinaryStream("image");

                byte[] buffer = new byte[1024];
                while (inputMus.read(buffer) > 0) {
                    outputMus.write(buffer);
                }
                if(inputImg != null){
                    byte[] bufferImg = new byte[1024];
                    while (inputImg.read(bufferImg) > 0) {
                        outputImg.write(bufferImg);
                    }
                }
                if(inputImg == null) {
                    fin = new Musique("" + id,nom,format,artiste, "music/music.wav");
                } else {
                    fin = new Musique("" + id,nom,format,artiste, "music/music.wav", "music/img.png");
                    inputImg.close();
                }
                inputMus.close();

            }
            rs.close();
            outputImg.close();
            outputMus.close();
            conn.close();
            return fin;
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return null;

    }

}

