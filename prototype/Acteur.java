import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Acteur {

    protected String nom, motDePasse, email, telephone, compagnie;
    protected Scanner scanner = new Scanner(System.in);
    protected Boolean continuer = true;
    protected LocalDateTime dateInscription =  LocalDateTime.now(); //la date où on commence l'inscription
    protected boolean confirmationEmail = false; //pour confirmer le mail
    protected String codeConfirmation = genererCodeConfirmation(); //clé à retourner pour confirmer le mail
    protected int choix = -1;


    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setMdp(String mdp) {
        this.motDePasse = mdp;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public void setCompagnie(String compagnie) {
        this.compagnie = compagnie;
    }

    // Getters
    public String getNom() {
        return nom;
    }
    public String getMdp() {
        return motDePasse;
    }
    public String getEmail() {
        return email;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getCompagnie() {
        return compagnie;
    }


    // Méthodes pour entrer les informations : nom, mdp, email, telephone, compagnie **********************************
    protected void entrerNom() {

        continuer = true;

        while (continuer) {
            System.out.print("Entrez votre nom: ");
            try {
                nom = scanner.nextLine();
                verifierAlpha(nom);
                continuer = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

    }

    public void entrerMDP() {

        continuer = true;

        while (continuer) {
            System.out.print("Entrez votre mot de passe: ");
            try {
                motDePasse = scanner.nextLine();
                if (motDePasse.length() < 8) {
                    throw new IllegalArgumentException("Le mot de passe doit comporter au moins 8 caractères.");
                }
                continuer = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

    }

    protected void entrerEmail() {

        continuer = true;

        while (continuer) {
            System.out.print("Entrez votre e-mail: ");
            try {
                email = scanner.nextLine();
                verifierEmail(email);
                continuer = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

    }

    protected void entrerTelephone() {

        continuer = true;

        while (continuer) {
            System.out.print("Entrez votre téléphone: ");
            try {
                telephone = String.valueOf(scanner.nextInt());
                if (telephone.length() != 10) {
                    throw new IllegalArgumentException("Numéro de téléphone invalide.");
                }
                continuer = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

    abstract protected void entrerCompagnie();


    // Méthodes pour vérifier l'inscription ***************************************************************************
    protected String genererCodeConfirmation() {
        return UUID.randomUUID().toString();
    }

    protected void envoyerEmail(String destinataire) {
        System.out.println("Envoi d'email à : " + destinataire);
        System.out.println("Sujet : Confirmation de l'inscription" );
        System.out.println("Contenu : Cliquez sur le lien pour confirmer votre inscription  et saisisez le code :" + codeConfirmation);
    }

    /*
     * Cette méthode permet de confirmer l'inscription
     * elle modifie ou pas la valeur de la variable booléene confirmationEmail
     * Si l'utilisateur entre la bonne clé après 24 heures, la variable confirmationEmail renvoie un True
     */
    public void confirmerInscription() {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Entrez le code de confirmation reçu par email : ");
            String cle = scanner.nextLine();

            //codeConfirmation vérifie l'exactitude de la clé
            //dateInscription est une Date à quoi on a ajouté une période de 24 heures
            if (codeConfirmation.equals(cle) && dateInscription.plusHours(24).isAfter(LocalDateTime.now())) {
                this.confirmationEmail = true;
                System.out.println("Inscription avec succès !");
            } else {
                System.out.println("Lien de confirmation invalide ou expiré.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la confirmation : " + e.getMessage());
        }
    }


    // Méthodes pour vérifier la validité des entrées *****************************************************************

    // Vérifier qu'un String contient uniquement des caractères alphanumériques
    protected static void verifierAlphaNum(String string) {
        if (!string.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("L'entrée doit contenir uniquement des caractères alphanumériques.");
        }
    }

    // Vérifier qu'un String contient uniquement des caractères alphabétiques
    protected static void verifierAlpha(String string) {
        if (!string.matches("[A-Za-z]+")) {
            throw new IllegalArgumentException("L'entrée doit contenir uniquement des caractères alphabétiques.");
        }
    }

    protected void verifierEmail(String email) {
        String emailPattern = "[A-Za-z0-9-_\\.]+@[a-z]+\\.(com|fr|ca|io|web)";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Adresse courriel invalide.");
        }
    }

    protected static void pseudoUnique(String pseudo) {
        for (Utilisateur utilisateur : Systeme.getInstance().getUtilisateurs()) {
            if (utilisateur.getPseudo().equals(pseudo)) {
                throw new IllegalArgumentException("Ce pseudo existe déjà.");
            }
        }
    }

    // Méthodes de fonctionnalité *************************************************************************************

    abstract public void sInscrire();

    abstract public void seConnecter();

    abstract public void modifierProfil();

}
