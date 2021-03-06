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
        String valuePage = request.getParameter("page");
        int page;
        if(valuePage == null){
            page = 1;
        } else {
            page = Integer.parseInt(valuePage);
        }
        SearchResult res = AccesBdd.search(searched, page-1);
        request.setAttribute("albums", res.albums);
        request.setAttribute("musiques", res.musiques);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);

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
