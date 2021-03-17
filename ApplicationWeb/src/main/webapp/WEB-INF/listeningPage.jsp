<%@page import="com.voidapp.ApplicationWeb.Musique.Musique"%>
<%@ page import="com.voidapp.ApplicationWeb.Musique.PochetteAlbum" %>
<%
	String id = request.getParameter("id");
	Musique music = (Musique) request.getAttribute("music");
	int idAlbum = (int) request.getAttribute("idAlbum");
	String title = music.getTitle();
	String format = music.getFormat();
	String musPath = music.getMusPath();
	String artiste = music.getAuthor();
	String imgPath = music.getImgPath();
	PochetteAlbum[] suggested = (PochetteAlbum[]) request.getAttribute("suggested");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<link href="../css/musique.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	  href="https://fonts.googleapis.com/css?family=Rubik">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="../js-player/lottie.js" type="text/javascript"></script>
<script src="../js-player/player.js"></script>
<html>
	<head>
		<title>Vous écoutez <%= title %></title>
	</head>

	<body onload="init()">

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

		<div class="player">
			<a href="/album?id=<%=idAlbum%>">
				<img src="<%=imgPath%>" alt=" ">
			</a>


			<h3 style="color:white;"><%= title %></h3>
			<h5 style="color:white"><%= artiste%></h5>
			<div id="audio-like-player-container">
				<div id="audio-player-container">
					<audio id="audio" src="<%=musPath%>" type="audio/<%=format%>" preload="auto"></audio>
					<button id="play-icon"></button>
					<span id="current-time" class="time">0:00</span>
					<input type="range" id="seek-slider" max="100" value="0">
					<span id="duration" class="time">0:00</span>
					<output id="volume-output">100</output>
					<input type="range" id="volume-slider" max="100" value="100">
					<button id="mute-icon"></button>
				</div>
				<%
					if(request.getSession().getAttribute("email")!=null){
				%>
				<div class="like" >
					<input type="checkbox" name="like" class="like-btn" onchange="document.getElementById('likes').submit()">
					<i class="fa fa-3x fa-heart"></i>
				</div>
				<%
				} else {
				%>
				<div class="like" style="display: none" >
					<input type="checkbox" name="like" class="like-btn" onchange="document.getElementById('likes').submit()">
					<i class="fa fa-3x fa-heart"></i>
				</div>
				<%
					}
				%>


			</div>

		</div>

		<div id="scrollSuggested">
			<%
				for(int k = 0; k< suggested.length; k++){
					String idA = suggested[k].getId();
					String titleA = suggested[k].getTitle();
					String pathPochetteA = "/music/"+titleA+"/cover.jpg";
					%>
					<a href="album?id=<%=idA%>" class="pochette">
						<img src="<%=pathPochetteA%>" alt=" " style="width:170px;height:170px;">
					</a>
					<%
				}
			%>
		</div>

		<div id="particles-js"></div>

		<script src="../particles.js-master/particles.js"></script>
		<script src="../particles.js-master/app.js"></script>
	</body>

	<footer class="bottom-nav">
		<img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
	</footer>
</html>