package com.voidapp.ApplicationWeb.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.voidapp.ApplicationWeb.compteUtilisateur.Utilisateur;
import com.voidapp.ApplicationWeb.formulaire.InscriptionFormulaire;

public class Inscription extends HttpServlet {
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "formulaire";
    public static final String VUE = "/WEB-INF/FormulaireInscription.jsp";
    public static final String HOME = "/index.jsp";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        /* Préparation de l'objet formulaire */
        InscriptionFormulaire formulaire = new InscriptionFormulaire();

        /* Appel au traitement et à la validation de la requête, et récupération du bean en résultant */
        Utilisateur utilisateur = formulaire.inscrireUtilisateur( request );

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, formulaire );
        request.setAttribute( ATT_USER, utilisateur );

        if((boolean) request.getAttribute("isOk")){
            request.removeAttribute("isOk");
            this.getServletContext().getRequestDispatcher( HOME ).forward( request, response );
        } else {
            request.removeAttribute("isOk");
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }

}
