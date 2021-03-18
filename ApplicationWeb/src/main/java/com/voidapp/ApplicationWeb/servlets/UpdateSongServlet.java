package com.voidapp.ApplicationWeb.servlets;

import com.voidapp.ApplicationWeb.bdd.AccesBdd;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UpdateSongServlet extends HttpServlet {
    public static final String VUE = "/WEB-INF/updateMusic.jsp";
    public static final String RD = "/index.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        response.setContentType("text/html");
        String id = getParam( request, "idS");
        System.out.println("dans servlet update"+id);
        String newTitle = getParam( request, "newTitle");
        AccesBdd.updateSong(id,newTitle);

        this.getServletContext().getRequestDispatcher( RD ).forward( request, response );
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
