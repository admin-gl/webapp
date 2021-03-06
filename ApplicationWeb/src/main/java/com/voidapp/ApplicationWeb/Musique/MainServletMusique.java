package com.voidapp.ApplicationWeb.Musique;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServletMusique extends HttpServlet {
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
		
		List<Musique> listMusic = new ArrayList<Musique>();
		
		Musique music = new Musique();  // la musique qui sera effectivement transmise au client
		Musique enigme1 = new Musique("1", "enigme_1", "wav", "Kilian", "music/enigme_1.wav");
		Musique enigme2 = new Musique("2", "enigme_2", "wav", "Kilian", "music/enigme_2.wav");
		
		listMusic.add(enigme1);
		listMusic.add(enigme2);
		
		String id = request.getParameter("id");
		
		if (id != null && !id.contentEquals("")) {
			if (id.contentEquals("1")) {
				music = enigme1;
			} else {
				music = enigme2;
			}
		}
        
        request.setAttribute("music", music);
		
        String pageName="/listeningPage.jsp";

        RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);
        

        try {

              rd.forward(request, response);

        } catch (ServletException e) {

              e.printStackTrace();

        } catch (IOException e) {

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
