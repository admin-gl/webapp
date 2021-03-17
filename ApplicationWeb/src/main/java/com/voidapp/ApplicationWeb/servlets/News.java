package com.voidapp.ApplicationWeb.servlets;
import com.voidapp.ApplicationWeb.Musique.Musique;
import com.voidapp.ApplicationWeb.bdd.AccesBdd;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class News extends HttpServlet {
    private final String VUE="/WEB-INF/news.jsp";

    public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        HttpSession session = request.getSession(true);
        doProcess(request, response);
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        doProcess(request, response);
    }
}
