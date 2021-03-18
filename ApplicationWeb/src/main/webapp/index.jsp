<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<link href="css/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Rubik">
<link href="css/searchEngin.css" rel="stylesheet" type="text/css">
<link href="css/home.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<html>
    <head>
        <title>Home</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" >
    </head>
    <body>

        <div class="topnav">
            <img src="logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
            <a href="index.jsp">Accueil</a>
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

        <h1 style="font-size:11vw">L'illimité, à <br>&emsp; portée de clic</h1>


        <div id="particles-js"></div>

        <script src="particles.js-master/particles.js"></script>
        <script src="particles.js-master/app.js"></script>

        <%
            if (request.getSession().getAttribute("email")!=null &&
                    request.getSession().getAttribute("admin").equals("1")){
        %>
        <footer class="bottom-nav">
            <img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
            <a href="admin" style="
        float: right;
        font-family:Rubik ;
        font-weight:
        bold;text-align: center;
        padding: 15px 18px;
        text-decoration: none;
        font-size: 20px;">Administration</a>
        </footer>
        <%
            } else {
        %>
        <footer class="bottom-nav">
            <img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
        </footer>
        <%
            }
        %>


    </body>


</html>