package com.voidapp.ApplicationWeb.servlets;

import com.voidapp.ApplicationWeb.bdd.AccesBdd;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class AjoutMusiqueServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/newMusic.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        String titre = getParam( request, "Titre");
        String artiste = getParam(request, "Artiste");
        String album = getParam(request, "Album");
        String Style = getParam(request, "Style");

        AccesBdd.addSong(titre,artiste,album,Style);

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    private static String getParam(HttpServletRequest request, String champ){
        String valeur = request.getParameter(champ);
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }

}
