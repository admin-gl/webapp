<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<link href="../css/InsciptionCSS.css" rel="stylesheet" type="text/css">
<link href="../css/common.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Rubik">
<script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.2.0/zxcvbn.js"></script>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription</title>

    </head>

    <body>
        <div class="topnav">
            <img src="../logos/planet_void_white_alpha.png" alt=" " style="width:70px;height:70px;">
            <a href="../index.jsp">Accueil</a>
            <a href="news">Tendances</a>
        </div>

        <div class="box">
            <form method="post" action="inscription">
                <h1>Inscription</h1>

                <p>Veuillez sélectionner votre civilité</p>
                <div class="civ">
                    <label>Monsieur<input class="option-input radio" type="radio" id="monsieur" name="civilite" value="monsieur" checked><span class="checkmark"></span></label>
                    <label><input class="option-input radio" type="radio" id="madame" name="civilite" value="madame"><span class="checkmark"></span>Madame</label>
                </div>

                 <div class="in-box">
                     <div>
                        <input type="text" id="nom" name="nom" placeholder="Nom *" value="${utilisateur.nom}" size="20" maxlength="60" />
                        <span class="erreur">${formulaire.erreurs['nom']}</span>
                     </div>
                     <div>
                        <input type="text" id="prenom" name="prenom" placeholder="Prenom *" value="${utilisateur.prenom}" size="20" maxlength="60" />
                        <span class="erreur">${formulaire.erreurs['prenom']}</span>
                     </div>
                 </div>

                <div class="in-box">
                    <div class="ps">
                        <input type="password" id="motdepasse" name="motdepasse" placeholder="mot de passe *" value="" size="20" maxlength="20" />
                        <meter max="4" id="password-strength-meter" value="0"></meter>
                        <span class="erreur">${formulaire.erreurs['motdepasse']}</span>
                    </div>
                    <div>
                        <input type="password" id="confirmation" name="confirmation" placeholder="confirmation du mot de passe *" value="" size="20" maxlength="20" />
                        <span class="erreur">${formulaire.erreurs['confirmation']}</span>
                    </div>
                </div>

                <div class="in-box">
                    <div>
                        <input type="text" id="email" name="email" placeholder="e-mail *" value="${utilisateur.mail}" size="20" maxlength="60" />
                        <span class="erreur">${formulaire.erreurs['email']}</span>
                    </div>
                    <div>
                        <input type="text" id="adresse" name="adresse" placeholder="adresse de facturation *" value="${Utilisateur.adressefacturation}" size="20" maxlength="60" />
                        <span class="erreur">${formulaire.erreurs['adresse']}</span>
                    </div>
                </div>

                <button type="submit" value="Inscription" class="sansLabel">Inscription</button>


                <p class="${empty formulaire.erreurs ? 'succes' : 'erreur'}">${formulaire.resultat}</p>
            </form>
        </div>

        <div id="particles-js"></div>

        <script src="../particles.js-master/particles.js"></script>
        <script src="../particles.js-master/app.js"></script>
        <script>
            const password = document.getElementById('motdepasse');
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