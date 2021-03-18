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
</head>
<body>
    <ul>
        <%
            for (Album album : albums) {
        %>
            <li><%=album.getTitre()%></li>
        <%
            }
            for (Musique musique : musiques) {
        %>
            <li><%=musique.getTitle()%></li>
        <%
            }
        %>
    </ul>
</body>
</html>
