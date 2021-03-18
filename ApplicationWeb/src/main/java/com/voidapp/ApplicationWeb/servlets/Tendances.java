package com.voidapp.ApplicationWeb.servlets;
import com.voidapp.ApplicationWeb.bdd.AccesBdd;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Tendances extends HttpServlet {
    private final String VUE="/WEB-INF/tendances.jsp";

    public void doProcess(HttpServletRequest request, HttpServletResponse response){
        int[][] top10_likes = AccesBdd.getTopTen();
        request.setAttribute("top10_likes", top10_likes);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(VUE);

        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        doProcess(request, response);
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        doProcess(request, response);
    }
}
