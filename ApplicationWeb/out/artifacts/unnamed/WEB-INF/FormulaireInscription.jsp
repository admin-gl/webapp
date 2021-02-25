<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Inscription</title>
    <link type="text/css" rel="stylesheet" href="form.css" />
</head>
<body>
<form method="post" action="inscription">
    <fieldset>
        <legend>Inscription</legend>
        <p>Formulaire à remplir pour s'inscrire en tant qu'utilisateur.</p>

        <span class="civ"><fieldset>
            <legend>Veuillez sélectionner votre civilité</legend>
            <div>
                <input type="checkbox" id="monsieur" name="civilite" value="monsieur" checked>
                <label for="monsieur">Monsieur</label>
            </div>
            <div>
                <input type="checkbox" id="madame" name="civilite" value="madame">
                <label for="madame">Madame</label>
            </div>
        </fieldset></span>

        <label for="nom">Nom <span class="requis">*</span></label>
        <input type="text" id="nom" name="nom" value="${utilisateur.nom}" size="20" maxlength="60" />
        <span class="erreur">${formulaire.erreurs['nom']}</span>
        <br />

        <label for="prenom">Prenom <span class="requis">*</span></label>
        <input type="text" id="prenom" name="prenom" value="${Utilisateur.prenom}" size="20" maxlength="60" />
        <span class="erreur">${formulaire.erreurs['prenom']}</span>
        <br />

        <label for="email">Adresse email <span class="requis">*</span></label>
        <input type="text" id="email" name="email" value="${Utilisateur.email}" size="20" maxlength="60" />
        <span class="erreur">${formulaire.erreurs['email']}</span>
        <br />

        <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
        <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
        <span class="erreur">${formulaire.erreurs['motdepasse']}</span>
        <br />

        <label for="confirmation">Confirmation du mot de passe <span class="requis">*</span></label>
        <input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" />
        <span class="erreur">${formulaire.erreurs['confirmation']}</span>
        <br />

        <label for="adresse">Adresse de facturation <span class="requis">*</span></label>
        <input type="text" id="adresse" name="adresse" value=""${utilisateur.adressefacturation}" size="20" maxlength="60" />
        <span class="erreur">${formulaire.erreurs['adresse']}</span>
        <br />


        <input type="submit" value="Inscription" class="sansLabel" />
        <br />

        <input type="submit" value="Connexion" class="sansLabel" />
        <br />

        <p class="${empty formulaire.erreurs ? 'succes' : 'erreur'}">${formulaire.resultat}</p>
    </fieldset>
</form>
</body>
</html>