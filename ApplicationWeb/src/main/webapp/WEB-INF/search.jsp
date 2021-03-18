<%@ page import="com.voidapp.ApplicationWeb.Musique.Album" %>
<%@ page import="com.voidapp.ApplicationWeb.Musique.Musique" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Album[] albums = (Album[]) request.getAttribute("albums");
    Musique[] musiques = (Musique[]) request.getAttribute("musiques");
%>
<html>
<head>
    <title>Recherche</title>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Rubik">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link href="../css/search.css" rel="stylesheet" type="text/css">
    <link href="../css/searchEngin.css" rel="stylesheet" type="text/css">
    <link href="../css/common.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
    <a class="sign" href="login">Connexion</a>
    <%
        }
    %>

</div>

<div class="resultats">
    <ul class="w3-ul w3-hoverable">
        <h2>Albums</h2>
        <%
            for (Album album : albums) {
                String pathCover = "/music/" + album.getTitre() + "/cover.jpg";
                String t = album.getTitre();
                String art = album.getArtiste();
                String style = album.getStyle();
                String id = album.getId();
        %>
        <li class="album" onclick="window.location.href='album?id=<%=id%>'">
            <img src="<%=pathCover%>" alt="" style="width: 100px; height: 100px;" onerror="this.onerror=null;this.src='../logos/planet_void_white_alpha.png'">
            <div class="informations">
                <h4><%=t%></h4>
                <h6><%=art%></h6>
                <p><%=style%></p>
            </div>
        </li>

        <%
            }
        %>
        <h2>Morceaux</h2>
        <%
            for (Musique musique : musiques) {
                String t = musique.getTitle();
                String art = musique.getAuthor();
                String pathCover = musique.getImgPath();
                String id = musique.getId();
        %>
        <li class="music" onclick="window.location.href='musique?id=<%=id%>'">
            <img src="<%=pathCover%>" alt="" style="width: 100px; height: 100px;" onerror="this.onerror=null;this.src='../logos/planet_void_white_alpha.png'">
            <div class="informations">
                <h4><%=t%></h4>
                <h6><%=art%></h6>
            </div>
        </li>
        <%
            }
        %>
    </ul>
</div>
<div style="height: 100px"></div>
<div id="particles-js"></div>

<script src="../particles.js-master/particles.js"></script>
<script src="../particles.js-master/app.js"></script>

<footer class="bottom-nav">
    <img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
</footer>

</body>
</html>
