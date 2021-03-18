package com.voidapp.ApplicationWeb.servlets;

import com.voidapp.ApplicationWeb.bdd.AccesBdd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class DeleteSongServlet extends HttpServlet {

    private static final String HOME = "index.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String id = request.getParameter("id");
        AccesBdd.deleteSongId(id);

        request.getRequestDispatcher(HOME).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}