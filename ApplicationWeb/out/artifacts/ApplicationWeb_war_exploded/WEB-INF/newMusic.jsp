<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>insertion d'une musique</title>
</head>
<body>
    <form>
        <label>
            titre :
            <input type="text" id="titre">
        </label>
        <label>
            artiste :
            <input type="text" id="artiste">
        </label>
        <label>
            chemin vers le fichier audio (absolut) :
            <input type="text" id="audio">
        </label>
        <label>
            chemin vers l'image (absolut/ optionnel) :
            <input type="text" id="image">
        </label>

        <button type="submit" value="Insertion">insertion</button>
    </form>
    <br>
    ${ messages }
</body>
</html>
