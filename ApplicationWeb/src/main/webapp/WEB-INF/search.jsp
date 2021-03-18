<%@ page import="com.voidapp.ApplicationWeb.Musique.Album" %>
<%@ page import="com.voidapp.ApplicationWeb.Musique.Musique" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Album[] albums = (Album[]) request.getAttribute("albums");
    Musique[] musiques = (Musique[]) request.getAttribute("musiques");
    String numPage = request.getParameter("page");
    if(numPage == null){
        numPage = "1";
    }
    String s = request.getParameter("s");

%>
<html>
<head>
    <title>Recherche - Page <%=numPage%></title>
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
        <%
            if(albums.length > 0){

        %>
                <h2>Albums</h2>
        <%
            }
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
            if(musiques.length > 0){
        %>
        <h2>Morceaux</h2>
        <%
            }
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

<div id="navigation-arrows">
    <%
        if(!numPage.equals("1")) {
    %>
    <svg id="left-arrow" onclick="window.location.href='search?s=<%=s%>&page=<%=Integer.parseInt(numPage)-1%>'" viewBox="0 0 18 17" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
        <g id="prev" transform="translate(8.500000, 8.500000) scale(-1, 1) translate(-8.500000, -8.500000)">
            <polygon class="arrow" points="16.3746667 8.33860465 7.76133333 15.3067621 6.904 14.3175671 14.2906667 8.34246869 6.908 2.42790698 7.76 1.43613596"></polygon>
            <polygon class="arrow-fixed" points="16.3746667 8.33860465 7.76133333 15.3067621 6.904 14.3175671 14.2906667 8.34246869 6.908 2.42790698 7.76 1.43613596"></polygon>
            <path d="M-1.48029737e-15,0.56157424 L-1.48029737e-15,16.1929159 L9.708,8.33860465 L-2.66453526e-15,0.56157424 L-1.48029737e-15,0.56157424 Z M1.33333333,3.30246869 L7.62533333,8.34246869 L1.33333333,13.4327013 L1.33333333,3.30246869 L1.33333333,3.30246869 Z"></path>
        </g>
    </svg>
    <%
        }
    %>
        <p id="numPage"><%=numPage%></p>
    <%
        if(albums.length == 5 || musiques.length == 5){
    %>

    <svg id="right-arrow" onclick="window.location.href='search?s=<%=s%>&page=<%=Integer.parseInt(numPage)+1%>'" width="18px" height="17px" viewBox="-1 0 18 17" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
        <g>
            <polygon class="arrow" points="16.3746667 8.33860465 7.76133333 15.3067621 6.904 14.3175671 14.2906667 8.34246869 6.908 2.42790698 7.76 1.43613596"></polygon>
            <polygon class="arrow-fixed" points="16.3746667 8.33860465 7.76133333 15.3067621 6.904 14.3175671 14.2906667 8.34246869 6.908 2.42790698 7.76 1.43613596"></polygon>
            <path d="M-4.58892184e-16,0.56157424 L-4.58892184e-16,16.1929159 L9.708,8.33860465 L-1.64313008e-15,0.56157424 L-4.58892184e-16,0.56157424 Z M1.33333333,3.30246869 L7.62533333,8.34246869 L1.33333333,13.4327013 L1.33333333,3.30246869 L1.33333333,3.30246869 Z"></path>
        </g>
    </svg>
    <%
        }
    %>
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
