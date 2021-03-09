<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<link href="../css/login.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Rubik">
<html>
    <head>
        <title>Login</title>
    </head>
    <body>

        <div class="topnav">
            <img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
            <a href="../index.jsp">Accueil</a>
            <a href="news">Nouveautés</a>
        </div>

        <div class="box">
            <form action="login" method="post">
                <h1>Connectez-vous</h1>
                <input type="text" name="email" placeholder="e-mail"><br>
                <input type="password" name="password" placeholder="mot de passe"><br>
                <button type="submit">Connexion</button>
                <p>pas encore membre ? <span onclick="window.location.href='inscription'">crééez-vous un compte</span></p>
            </form>
        </div>

        <div id="particles-js"></div>

        <script src="../particles.js-master/particles.js"></script>
        <script src="../particles.js-master/app.js"></script>
    </body>

    <footer class="bottom-nav">
        <img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
    </footer>

</html>
