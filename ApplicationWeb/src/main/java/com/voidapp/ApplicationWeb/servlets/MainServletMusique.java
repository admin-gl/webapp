package com.voidapp.ApplicationWeb.servlets;

import com.voidapp.ApplicationWeb.Musique.Musique;
import com.voidapp.ApplicationWeb.bdd.AccesBdd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServletMusique extends HttpServlet {

	private final String pageName="/WEB-INF/listeningPage.jsp";
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {


		Musique music = new Musique();  // la musique qui sera effectivement transmise au client

		String id = request.getParameter("id");
		
		if (id != null && !id.contentEquals("")) {
			music = AccesBdd.readMusic(Integer.parseInt(id), new File(getServletContext().getRealPath("/") + "/music/music.wav"), new File(getServletContext().getRealPath("/") + "music/img.png"));
		}
        request.setAttribute("music", music);



        RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);
        

        try {

              rd.forward(request, response);

        } catch (ServletException | IOException e) {

              e.printStackTrace();

        }

	}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
