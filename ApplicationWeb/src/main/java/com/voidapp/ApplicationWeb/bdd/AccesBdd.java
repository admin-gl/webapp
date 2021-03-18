package com.voidapp.ApplicationWeb.bdd;

import com.voidapp.ApplicationWeb.Musique.Album;
import com.voidapp.ApplicationWeb.Musique.Musique;
import com.voidapp.ApplicationWeb.compteUtilisateur.Utilisateur;
import com.voidapp.ApplicationWeb.formulaire.SearchResult;

import java.sql.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AccesBdd {

    private static ResourceBundle properties;
    private static final String resourceBundle = "config";

    public static Connection Connect() throws SQLException, ClassNotFoundException {
        properties = ResourceBundle.getBundle(resourceBundle);
            Class.forName(properties.getString("DB_DRIVER"));
            Connection connexion;
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
        try (Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
             Statement statement = connexion.createStatement()) {

            /* Exécution d'une requête d'écriture*/


            String requete="INSERT INTO Utilisateur (email,nom,prenom,mdp,adresse,dateadhesion,estadmin,civilite) VALUES ('"+user.getMail()+"','"+user.getNom()+"','"+user.getPrenom()+"','"+user.getMdp()+"','"+user.getAdressefacturation()+"','"+user.getDateadhesion()+"',false,'"+user.getCivilite()+"');";

            statement.executeUpdate(requete);

        } catch (SQLException e) {
            message = message + "erreur dans la requete";
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
            ps.close();
            c.close();

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
            rs.close();
            ps.close();
            c.close();
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
            rs.close();
            ps.close();
            c.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return lname;
    }

    /*
     * permet d'obtenir le total de titres présents dans la bibliothèque
     */
    public static int getTotalAlbum(){
        int i = -1;
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select count(*) from album;");
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                i = rs.getInt(1);
            }
            rs.close();
            ps.close();
            c.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    /*
     * permet d'obtenir les dux titres les plus aimés (à voir)
     */
    public static int[][] getTopTen(){
        int[] top = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        int[] nbLike = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        int k=0;
        try {
            Connection c = Connect();
            PreparedStatement ps = c.prepareStatement("select id_musique, count(id_utilisateur) as nbLike from likes group by id_musique order by nbLike desc limit 10;");
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                top[k] = rs.getInt("id_musique");
                nbLike[k] = rs.getInt("nbLike");
                k++;
            }
            rs.close();
            ps.close();
            c.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new int[][]{top,nbLike};
    }

    public static int getNbSongsAlbum(String id){
        String n = "";
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select count(*) from musique, album, linkerAlbMus where album.id=? and linkerAlbMus.idAlb=album.id and linkerAlbMus.idMus=musique.id;");
            ps.setString(1, id);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                n = rs.getString(1);
            }
            rs.close();
            ps.close();
            c.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return Integer.parseInt(n);
    }

    public static Musique[] getSongsInAlbum(String idAlb,int nbSong){
        Musique[] titles = new Musique[nbSong];
        int k=0;
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select distinct musique.id,musique.nom from musique, album, linkerAlbMus where linkerAlbMus.idAlb=? and musique.id=linkerAlbMus.idMus;");
            ps.setString(1, idAlb);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                titles[k] = new Musique(rs.getString("id"),rs.getString("nom"));
                k++;
            }
            rs.close();
            ps.close();
            c.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("dans getSongs : "+Arrays.toString(titles));
        return titles;
    }

    public static void chMail(String oldemail, String newemail){
        try {
            Connection c = Connect();
            PreparedStatement ps = c.prepareStatement("update Utilisateur set email=? where email=?;");
            ps.setString(1, newemail);
            ps.setString(2, oldemail);
            ps.executeUpdate();
            ps.close();
            c.close();
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
            ps.close();
            c.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static String isAdmin(String email){
        String adm ="";
        try {
            Connection c = Connect();
            PreparedStatement ps = c.prepareStatement("select estadmin from Utilisateur where email=?;");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                adm = rs.getString("estadmin");
            }
            ps.close();
            c.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return adm;
    }

    public static String getTitle(String id){
        String title = "";
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select nom from musique where id=?;");
            ps.setString(1, id);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                title = rs.getString("nom");
            }
            rs.close();
            ps.close();
            c.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return title;
    }

    public static String getArtist(String id){
        String artist = "";
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select artiste from musique where id=?;");
            ps.setString(1, id);
            ResultSet rs =ps.executeQuery();
            while (rs.next()){
                artist = rs.getString("artiste");
            }
            rs.close();
            ps.close();
            c.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return artist;
    }

    public static String getAlbumFromSongId(String id){
        String album = "";
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select album.titre from album,musique,linkerAlbMus where linkerAlbMus.idMus =? and album.id=linkerAlbMus.idAlb limit 1;");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                album = rs.getString("titre");
            }
            rs.close();
            ps.close();
            c.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return album;
    }

    public static int getAlbumIdFromAlbumTitle(String title){
        int idA=-1;
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select id from album where titre=?;");
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                idA = rs.getInt("id");
            }
            rs.close();
            ps.close();
            c.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return idA;
    }

    public static String getAlbumTitle(String id){
        String titleA = "";
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select titre from album where id=?;");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                titleA = rs.getString("titre");
            }
            rs.close();
            ps.close();
            c.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return titleA;
    }

    public static String[] getSimilarAlbum(String id, int nbSim){
        String[] suggested = new String[nbSim];
        int i=0;
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select distinct album.id from album,musique,linkerAlbMus where style = (select style from album, musique, linkerAlbMus where linkerAlbMus.idMus =? and album.id=linkerAlbMus.idAlb limit 1);");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                suggested[i] = rs.getString("id");
                i++;
            }
            rs.close();
            ps.close();
            c.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return suggested;
    }

    public static int getNbSimilarAlb(String id){
        int i=-1;
        try {
            Connection c =  Connect();
            PreparedStatement ps = c.prepareStatement("select count(distinct album.id) as nbSim from album,musique,linkerAlbMus where style = (select style from album, musique, linkerAlbMus where linkerAlbMus.idMus =? and album.id=linkerAlbMus.idAlb limit 1);");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                i = rs.getInt("nbSim");
            }
            rs.close();
            ps.close();
            c.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void addSong(String titre, String artiste, String album, String style) {
        int idA = -1;
        int idS = -1;
        try {
            Connection c = Connect();
            PreparedStatement albExist = c.prepareStatement("select id from album where titre=?");
            PreparedStatement addS = c.prepareStatement("insert into musique(nom,artiste) values(?,?);");
            PreparedStatement getSId = c.prepareStatement("select id from musique where nom=? and artiste=?;");
            albExist.setString(1, album);
            addS.setString(1, titre);
            addS.setString(2, artiste);
            getSId.setString(1, titre);
            getSId.setString(2, artiste);
            ResultSet rs = albExist.executeQuery();
            addS.executeUpdate();
            ResultSet rs3 = getSId.executeQuery();
            while (rs.next()) {
                idA = rs.getInt("id");
            }
            while (rs3.next()) {
                idS = rs3.getInt("id");
            }

            System.out.println(idA + idS);
            //si l'album existe déjà
            if (idA != -1) {
                if (idS != -1) {
                    PreparedStatement addLink = c.prepareStatement("insert into linkerAlbMus values(?,?);");
                    addLink.setString(1, "" + idA);
                    addLink.setString(2, "" + idS);
                    addLink.executeUpdate();
                }
                //si l'album n'existe pas
            } else {
                PreparedStatement addA = c.prepareStatement("insert into album(titre,artiste,style) values(?,?,?);");
                PreparedStatement getAId = c.prepareStatement("select id from album where titre=? and artiste=?;");
                addA.setString(1, album);
                addA.setString(2, artiste);
                addA.setString(3, style);
                getAId.setString(1, album);
                getAId.setString(2, artiste);
                addA.executeUpdate();
                ResultSet rs5 = getAId.executeQuery();
                while (rs5.next()) {
                    idA = rs5.getInt("id");
                }
                if (idS != -1) {
                    PreparedStatement addLink = c.prepareStatement("insert into linkerAlbMus values(?,?);");
                    addLink.setString(1, "" + idA);
                    addLink.setString(2, "" + idS);
                    addLink.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updateLikes(String email, String idMusique , boolean liked){
        try {
            Connection c = Connect();
            PreparedStatement ps;
            if(liked) {
                ps = c.prepareStatement("insert into likes (id_musique, id_utilisateur) values (?, (select id from Utilisateur where email= ?))");
            } else {
                ps = c.prepareStatement("delete from likes where id_musique=? and id_utilisateur=(select id from Utilisateur where email= ?)");
            }
            ps.setString(1, idMusique);
            ps.setString(2, email);
            ps.executeUpdate();

            ps.close();
            c.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> getLikes(String email){
        try{
            Connection c = Connect();
            PreparedStatement ps = c.prepareStatement("select id_musique from likes where id_utilisateur = (select id from Utilisateur where email = ?)");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            ArrayList<Integer> temp = new ArrayList<>();
            while(rs.next()){
                temp.add(rs.getInt("id_musique"));
            }
            rs.close();
            ps.close();
            c.close();

            return temp;
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static SearchResult search(String searched, int page){
        try{
            Connection c = Connect();
            int limit = 5;
            int offset = (5 * page);
            PreparedStatement ps1 = c.prepareStatement("(select * from musique where artiste like ? or nom like ? union select * from musique where artiste sounds like ? or nom sounds like ?) limit ? offset ?;");
            ps1.setString(1, "%"+searched+"%");
            ps1.setString(2, "%"+searched+"%");
            ps1.setString(3, searched);
            ps1.setString(4, searched);
            ps1.setInt(5, limit);
            ps1.setInt(6, offset);
            ResultSet rs1 = ps1.executeQuery();
            ArrayList<Musique> tempMus = new ArrayList<>();
            while(rs1.next()){
                Musique music = new Musique();
                music.setId(rs1.getInt("id")+"");
                music.setAuthor(rs1.getString("artiste"));
                music.setTitle(rs1.getString("nom"));
                music.setImgPath("/music/"+ getAlbumFromSongId(music.getId()) + "/cover.jpg");
                tempMus.add(music);
            }
            rs1.close();
            ps1.close();

            PreparedStatement ps2 = c.prepareStatement("(select * from album where artiste like ? or titre like ? union select * from album where artiste sounds like ? or titre sounds like ?) limit ? offset ?;");
            ps2.setString(1, "%"+searched+"%");
            ps2.setString(2, "%"+searched+"%");
            ps2.setString(3, searched);
            ps2.setString(4, searched);
            ps2.setInt(5, limit);
            ps2.setInt(6, offset);
            ResultSet rs2 = ps2.executeQuery();
            ArrayList<Album> tempAlbum = new ArrayList<>();
            while(rs2.next()){
                Album album = new Album();
                album.setId(rs2.getInt("id")+ "");
                album.setArtiste(rs2.getString("artiste"));
                album.setStyle(rs2.getString("style"));
                album.setTitre(rs2.getString("titre"));
                tempAlbum.add(album);
            }
            rs2.close();
            ps2.close();
            c.close();

            return new SearchResult(tempMus.toArray(new Musique[0]), tempAlbum.toArray(new Album[0]));
        } catch (Exception e){
            e.printStackTrace();
        }
        return new SearchResult(new Musique[0], new Album[0]);
    }

    public static void deleteSongId(String id){
        try{
            Connection c = Connect();
            PreparedStatement ps = c.prepareStatement("delete from musique where id=?;");
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
            c.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateSong(String id, String newTitle){
        try{
            Connection c = Connect();
            PreparedStatement ps = c.prepareStatement("update musique set nom=? where id=?;");
            System.out.println(newTitle);
            System.out.println(id);
            ps.setString(1, newTitle);
            ps.setString(2, id);
            ps.executeUpdate();
            ps.close();
            c.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    les quelques fonctions suvantes servaient à téléchager la musique stockée sous forme de blob sur la bdd ainsi qu'à l'uploader
    c'était fonctionnel jusqu'au moment ou les serveurs d'OVH ont pris feu, et on a décidé de travailler plus simplement après


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

<<<<<<< HEAD
=======
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

     public static void main(String[] args) {
        writeMusic("enigme 1", "Kilian", "wav", "C:\\Users\\kilia\\Documents\\webapp\\ApplicationWeb\\src\\main\\webapp\\music\\enigme_1.wav", null);
        writeMusic("enigme 2", "Kilian", "wav", "C:\\Users\\kilia\\Documents\\webapp\\ApplicationWeb\\src\\main\\webapp\\music\\enigme_2.wav", "C:\\Users\\kilia\\Pictures\\musique.png");
    }

    public static void main(String[] args) {
        File f1 = new File("/Users/glenan/Desktop/webapp/ApplicationWeb/src/main/webapp/music/imgP1.png");
        File f2 = new File("/Users/glenan/Desktop/webapp/ApplicationWeb/src/main/webapp/music/imgP2.png");
        File F[] = {f1,f2};

        getPochette(F);
        System.out.println(getTotalMusic());
    }

 */

}

