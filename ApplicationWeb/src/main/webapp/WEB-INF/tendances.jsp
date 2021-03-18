<%@page import="com.voidapp.ApplicationWeb.Musique.Musique"%>
<%
    int[][] top = (int[][]) request.getAttribute("top10_likes");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<link href="../css/musique.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Rubik">
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
    <form class="searchform" method="post" action="search">
        <input name="searched" class="searchBar" type="text" placeholder="Entrez votre recherche">
        <input class="searchSubmit" type="submit">
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