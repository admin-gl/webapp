<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<link href="css/home.css" rel="stylesheet" type="text/css">
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
            <a class="sign" href="logout">Déconnexion</a>
            <%
            } else {
            %>
            <a class="sign" href="inscription">Inscription</a>
            <a class="sign" href="login">Connexion</a>
            <%
                }
            %>
        </div>

        <h1>L'illimité, à <br>&emsp; portée de clic</h1>

        <div id="particles-js"></div>

        <script src="particles.js-master/particles.js"></script>
        <script src="particles.js-master/app.js"></script>

        <div class="bottom-nav">
            <img src="logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
        </div>

    </body>


</html>