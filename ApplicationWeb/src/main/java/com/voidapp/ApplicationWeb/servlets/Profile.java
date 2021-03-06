package com.voidapp.ApplicationWeb.servlets;

import com.voidapp.ApplicationWeb.bdd.AccesBdd;
import com.voidapp.ApplicationWeb.bdd.Hasher;
import com.voidapp.ApplicationWeb.formulaire.InscriptionFormulaire;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Profile extends HttpServlet {

    private static final String THIS = "WEB-INF/profilepage.jsp";
    private static final String HOME = "index.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(THIS).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String mail = session.getAttribute("email").toString();
        String button = request.getParameter("button1");
        if ("chmail".equals(button)) {
            String newemail = request.getParameter("newemail");
            String confnewemail = request.getParameter("confirmationmail");
            if (newemail.equals(confnewemail)){
                AccesBdd.chMail(mail, newemail);
                session.setAttribute("email", newemail);
            }
            request.getRequestDispatcher(HOME).forward(request, response);
        } else if ("chmdp".equals(button)) {
            String oldpwd = request.getParameter("oldpassword");
            String newpwd = request.getParameter("newpassword");
            if(AccesBdd.checkUser(mail, Hasher.encode(oldpwd))) {
                AccesBdd.chMdp(mail, newpwd);
                request.getRequestDispatcher(HOME).forward(request, response);
            } else {
                request.getRequestDispatcher(THIS).forward(request, response);
            }
        }

    }
}
