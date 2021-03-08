package com.voidapp.ApplicationWeb.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voidapp.ApplicationWeb.compteUtilisateur.Utilisateur;
import com.voidapp.ApplicationWeb.formulaire.InscriptionFormulaire;

public class News extends HttpServlet {
    public static final String VUE = "/WEB-INF/news.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

    }

}
