<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<link href="../css/InsciptionCSS.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Rubik">
<html>
<head>
    <meta charset="utf-8" />
    <title>Inscription</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.2.0/zxcvbn.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="../css/searchEngin.css" rel="stylesheet" type="text/css">
    <link href="../css/common.css" rel="stylesheet" type="text/css">
</head>

<body>
<div class="topnav">
    <img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
    <a href="../index.jsp">Accueil</a>
    <a href="news">Tendances</a>
    <form class="search-container" method="get" action="search">
        <input name="s" class="searchBar" type="text" placeholder="Rechercher">
        <button class="searchSubmit" type="submit">
            <i class="fa fa-search"></i>
        </button>
    </form>
    <a class="sign" href="logout">Déconnexion</a>
</div>

<div class="box">
    <form action="/profile" method="post">
        <h1>Votre profil</h1>
        <div class="in-box">
            <div>
                <input type="text" id="nom" name="nom" value="<%= request.getSession().getAttribute("nom") %> <%=request.getSession().getAttribute("prenom")%>" size="20" maxlength="60" disabled/>
            </div>
            <div>
                <input type="text" id="email" name="email" value="<%= request.getSession().getAttribute("email") %>" size="20" maxlength="60" disabled/>
            </div>
        </div>
        <h1>Mettre à jour vos informations</h1>


        <div class="in-box">
            <div>
                <input type="password" id="oldpassword" name="oldpassword" placeholder="ancien mot de passe *" value="" size="20" maxlength="20" />
            </div>
            <div class="ps">
                <input type="password" id="newpassword" name="newpassword" placeholder="nouveau mot de passe *" value="" size="20" maxlength="20" />
                <meter max="4" id="password-strength-meter" value="0"></meter>
            </div>
        </div>
        <button type="submit" name="button1" value="chmdp" class="sansLabel">Changer mot de passe</button>
        <div class="in-box">
            <div>
                <input type="text" id="newemail" name="newemail" placeholder="nouvel e-mail *" value="${utilisateur.mail}" size="20" maxlength="60" />
            </div>
            <div>
                <input type="text" id="confirmationmail" name="confirmationmail" placeholder="confirmation nouvel e-mail *" value="${utilisateur.mail}" size="20" maxlength="60" />
            </div>
        </div>

        <button type="submit" name="button1" value="chmail" class="sansLabel">Changer mail</button>

    </form>
</div>

<div id="particles-js"></div>

<script src="../particles.js-master/particles.js"></script>
<script src="../particles.js-master/app.js"></script>
<script>
    const password = document.getElementById('newpassword');
    const meter = document.getElementById('password-strength-meter');
    password.addEventListener('keydown', function () {
        const val = password.value;
        const result = zxcvbn(val);

        // Update the password strength meter
        meter.value = result.score;
    });
</script>

</body>

<footer class="bottom-nav">
    <img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
</footer>
</html>