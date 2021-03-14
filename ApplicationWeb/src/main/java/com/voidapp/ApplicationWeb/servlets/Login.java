package com.voidapp.ApplicationWeb.servlets;

import com.voidapp.ApplicationWeb.bdd.AccesBdd;
import com.voidapp.ApplicationWeb.bdd.Hasher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    private static final String THIS = "WEB-INF/login.jsp";
    private static final String HOME = "index.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(THIS).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fname = AccesBdd.getFName(email);
        String lname = AccesBdd.getLName(email);

        if(AccesBdd.checkUser(email, Hasher.encode(password))) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("prenom", fname);
            session.setAttribute("nom", lname);
            request.getRequestDispatcher(HOME).forward(request, response);
        } else {
            request.getRequestDispatcher(THIS).forward(request, response);
        }
    }
}  
