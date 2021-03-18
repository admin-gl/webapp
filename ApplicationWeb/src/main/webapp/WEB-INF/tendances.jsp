<%
    int[][] top = (int[][]) request.getAttribute("top10_likes");
    String[] titres = (String[]) request.getAttribute("titres");
    String[] artistes = (String[]) request.getAttribute("artistes");
    String[] covers = (String[]) request.getAttribute("covers");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Rubik">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="../css/searchEngin.css" rel="stylesheet" type="text/css">
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link href="../css/tendance.css" rel="stylesheet" type="text/css">
<html>


<head>
    <title>Tendances du moment</title>
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
    <div class="top10">
    <%
        int i;
        for(i = 0; i<10;i++){
            if(top[0][i] != -1){
    %>
        <div id="classement-info-container"onclick="window.location.href='musique?id=<%=top[0][i]%>'">
            <h1><%=i+1%></h1>
            <div id="info-container">
                <img src="<%=covers[i]%>" alt="" onerror="this.onerror=null;this.src='../logos/planet_void_white_alpha.png'">
                <div id="informations">
                    <h4><%=titres[i]%></h4>
                    <h6><%=artistes[i]%></h6>
                </div>
                <div id="likes">
                    <p><%=top[1][i]%><i class="fa fa-2x fa-heart"></i></p>

                </div>
            </div>
        </div>
    <%
            }
        }
    %>
    </div>

<div id="particles-js"></div>

<script src="../particles.js-master/particles.js"></script>
<script src="../particles.js-master/app.js"></script>

<div style="height: 100px"></div>

<footer class="bottom-nav">
    <img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
</footer>
</body>
</html>