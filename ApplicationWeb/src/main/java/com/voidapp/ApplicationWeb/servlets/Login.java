package com.voidapp.ApplicationWeb.servlets;

import com.voidapp.ApplicationWeb.bdd.Validate;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        if(Validate.checkUser(email, pass))
        {
            HttpSession session=request.getSession();
            session.setAttribute("email",email);
            RequestDispatcher rs = request.getRequestDispatcher("/news");
            rs.forward(request, response);
        }
        else
        {
            out.println("Username or Password incorrect");
            RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
            rs.include(request, response);
        }
    }

}