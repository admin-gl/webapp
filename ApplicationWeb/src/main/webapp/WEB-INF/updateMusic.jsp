<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("id");
    String oldTitle = request.getParameter("oldTitle");
%>
<link href="../css/adminCss.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Rubik">
<html>
<head>
    <title>modification d'une musique</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" >
</head>
<body>
<div class="topnav">
    <img src="logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
    <a href="index.jsp">Accueil</a>
    <a href="news">Nouveautés</a>

    <%
        if(request.getSession().getAttribute("email")!=null){
    %>
    <a class="sign" href="logout">Déconnection</a>
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
<div id="particles-js"></div>

<script src="particles.js-master/particles.js"></script>
<script src="particles.js-master/app.js"></script>

<div class="box">
    <form action="edit" method="post">
        <h1>Modification d'un titre</h1>
        <div class="in-box">
            <input type="text" name="newTitle" placeholder="<%=oldTitle%>"><br>
            <input type="text" name="idS" value="<%=id%>" style="display: none">
        </div>
        <button type="submit">Mettre à jour</button>
    </form>
</div>
<br>
<%
    if (request.getSession().getAttribute("email")!=null){
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
