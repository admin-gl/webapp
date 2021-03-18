<%@page import="com.voidapp.ApplicationWeb.Musique.Musique"%>
<%@ page import="com.voidapp.ApplicationWeb.Musique.PochetteAlbum" %>
<%
    String idAlb = request.getParameter("idAlb");
    String titleAlbum = (String) request.getAttribute("titleAlbum");
    Musique[] titles = (Musique[]) request.getAttribute("listeTitres");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<link href="../css/album.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Rubik">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<html>
<head>
    <title>Vous écoutez <%= titleAlbum %></title>
</head>

<body onload="init()">

<div class="topnav">
    <img src="logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
    <a href="index.jsp">Accueil</a>
    <a href="news">Tendances</a>

    <%
        if(request.getSession().getAttribute("email")!=null){
    %>
    <a class="sign" href="logout">Déconnexion</a>
    <a class="sign" href="profile">Profil</a>
    <%
    } else {
    %>
    <form class="searchform" method="post" action="search">
        <input name="searched" class="searchBar" type="text" placeholder="Entrez votre recherche">
        <input class="searchSubmit" type="submit">
    </form>
    <a class="sign" href="inscription">Inscription</a>
    <a class="sign" href="login">Connexion</a>
    <%
        }
    %>

</div>

<div class="container" >
    <div id="imageContainer">
        <img src="/music/<%=titleAlbum%>/cover.jpg" alt=" " style="width:400px;height:400px;">
    </div>
    <div id="songsContainer">
        <h3 style="font-size: x-large"><%=titleAlbum%></h3><br>
        <ul class="w3-ul w3-hoverable" style="width: 350px">
            <%
                for (int k=0;k< titles.length;k++){
                    String t = titles[k].getTitle();
                    int id = Integer.parseInt(titles[k].getId());
            %>
            <li onclick="window.location.href='musique?id=<%=id%>'"><a><%=k+1%> - <%=t%></a></li>
            <br>
            <%
                }
            %>
        </ul>

    </div>
</div>




<div id="particles-js"></div>

<script src="../particles.js-master/particles.js"></script>
<script src="../particles.js-master/app.js"></script>
</body>

<footer class="bottom-nav">
    <img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
</footer>
</html>