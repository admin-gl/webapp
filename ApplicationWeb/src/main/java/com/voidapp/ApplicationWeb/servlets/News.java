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

        int i = AccesBdd.getTotalMusic();
        Musique pochettes[] = new Musique[i];
        File F[] = new File[i];
        for (int k=0;k<i;k++){
            F[k] = new File(getServletContext().getRealPath("/") + "music/imgP"+k+".png");
            try {
                if(!F[k].exists()){
                    F[k].createNewFile();
                }
            } catch (IOException e ){
                e.printStackTrace();
            }
            pochettes = (AccesBdd.getPochette(F));
        }

        request.setAttribute("pochettes", pochettes);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(VUE);

        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        HttpSession session = request.getSession(true);
        doProcess(request, response);
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        doProcess(request, response);
    }
}
