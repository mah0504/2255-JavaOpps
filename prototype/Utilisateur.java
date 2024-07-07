import java.util.*;
import java.util.regex.*;

public class Utilisateur implements Acteur {
    private String nom, prenom, pseudo, mdp, email, telephone;
    private ArrayList<Interet> listeInterets;
    Scanner scanner = new Scanner(System.in);
    Boolean continuer = true;

    public Utilisateur() {
        // rien mettre?? ou sinscrire??
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public ArrayList<Interet> getListeInterets() {
        return listeInterets;
    }

    public void setListeInterets(ArrayList<Interet> listeInterets) {
        this.listeInterets = listeInterets;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", motDePasse='" + mdp + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", listeInterets=" + listeInterets +
                '}';
    }

    @Override
    public void sInscrire() {

        entrerPseudo();
        entrerNom();
        entrerPrenom();
        entrerMDP();
        entrerEmail();
        entrerTelephone();

        //todo interet
        //todo e-mail de confirmation

        //une fois que e-mail confirmé,
        Systeme.getInstance().ajouterUtilisateur(this);
        System.out.println("Inscription réussie!");

    }

    public void entrerPseudo() {

        continuer = true;

        while (continuer) {
            System.out.print("Entrez votre pseudo: ");
            try {
                pseudo = scanner.nextLine();
                verifierAlphaNum(pseudo);
                pseudoUnique(pseudo);
                continuer = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

    }

    public void entrerNom() {

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

    public void entrerPrenom() {

        continuer = true;

        while (continuer) {
            System.out.print("Entrez votre prenom: ");
            try {
                prenom = scanner.nextLine();
                verifierAlpha(prenom);
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
                mdp = scanner.nextLine();
                if (mdp.length() < 8) {
                    throw new IllegalArgumentException("Le mot de passe doit comporter au moins 8 caractères.");
                }
                continuer = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

    }

    public void entrerEmail() {

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

    public void entrerTelephone() {

        continuer = true;

        while (continuer) {
            System.out.print("Entrez votre téléphone: ");
            try {
                telephone = String.valueOf(scanner.nextInt());
                if (telephone.length() > 10) {
                    throw new IllegalArgumentException("Numéro de téléphone invalide.");
                }
                continuer = false;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }





    /*@Override
    public void sInscrire() {
        try (Scanner scanner = new Scanner(System.in)) {


            Interet.initialiserListeInterets();

            System.out.println("Choisissez vos intérêts (entrez le numéro):");
            for (int i = 0; i < listeInterets.size(); i++) {
                System.out.println((i + 1) + ". " + listeInterets.get(i).getNom());
            }

            for (int i = 0; i < 10; i++) {
                System.out.print("Choix " + (i + 1) + ": ");
                int choix = scanner.nextInt();
                if (choix >= 1 && choix <= listeInterets.size()) {
                    Interet interet = listeInterets.get(choix - 1);
                    interet.souscrire(this);
                } else {
                    System.out.println("Choix invalide. Réessayez.");
                    i--;
                }
            }
    }*/

    @Override
    public void seConnecter() {

        continuer = true;
        Utilisateur utilisateur;

        while (continuer) {

            try {

                System.out.print("Entrez votre pseudo : ");
                pseudo = scanner.nextLine();
                System.out.print("Entrez votre mot de passe : ");
                mdp = scanner.nextLine();

                int index = chercherPseudo(pseudo);
                if (index >= 0) {
                    utilisateur = Systeme.getInstance().getUtilisateurs().get(index);
                    if (! mdp.equals(utilisateur.getMdp())) {
                        throw new IllegalArgumentException("Pseudo ou mot de passe invalide.");
                    }
                }
                continuer = false;

                //todo si connexion réussie, dans le main remplacer l'user par celui Systeme.getInstance().getUtilisateurs().get(index)
                // genre retourner -1 si on veut annuler connexion et retourner au menu principal ou index si connexion réussie

            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }



    /*@Override
    public void seConnecter() {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Entrez votre pseudo : ");
            String pseudo = scanner.nextLine();
            System.out.print("Entrez votre mot de passe : ");
            String motDePasse = scanner.nextLine();


            boolean trouve = false;
            for (Utilisateur utilisateur : listeUsers) {
                if (utilisateur.getPseudo().equals(pseudo) && utilisateur.getMdp().equals(motDePasse)) {
                    System.out.println("Connexion réussie !!");
                    trouve = true;
                    break;
                }
            }

            if (!trouve) {
                System.out.println("Pseudo ou mot de passe invalide ou inexistants.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
        }
    }*/

    @Override
    public void modifierProfil() {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Que voulez-vous modifier ?");
            System.out.println("1. Nom");
            System.out.println("2. Prénom");
            System.out.println("3. Email");
            System.out.println("4. Mot de passe");
            System.out.println("5. Numéro de téléphone");
            System.out.println("0. Quitter");

            int choix = -1;
            while (choix != 0) {
                System.out.print("Votre choix : ");
                choix = scanner.nextInt();
                scanner.nextLine(); // Pour consommer le retour à la ligne

                switch (choix) {
                    case 1:
                        entrerNom();
                        break;
                    case 2:
                        entrerPrenom();
                        break;
                    case 3:
                        entrerEmail();
                        break;
                    case 4:
                        entrerMDP();
                        break;
                    case 5:
                        entrerTelephone();
                        break;
                    case 0:
                        System.out.println("Modification terminée.");
                        break;
                    default:
                        System.out.println("Choix invalide. Réessayez.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la modification du profil : " + e.getMessage());
        }
    }

    public void gererFlotte() {
    }

    public void gererSuiveurs() {
    }

    public void gererActivites() {
    }

    public void gererInterets() {
    }

    public void suivreUtilisateur() {
    }

    public void sInscrireActivite() {
    }


    public void voirNotifications() {
    }

    /*public void enregistrerRobot(Robot robot) {
        this.robots.add(robot);
    }

    public void afficherRobots() {
        for (Robot robot : this.robots) {
            System.out.println(robot);
        }
    }*/

    // Vérifier qu'un String contient uniquement des caractères alphanumériques
    private static void verifierAlphaNum(String string) {
        if (!string.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("L'entrée doit contenir uniquement des caractères alphanumériques.");
        }
    }

    // Vérifier qu'un String contient uniquement des caractères alphabétiques
    private static void verifierAlpha(String string) {
        if (!string.matches("[A-Za-z]+")) {
            throw new IllegalArgumentException("L'entrée doit contenir uniquement des caractères alphabétiques.");
        }
    }

    private void verifierEmail(String email) {
        String emailPattern = "[A-Za-z0-9-_\\.]+@[a-z]+\\.(com|fr|ca|io|web)";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Adresse courriel invalide.");
        }
    }

    private static void pseudoUnique(String pseudo) {
        for (Utilisateur utilisateur : Systeme.getInstance().getUtilisateurs()) {
            if (utilisateur.getPseudo().equals(pseudo)) {
                throw new IllegalArgumentException("Ce pseudo existe déjà.");
            }
        }
    }

    private static int chercherPseudo(String pseudo) {
        ArrayList<Utilisateur> utilisateurs = Systeme.getInstance().getUtilisateurs();
        for (int i = 0; i < utilisateurs.size(); i++) {
            if (utilisateurs.get(i).getPseudo().equals(pseudo)) {
                return i;   // le pseudo existe déjà
            }
        }
        return -1;          // le pseudo n'existe pas
    }


    /*public void initialiserUtilisateurs() {

        Utilisateur utilisateur1 = new Utilisateur("Dannane", "Chaima", "aithu", "aithu@example.com", "motdepasse1", "1357924680", listeInterets);
        listeUsers.add(utilisateur1);

        Utilisateur utilisateur2 = new Utilisateur("nom2", "Maha", "maha", "maha@example.com", "motdepasse2", "2468013579",listeInterets);
        listeUsers.add(utilisateur2);

        Utilisateur utilisateur3 = new Utilisateur("nom3","Sabrina", "miaou", "miaou@example.com", "motdepasse3", "9753124680", listeInterets);
        listeUsers.add(utilisateur3);

        Utilisateur utilisateur4 = new Utilisateur("nom4", "Audrey", "aude", "aude@example.com", "motdepasse4", "0864213579", listeInterets);
        listeUsers.add(utilisateur4);

        Utilisateur utilisateur5 = new Utilisateur("nom5", "prenom5", "nprenom5", "nprenom5@example.com", "motdepasse5", "3332221111", listeInterets);
        listeUsers.add(utilisateur5);

        Utilisateur utilisateur6 = new Utilisateur("nom6", "prenom6", "nprenom6", "nprenom6@example.com", "motdepasse6", "2221113333", listeInterets);
        listeUsers.add(utilisateur6);

        Utilisateur utilisateur7 = new Utilisateur("nom7", "prenom7", "nprenom7", "nprenom7@example.com", "motdepasse7", "6665554444", listeInterets);
        listeUsers.add(utilisateur7);

        Utilisateur utilisateur8 = new Utilisateur("nom8", "prenom8", "nprenom8", "nprenom8@example.com", "motdepasse8", "5554446666", listeInterets);
        listeUsers.add(utilisateur8);

        Utilisateur utilisateur9 = new Utilisateur("nom9", "prenom9", "nprenom9", "nprenom9@example.com", "motdepasse9", "8889997777", listeInterets);
        listeUsers.add(utilisateur9);

        Utilisateur utilisateur10 = new Utilisateur("nom10", "prenom10", "nprenom10", "nprenom10@example.com", "motdepasse10", "9997778888", listeInterets);
        listeUsers.add(utilisateur10);


    }*/
}

