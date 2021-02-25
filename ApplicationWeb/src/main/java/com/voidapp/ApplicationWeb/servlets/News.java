package com.voidapp.ApplicationWeb.servlets;

import java.io.IOException;
import java.util.Enumeration;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.voidapp.ApplicationWeb.compteUtilisateur.Utilisateur;
import com.voidapp.ApplicationWeb.formulaire.InscriptionFormulaire;

public class News extends HttpServlet {
    public static final String VUE = "/WEB-INF/news.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Affichage de la page d'inscription */
        HttpSession session = request.getSession(true);
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

}
