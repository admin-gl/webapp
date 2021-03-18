package com.voidapp.ApplicationWeb.servlets;

import com.voidapp.ApplicationWeb.Musique.Musique;
import com.voidapp.ApplicationWeb.Musique.PochetteAlbum;
import com.voidapp.ApplicationWeb.bdd.AccesBdd;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainServletMusique extends HttpServlet {

	private final String pageName="/WEB-INF/listeningPage.jsp";
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");

		String album = AccesBdd.getAlbumFromSongId(id);
		String artist = AccesBdd.getArtist(id);
		String titre = AccesBdd.getTitle(id);


		int nbSim = AccesBdd.getNbSimilarAlb(id);
		int idAlbum = AccesBdd.getAlbumIdFromAlbumTitle(album);
		PochetteAlbum[] SuggestedAlbums = new PochetteAlbum[nbSim];
		//les id des albums de même style
		String[] suggested = AccesBdd.getSimilarAlbum(id,nbSim);

		for (int k=0;k<nbSim;k++){
			if(suggested[k]!=null){
				SuggestedAlbums[k] = new PochetteAlbum(suggested[k], AccesBdd.getAlbumTitle(suggested[k]));
			} else{
				SuggestedAlbums[k] = null;
			}
		}

		/* cette partie de code était dédiée à la récupération des blobs de musique/image sotckées en ligne
		* c'était fonctionnel jusqu'au moment ou les serveurs d'OVH ont pris feu
		* on a alors décidé de faire les choses plus simplement, avec des données en local

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

		Musique music = new Musique(id, titre, "mp3", artist, "/music/"+album+"/"+titre+".mp3", "/music/"+album+"/cover.jpg");

        request.setAttribute("music", music);
        request.setAttribute("suggested", SuggestedAlbums);
		request.setAttribute("idAlbum", idAlbum);
		String titleA = SuggestedAlbums[0].getTitle();
        System.out.println("/music/"+titleA+"/cover.jpg");
        System.out.println(music.getMusPath());
		HttpSession session = request.getSession();
        if(session.getAttribute("email") != null){
        	session.setAttribute("idMusique", music.getId());
			ArrayList<Integer> temp = (ArrayList<Integer>) session.getAttribute("likes");
			System.out.println(temp.contains(Integer.parseInt(id)));
			if(temp.contains(Integer.parseInt(id))){
				request.setAttribute("liked", "1");
			} else {
				request.setAttribute("liked", "0");
			}
		} else {
			request.setAttribute("liked", "0");
		}

        RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);


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
