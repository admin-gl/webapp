package com.voidapp.ApplicationWeb.servlets;

import com.voidapp.ApplicationWeb.bdd.AccesBdd;
import com.voidapp.ApplicationWeb.formulaire.SearchResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchServlet extends HttpServlet {

    private final String pageName = "/WEB-INF/search.jsp";

    private void doProcess(HttpServletRequest request, HttpServletResponse response){
        String searched = request.getParameter("s");
        if(searched == null){
            searched = request.getParameter("searched");
        }
        System.out.println(searched);
        SearchResult res = AccesBdd.search(searched);
        request.setAttribute("albums", res.albums);
        request.setAttribute("musiques", res.musiques);
        System.out.println(res.albums.length);
        System.out.println(res.musiques.length);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName + "?s="+ searched);

        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doProcess(request, response);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doProcess(req, resp);
    }
}
