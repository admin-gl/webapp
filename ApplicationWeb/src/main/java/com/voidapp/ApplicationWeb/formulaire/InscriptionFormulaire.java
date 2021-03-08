package com.voidapp.ApplicationWeb.formulaire;
import com.voidapp.ApplicationWeb.bdd.Hasher;
import com.voidapp.ApplicationWeb.compteUtilisateur.Utilisateur;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public final class InscriptionFormulaire {

    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "motdepasse";
    private static final String CHAMP_CONF   = "confirmation";
    private static final String CHAMP_NOM    = "nom";
    private static final String CHAMP_PRENOM = "prenom";
    private static final String CHAMP_ADRESSE = "adresse";
    private static final String CHAMP_CIVILITE = "civilite";

    private String resultat;
    private final Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur inscrireUtilisateur(HttpServletRequest request ) {
        String email = getValeurChamp( request, CHAMP_EMAIL );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String confirmation = getValeurChamp( request, CHAMP_CONF );
        String nom = getValeurChamp( request, CHAMP_NOM );
        String prenom = getValeurChamp( request, CHAMP_PRENOM );
        String adresse = getValeurChamp( request, CHAMP_ADRESSE );
        String civilite = request.getParameter(CHAMP_CIVILITE);
        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setDateadhesion(new java.sql.Date(new Date().getTime()));

        if(civilite.equals("monsieur")){
            utilisateur.setCivilite(Utilisateur.Civilite.monsieur);
        }else{
            utilisateur.setCivilite(Utilisateur.Civilite.madame);
        }

        try {
            validationEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        utilisateur.setMail( email );

        try {
            validationMotsDePasse( motDePasse, confirmation );
            utilisateur.setMdp(Hasher.encode(motDePasse));
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }

        try {
            validationNonVide("Nom", nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        utilisateur.setNom( nom );

        try {
            validationNonVide("Prenom", prenom );
        } catch ( Exception e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        utilisateur.setPrenom( prenom );

        try {
            validationNonVide("Adresse", adresse );
        } catch ( Exception e ) {
            setErreur( CHAMP_ADRESSE, e.getMessage() );
        }
        adresse.replaceAll("'", "\'");
        utilisateur.setAdressefacturation(adresse);

        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'inscription.";
            resultat = resultat + utilisateur.AddUser();
        } else {
            resultat = "Échec de l'inscription.";
        }

        return utilisateur;
    }

    /**
     * Valide l'adresse mail saisie.
     */
    private void validationEmail( String email ) throws Exception {

        String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern ptr = Pattern.compile(regex);
        Matcher mtc = ptr.matcher(email);
        if ( email != null && email.trim().length() != 0 ) {
            if (!mtc.matches()){
                throw new Exception( "Merci de saisir une adresse mail valide." );
            }
        } else {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }

    /**
     * Valide les mots de passe saisis.
     */
    private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception{
        if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
            if (!motDePasse.equals(confirmation)) {
                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if (motDePasse.trim().length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    /**
     * Valide le nom d'utilisateur saisi.
     */
    private void validationNonVide( String champ,String valeurchamp ) throws Exception {
        if (valeurchamp == null) {
            throw new Exception( "Le champ "+ champ + " doit contenir au moins 1 caractère." );
        }
    }

    /**
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /**
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur.trim();
        }
    }
}
