<%@page import="com.voidapp.ApplicationWeb.Musique.Musique"%>
<%
String id = request.getParameter("id");
Musique music = (Musique) request.getAttribute("music");
String title = music.getTitle();
String format = music.getFormat();
String path = music.getPath();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Vous écoutez <%= title %></title>
</head>
<body>
	<h1><%= title %></h1>
	<br/>
	<audio controls>
		<source src=<%=path%> type="audio/<%=format%>">
	</audio>
	<br/>
	<p>Voici le paramètre récupéré : <%=id%></p>
	<p>Vous aimerez peut-être aussi : </p>
	<a href="musique?id=1">enigme_1</a><br>
	<a href="musique?id=coucou">enigme_2</a>
</body>
</html>