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
        String[] covers = new String[10];
        String[] artists = new String[10];
        String[] titres = new String[10];
        for (int i = 0; i<top10_likes[0].length;i++) {
            if(top10_likes[0][i] != -1) {
                covers[i] = "/music/" + AccesBdd.getAlbumFromSongId(top10_likes[0][i] + "") + "/cover.jpg";
                artists[i] = AccesBdd.getArtist(top10_likes[0][i] + "");
                titres[i] = AccesBdd.getTitle(top10_likes[0][i] + "");
            }
        }
        request.setAttribute("top10_likes", top10_likes);
        request.setAttribute("covers", covers);
        request.setAttribute("artistes", artists);
        request.setAttribute("titres", titres);
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
