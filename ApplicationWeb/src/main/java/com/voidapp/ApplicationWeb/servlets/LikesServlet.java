package com.voidapp.ApplicationWeb.servlets;

import com.voidapp.ApplicationWeb.bdd.AccesBdd;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LikesServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        boolean liked = request.getParameter("data").matches("true");
        String idMusique = (String) session.getAttribute("idMusique");
        String email = (String) session.getAttribute("email");

        AccesBdd.updateLikes(email, idMusique, liked);
        ArrayList<Integer> likes = (ArrayList<Integer>) session.getAttribute("likes");
        System.out.println(likes);
        if(liked){
            likes.add(Integer.parseInt(idMusique));
        } else {
            likes.remove((Object) Integer.parseInt(idMusique));
        }
        session.setAttribute("likes", likes);
        System.out.println(likes);
    }
}
