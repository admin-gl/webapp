package com.voidapp.ApplicationWeb.bdd;

import com.voidapp.ApplicationWeb.Musique.Musique;
import com.voidapp.ApplicationWeb.compteUtilisateur.Utilisateur;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
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
            ResultSet rs = ps.executeQuery();
            st = rs.next();
            rs.close();

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

    public static int getTotalMusic(){
        int i = -1;
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select count(*) from musique;");
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                i = rs.getInt(1);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int[] getTopTen(){
        int top[]={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        int k=0;
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select top 10 * from musique order by likes;");
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                top[k] = rs.getInt(1);
                k++;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return top;
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
            ps.setString(2, email);
            ps.executeUpdate();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
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
                    System.out.println("pas d'image");
                    fin = new Musique("" + id,nom,format,artiste, "music/music.wav");
                } else {
                    fin = new Musique("" + id,nom,format,artiste, "music/music.wav", "music/img.png");
                }
            }
            rs.close();
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

    public static Musique[] getPochette(File[] img){
        properties = ResourceBundle.getBundle(resourceBundle);
        String url = properties.getString("JDBC_URL");
        String utilisateur = properties.getString("DB_LOGIN");
        String motDePasse = properties.getString("DB_PASSWORD");
        int k=0;
        try {
            Class.forName(properties.getString("DB_DRIVER"));
            Connection conn = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("db connected");
            PreparedStatement ps = conn.prepareStatement("select * from musique;");
            ResultSet rs =ps.executeQuery();
            Musique m[] = new Musique[img.length];
            while (rs.next()){
                FileOutputStream outputImg = new FileOutputStream(img[k]);
                k++;
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String artiste = rs.getString("artiste");
                InputStream binaryStream = rs.getBinaryStream("image");
                System.out.println("binary stream : "+binaryStream);
                byte[] buffer = new byte[1024];
                if (binaryStream!=null){
                    while (binaryStream.read(buffer) > 0) {
                        outputImg.write(buffer);
                    }
                    m[k] = new Musique("" + id,nom,artiste,"music/imgP"+k+".png");
                } else {
                    m[k] = new Musique("" + id,nom,artiste);
                }
            }
            rs.close();
            return m;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

/*    public static void main(String[] args) {
        writeMusic("enigme 1", "Kilian", "wav", "C:\\Users\\kilia\\Documents\\webapp\\ApplicationWeb\\src\\main\\webapp\\music\\enigme_1.wav", null);
        writeMusic("enigme 2", "Kilian", "wav", "C:\\Users\\kilia\\Documents\\webapp\\ApplicationWeb\\src\\main\\webapp\\music\\enigme_2.wav", "C:\\Users\\kilia\\Pictures\\musique.png");
    }*/

    public static void main(String[] args) {
        File f1 = new File("/Users/glenan/Desktop/webapp/ApplicationWeb/src/main/webapp/music/imgP1.png");
        File f2 = new File("/Users/glenan/Desktop/webapp/ApplicationWeb/src/main/webapp/music/imgP2.png");
        File F[] = {f1,f2};

        getPochette(F);
        System.out.println(getTotalMusic());
    }

}

