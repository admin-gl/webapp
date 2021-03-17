package com.voidapp.ApplicationWeb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Logout extends HttpServlet {

    private static final String HOME = "index.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session=request.getSession();
        session.invalidate();

        request.getRequestDispatcher(HOME).forward(request, response);
    }
}