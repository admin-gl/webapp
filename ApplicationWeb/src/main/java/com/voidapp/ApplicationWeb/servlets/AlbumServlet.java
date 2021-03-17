package com.voidapp.ApplicationWeb.servlets;

import com.voidapp.ApplicationWeb.Musique.Musique;
import com.voidapp.ApplicationWeb.Musique.PochetteAlbum;
import com.voidapp.ApplicationWeb.bdd.AccesBdd;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlbumServlet extends HttpServlet {

    private final String VIEW="/WEB-INF/albumPage.jsp";

    private void doProcess(HttpServletRequest request, HttpServletResponse response) {


        String idAlb = request.getParameter("id");
        int nbSong = AccesBdd.getNbSongsAlbum(idAlb);

        String titleAlbum = AccesBdd.getAlbumTitle(idAlb);


        Musique[] titles;
        //les id des albums de même style
        titles = AccesBdd.getSongsInAlbum(idAlb,nbSong);

        /* cette partie de code était dédiée à la récupération des blobs de musique/image stockées en ligne
         * c'était fonctionnel jusqu'au moment ou les serveurs d'OVH ont pris feu
         * on a alors décidé de faire les choses plus simplement, avec des données en local */

		/*
		if (id != null && !id.contentEquals("")) {
			File musFile = new File(getServletContext().getRealPath("/") + "/music/music.wav");
			File imgFile = new File(getServletContext().getRealPath("/") + "music/img.png");
			try {
				if (!musFile.exists()) {
					musFile.createNewFile();
				}
				if(!imgFile.exists()){
					imgFile.createNewFile();
				}
				music = AccesBdd.readMusic(Integer.parseInt(id), musFile, imgFile);
			} catch (IOException e ){
				e.printStackTrace();
			}
			music = AccesBdd.readMusic(Integer.parseInt(id), new File(getServletContext().getRealPath("/") + "/music/music.wav"), new File(getServletContext().getRealPath("/") + "music/img.png"));
		}

		 */

        request.setAttribute("titleAlbum", titleAlbum);
        request.setAttribute("listeTitres", titles);
        System.out.println("dans album servlet :"+titleAlbum);
        System.out.println("dans album servlet :"+nbSong);
        System.out.println("dans album servlet :"+ Arrays.toString(titles));
        RequestDispatcher rd = getServletContext().getRequestDispatcher(VIEW);


        try {

            rd.forward(request, response);

        } catch (ServletException | IOException e) {

            e.printStackTrace();

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

}
