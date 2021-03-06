<%@page import="com.voidapp.ApplicationWeb.Musique.Musique"%>
<%
	String id = request.getParameter("id");
	Musique music = (Musique) request.getAttribute("music");
	String title = music.getTitle();
	String format = music.getFormat();
	String path = music.getPath();
	String artiste = music.getAuthor();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<link href="../css/musique.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	  href="https://fonts.googleapis.com/css?family=Rubik">
<script src="../js-player/lottie.js" type="text/javascript"></script>
<script src="../js-player/player.js"></script>
<html>
	<head>
		<title>Vous écoutez <%= title %></title>
	</head>

	<body onload="init()">

		<div class="topnav">
			<img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
			<a href="../index.jsp">Accueil</a>
			<a href="news">Nouveautés</a>
			<a class="sign" href="logout">Déconnexion</a>
		</div>

		<div class="player">
			<img src="../logos/planet_void_white_alpha.png" alt=" ">

			<h3 style="color:white;"><%= title %></h3>
			<h5 style="color:white"><%= artiste%></h5>
			<div id="audio-like-player-container">
				<div id="audio-player-container">
					<audio id="audio" src=<%=path%> type="audio/<%=format%>" preload="auto"></audio>
					<button id="play-icon"></button>
					<span id="current-time" class="time">0:00</span>
					<input type="range" id="seek-slider" max="100" value="0">
					<span id="duration" class="time">0:00</span>
					<output id="volume-output">100</output>
					<input type="range" id="volume-slider" max="100" value="100">
					<button id="mute-icon"></button>
				</div>
				<button id="like-icon"></button>
			</div>
		</div>

		<a href="musique?id=1">enigme_1</a><br>
		<a href="musique?id=coucou">enigme_2</a>

		<div id="particles-js"></div>

		<script src="../particles.js-master/particles.js"></script>
		<script src="../particles.js-master/app.js"></script>
	</body>

	<footer class="bottom-nav">
		<img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
	</footer>
</html>