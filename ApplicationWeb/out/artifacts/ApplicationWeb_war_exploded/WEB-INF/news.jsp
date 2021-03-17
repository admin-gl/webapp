<%@page import="com.voidapp.ApplicationWeb.Musique.Musique"%>
<%
    Musique pochettes[] = (Musique[]) request.getAttribute("pochettes");
    String title = pochettes[1].getTitle();
    String artiste = pochettes[1].getAuthor();
    String imgPath = pochettes[1].getImgPath();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<link href="css/musique.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Rubik">
<html>


<head>
    <title>Home</title>
</head>
<body>

<div class="topnav">
    <img src="logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
    <a href="index.jsp">Accueil</a>
    <a href="news">Nouveautés</a>

    <%
        if(request.getSession().getAttribute("email")!=null){
    %>
    <a class="sign" href="logout">Déconnection</a>
    <a class="sign" href="profile">Profil</a>
    <a class="sign" href="recherche">Recherche</a>
    <%
    } else {
    %>
    <a class="sign" href="inscription">Inscription</a>
    <a class="sign" href="WEB-INF/form.jsp">Connexion</a>
    <%
        }
    %>


</div>

<div id="particles-js"></div>

<script src="particles.js-master/particles.js"></script>
<script src="particles.js-master/app.js"></script>

<br/>
<a style="font-size:30px">Suggestions pour <%=request.getSession().getAttribute("prenom")%> : </a>
<div class="player">
    <img src="<%=imgPath%>" alt=" ">

    <h3 style="color:white;"><%= title %></h3>
    <h5 style="color:white"><%= artiste%></h5>

</div>
<div>

</div>

<footer class="bottom-nav">
    <img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
</footer>
</body>
</html>