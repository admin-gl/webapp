package com.voidapp.ApplicationWeb.servlets;

import com.voidapp.ApplicationWeb.bdd.AccesBdd;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AjoutMusiqueServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/newMusic.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /*
        String titre = getParam( request, "titre");
        String artiste = getParam(request, "artiste");
        String musPath = getParam(request, "audio");
        String imgPath = getParam(request, "image");
        String format;
        if(musPath != null){
            format = musPath.substring(musPath.length()-4);
            if(titre != null && artiste != null) {
               AccesBdd.writeMusic(titre, artiste, format, musPath, imgPath);
            } else {
                response.sendError(1, "pas de titre ou d'artiste");
            }
        } else {
            response.sendError(1, "pas de chemin vers la musique");
        }


        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    private static String getParam(HttpServletRequest request, String champ){
        String valeur = request.getParameter(champ);
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
*/
    }

}
