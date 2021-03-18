<%@page import="com.voidapp.ApplicationWeb.Musique.Musique"%>
<%
    int[][] top = (int[][]) request.getAttribute("top10_likes");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<link href="../css/musique.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Rubik">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="../css/searchEngin.css" rel="stylesheet" type="text/css">
<html>


<head>
    <title>Hit's du moment</title>
</head>
<body>

<div class="topnav">
    <img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
    <a href="../index.jsp">Accueil</a>
    <a href="tendances">Tendances</a>
    <%
        if(request.getSession().getAttribute("email")!=null){
    %>
    <form class="search-container" method="get" action="search">
        <input name="s" class="searchBar" type="text" placeholder="Rechercher">
        <button class="searchSubmit" type="submit">
            <i class="fa fa-search"></i>
        </button>
    </form>
    <a class="sign" href="logout">Déconnexion</a>
    <a class="sign" href="profile">Profil</a>
    <%
    } else {
    %>
    <a class="sign" href="inscription">Inscription</a>
    <a class="sign" href="../WEB-INF/login.jsp">Connexion</a>
    <%
        }
    %>

</div>

    <%
        int i;
        for(i = 0; i<10;i++){
            if(top[0][i] != -1){
    %>
        <p>id_musique = <%=top[0][i]%> ; nbLikes = <%=top[1][i]%></p>
        <br>
    <%
            }
        }
    %>

<div id="particles-js"></div>

<script src="../particles.js-master/particles.js"></script>
<script src="../particles.js-master/app.js"></script>

<br/>

<div>

</div>

<footer class="bottom-nav">
    <img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
</footer>
</body>
</html>