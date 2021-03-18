<%@ page import="com.voidapp.ApplicationWeb.Musique.Album" %>
<%@ page import="com.voidapp.ApplicationWeb.Musique.Musique" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Album[] albums = (Album[]) request.getAttribute("albums");
    Musique[] musiques = (Musique[]) request.getAttribute("musiques");

%>
<html>
<head>
    <title>Recherche</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Rubik">
    <link href="../css/searchEngin.css" rel="stylesheet" type="text/css">
</head>
<body>
    <ul class="w3-ul w3-hoverable">
        <h2>Albums</h2>
        <%
            for (Album album : albums) {
                String pathCover = "/music/" + album.getTitre() + "/cover.jpg";
                String t = album.getTitre();
                String art = album.getArtiste();
                String style = album.getStyle();
                String id = album.getId();
        %>
            <li onclick="window.location.href='album?id=<%=id%>'">
                <img src="<%=pathCover%>" alt="" style="width: 100px; height: 100px;">
                <div class="informations">
                    <h4><%=t%></h4>
                    <h6><%=art%></h6>
                    <p><%=style%></p>
                </div>
            </li>
        <h2>Morceaux</h2>
        <%
            }
            for (Musique musique : musiques) {
                String t = musique.getTitle();
                String art = musique.getAuthor();
                String pathCover = musique.getImgPath();
                String id = musique.getId();
        %>
        <li onclick="window.location.href='musique?id=<%=id%>'">
            <img src="<%=pathCover%>" alt="" style="width: 100px; height: 100px;">
            <div class="informations">
                <h4><%=t%></h4>
                <h6><%=art%></h6>
            </div>
        </li>
        <%
            }
        %>
    </ul>
</body>
</html>
