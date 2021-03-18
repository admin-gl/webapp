<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<link href="css/home.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Rubik">
<html>
    <head>
        <title>Home</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" >
    </head>
    <body>

        <div class="topnav">
            <img src="logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
            <a href="index.jsp">Accueil</a>
            <a href="news">Tendances</a>

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
            <a class="sign" href="login">Connexion</a>
            <%
                }
            %>

        </div>

        <h1 style="font-size:11vw">L'illimité, à <br>&emsp; portée de clic</h1>


        <div id="particles-js"></div>

        <script src="particles.js-master/particles.js"></script>
        <script src="particles.js-master/app.js"></script>

        <footer class="bottom-nav">
            <img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
        </footer>

    </body>


</html>