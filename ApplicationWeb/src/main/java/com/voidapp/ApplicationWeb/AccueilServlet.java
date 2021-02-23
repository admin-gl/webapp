package com.voidapp.ApplicationWeb;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "accueilServlet", value = "/accueil-servlet")
public class AccueilServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Bienvenue !";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}