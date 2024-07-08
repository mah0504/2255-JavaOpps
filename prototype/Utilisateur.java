import javax.swing.plaf.basic.BasicSliderUI;
import java.util.*;
import java.util.regex.*;

public class Utilisateur extends Acteur {

    private String prenom, pseudo;
    private Flotte flotte= new Flotte();

    private ArrayList<Interet> listeInterets = new ArrayList<>(); // liste de 10 interets que suit l'utilisateur
    private ArrayList<Utilisateur> listeSuivis=new ArrayList<>(); // liste des utilisateurs que je suis
    private ArrayList<Utilisateur> listeSuiveurs=new ArrayList<>(); // liste des utilisateurs qui me suivent
    private ArrayList<Activite> listeActivites=new ArrayList<>(); // liste des activités que maintiens l'utilisateur
    private int pointsGagnes, classement;
    List<Activite> MesActivites = new ArrayList<>();
    private List<Notifications> MesNotifications= new ArrayList<>();
    // Constructeurs :


    public Utilisateur(String nom, String prenom, String pseudo, String email, String motDePasse, String telephone, int pointsGagnes){
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.pointsGagnes = pointsGagnes;

    }

    public Utilisateur(){
        pointsGagnes = 0;

    }


    // Méthodes pour entrer les informations
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

    private void entrerPrenom() {    //user

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


    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public Flotte getFlotte(){
        return flotte;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public List<Notifications> getNotifications() { return MesNotifications;}

    public void addNotif(Notifications notification) { MesNotifications.add(notification);}




    public int getPoints() {
        return pointsGagnes;
    }

    public void setPoints(int pointsGagnes) {
        this.pointsGagnes = pointsGagnes;
    }

    public ArrayList<Interet> getListeInterets() {
        return listeInterets;
    }

    public void setListeInterets(ArrayList<Interet> listeInterets) {
        this.listeInterets = listeInterets;
    }

    public ArrayList<Utilisateur> getListeSuivis() {
        return listeSuivis;
    }

    public void setListeSuivis(ArrayList<Utilisateur> listeSuivis) {
        this.listeSuivis = listeSuivis;
    }

    public ArrayList<Utilisateur> getListeSuiveurs() {
        return listeSuiveurs;
    }

    public void setListeSuiveurs(ArrayList<Utilisateur> listeSuiveurs) {
        this.listeSuiveurs = listeSuiveurs;
    }

    public ArrayList<Activite> getListeActivites() {
        return listeActivites;
    }

    public void setListeActivites(ArrayList<Activite> listeActivites) {
        this.listeActivites = listeActivites;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", points accumulés=" + pointsGagnes +
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
        entrerCompagnie();

//        todo la suite...
//         gererInterets();
//         envoyerEmail(email);
//         confirmerInscription();

        Systeme.getInstance().ajouterUtilisateur(this);

        System.out.println("Inscription réussie!");

    }

    @Override
    public int seConnecter() {

        continuer = true;
        Utilisateur utilisateur;
        int index = -1;

        while (continuer) {
            try {

                System.out.print("Entrez votre pseudo : ");
                pseudo = scanner.nextLine();
                System.out.print("Entrez votre mot de passe : ");
                motDePasse = scanner.nextLine();

                index = chercherPseudo(pseudo);
                if (index < 0) {
                    throw new IllegalArgumentException("Pseudo inexistant.");
                }
                utilisateur = Systeme.getInstance().getUtilisateurs().get(index);
                if (! motDePasse.equals(utilisateur.getMdp())) {
                    throw new IllegalArgumentException("Mot de passe invalide.");
                }
                System.out.println("Connexion réussie.");
                return index;

                // todo si connexion réussie, dans le main remplacer l'user par celui Systeme.getInstance().getUtilisateurs().get(index)

            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
                index = -1;
                continuer = stopContinuer();
            }
        }
        return index;
    }


    @Override
    public void modifierProfil() {

        continuer = true;
        Fournisseur fournisseur;

        while (continuer) {
            try {

                System.out.println("Que voulez-vous modifier ?");
                System.out.println("1. Nom");
                System.out.println("2. Prénom");
                System.out.println("3. Email");
                System.out.println("4. Mot de passe");
                System.out.println("5. Numéro de téléphone");
                System.out.println("6. Nom de la compagnie");
                System.out.println("0. Quitter");

                choix = scanner.nextInt();
                switch (choix) {
                    case 0:
                        continuer = false;
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
                    case 6:
                        entrerCompagnie();
                        break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
                scanner.next();
            }
        }
    }



    public void gererFlotte(Flotte flotte, Composantes composantes ) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Veuillez choisir quelle fonctionnalité effectuer dans votre flotte: " +
                    "[1] : Acheter une composante \n [2] : Assigner une composante à un robot \n [3]" +
                    " : Enregistrer un robot \n");
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    flotte.acheterComposante(composantes.choisirCompo());
                    break;
                case 2:
                    flotte.assignerComposante(flotte.choisirRobot(), composantes.choisirCompo());
                    break;
                case 3:
                    flotte.enregistrerRobot(new Robot(), composantes.choisirCompo());
                    break;
                default:
                    System.out.println("Veuillez entrer un choix valide !");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrée non valide. Veuillez entrer un nombre.");
            scanner.next(); // Clear the invalid input
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite: " + e.getMessage());
            e.printStackTrace();
            return; // revenir au menu jsp ?
        }
    }

    //choisir robot avant à utiliser
    public void afficherLesEtats(Robot robot) {
        Object[] etats = robot.getEtats();
        System.out.println("États du robot " + robot.getNom() + " :");
        for (Object etat : etats) {
            System.out.println(etat);
        }
    }
    /*public void voirMetriques( Robot robot){

        for ( Metrique m: robot.getMetriquesRobot() ){
            System.out.println( "La metrique : " +m );
        }
    }*/

    /*
     * Méthode qui permet de gérer les Suiveurs et Suivis
     * Elle permet soit de Suivre un Utilisateur, soit de voir qui nous suivons et qui nous suivent.
     * Pour Suivre un utilisateur, il faut connâitre son pseudo
     * Manquant : Pensez à afficher la liste des Utilisateurs avant de choisir le pseudo
     */
    public void gererSuiveurs() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuer = true;

            while (continuer) {
                System.out.println("Que voulez-vous faire ?");
                System.out.println("1. Suivre un utilisateur");
                System.out.println("2. Voir les utilisateurs que vous suivez");
                System.out.println("3. Voir les utilisateurs qui vous suivent");
                System.out.println("0. Quitter");

                choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        //todo recherche par pseudo
                        System.out.print("Qui voulez vous suivre ?");
                        Utilisateur utilisateurSuivi = rechercherParPseudo(scanner.nextLine());
                        if (utilisateurSuivi != null) {
                            suivreUtilisateur(utilisateurSuivi);
                        } else {
                            System.out.println("Utilisateur non trouvé.");
                        }
                        break;
                    case 2:
                        voirSuivis();
                        break;
                    case 3:
                        voirSuiveurs();
                        break;
                    case 0:
                        continuer = false;
                        break;
                    default:
                        System.out.println("Choix invalide. Réessayez !");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la gestion des suivis : " + e.getMessage());
        }
    }



    public void addActivites(Activite activite) {
        MesActivites.add(activite);
    }
    /*
        afficherMesActivites() : afficher les actvités auxquelles je suis inscrits
        sInscrireActivite() : S'inscrire à des activités
        Manquant : Calculer les Points + Montrer le classement ==> gererClassement()
     */
    public void gererActivites() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("1. Voir vos activités");
        System.out.println("2. S'inscrire à une activité");

        choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                afficherMesActivites();
                break;
            case 2:
                sInscrireActivite();
                break;
            default:
                System.out.println("Choix invalide.");
        }
    }

    /*
     * Cette méthode permet d'initialiser pour chaque Utilisateur une liste à 10 Intérets
     * L'utilisateur peut choisir d'initialiser sa liste avec des Intérets qui existent déjà (listeInteretsGeneraux)
     * ou d'ajouter de nouveaux Intérêts ( en même temps il faut initialiser la listeInteretsGeneraux pour inclure
     * les nouveaux Intérêts )
     */

    public void gererInterets() {

        ArrayList<Interet> interetsSysteme = Systeme.getInstance().getInterets();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Choisissez vos intérêts (entrez le numéro, 0 pour aucun choix):");
            for (int i = 0; i < interetsSysteme.size(); i++) {
                System.out.println((i + 1) + ". " + interetsSysteme.get(i).getNom());
            }

            int nbChoix = 0;
            while (nbChoix < 10) {
                System.out.print("Choix " + (nbChoix + 1) + ": ");
                choix = scanner.nextInt();
                scanner.nextLine();

                if (choix == 0) {
                    break; // L'utilisateur ne choist aucun intéret proposé
                } else if (choix >= 1 && choix <= interetsSysteme.size()) {
                    Interet interet = interetsSysteme.get(choix - 1);
                    interet.souscrire(this);
                    nbChoix++;
                } else {
                    System.out.println("Choix invalide. Réessayez.");
                }
            }

            // Si l'utilisateur n'a pas sélectionné 10 choix, lui demander d'ajouter de nouveaux intérêts
            while (nbChoix < 10) {
                System.out.print("Entrez un nouvel intérêt : ");
                Interet nvInteret = new Interet(scanner.nextLine());
                interetsSysteme.add(nvInteret);
                nvInteret.souscrire(this);
                nbChoix++;
            }

        } catch (Exception e) {
            System.out.println("Erreur lors de la gestion des intérêts : " + e.getMessage());
        }
    }
    /*
     * Cette méthode permet de suivre un Utilisateur
     * Elle modifie la liste des Suivis ( pour l'utilisateur qui souhaitent faire le Suivi)
     * et la liste des Suiveurs (pour l'utilisateur que nous souhaitons suivre )
     */
    public void suivreUtilisateur(Utilisateur utilisateur) {
        if (!this.listeSuivis.contains(utilisateur)) {
            this.listeSuivis.add(utilisateur);
            utilisateur.listeSuiveurs.add(this);
            System.out.println("Vous suivez " + utilisateur.getPseudo());
        } else {
            System.out.println("Vous suivez déjà "+ utilisateur.getPseudo());
        }
    }


    /*
        //get l'activite et l'ajouter à la liste de mes actvités
        //get liste des Interets + choisir un Interet + choisir une activité dans la liste des Activites
        //get les Intérets et choisir une actvité et l'ajouter à la liste de mes actvités (Map<Interets, List<Activite>)
     */

    public void sInscrireActivite() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Comment voulez-vous vous inscrire à une activité ?");
        System.out.println("1. À partir de la liste de toutes les activités disponibles");
        System.out.println("2.  À partir de la liste des intérêts");

        choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                choisirByActivite();
                break;
            case 2:
                choisirByInteret();
                break;
            default:
                System.out.println("Choix invalide.");
        }
    }

    /*
     * Cette méthode doit implémenter l'affichage des notifications à l'intention d'un Utilisateur
     * Ces notifications peuvent être connectés aux Acticités, à la flotte ou autre
     */
    public void voirNotifications() {
    }

    //Calculer ou Trouver le classement d'un Utilisateur à partir des pointsGagnés
    //penser à ajouter le classement comme attribut pour qu'il s'affiche dans le profil
    public void gererClassement(){
    }


    /*public void enregistrerRobot(Robot robot) {
        this.robots.add(robot);
    }

    public void afficherRobots() {
        for (Robot robot : this.robots) {
            System.out.println(robot);
        }
    }*/

    /*
     * Fonctions auxiliaires qui gèrent les Suivis
     * voirSuivis() : affiche la liste des personnes que l'utilisateur suit
     * voirSuiveurs() : affihe la liste des personnes qui suivent l'utilisateur
     * rechercherParPseudo() : retourne l'utilisateur qui possède le pseudo recherché
     */
    public void voirSuivis() {
        System.out.println("Liste des utilisateurs que vous suivez :");
        for (Utilisateur utilisateur : listeSuivis) {
            System.out.println(utilisateur.toString());
        }
    }

    public void voirSuiveurs() {
        System.out.println("Liste des utilisateurs qui vous suivent :");
        for (Utilisateur utilisateur : listeSuiveurs) {
            System.out.println(utilisateur.toString());
        }
    }

    //todo elle fonctionne pas cette méthode et elle est utilisée ailleurs
    private Utilisateur rechercherParPseudo(String pseudo) {
        /*for (Utilisateur utilisateur : listeUsers) {
            if (utilisateur.getPseudo().equals(pseudo)) {
                return utilisateur;
            }
        }*/
        return null;
    }
    /* ************************************************************************************************ */

    /*
     * Fonction auxiliaire pour vérifier l'inscription
     * emailValide() : vérifie que le pattern de l'email est le bon
     * genererCodeConfirmation() : génère une clé random
     * envoyerEmail() : envoie à l'utilisateur un mail qui lui permet d'obtenir la clé de confirmation
     *
     */
    private static boolean emailValide(String email) {
        String emailPattern = "[A-Za-z0-9-_\\.]+@[a-z]+\\.(com|fr|ca|io|web)";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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



    /* ************************************************************************************************ */
    /*
     * Fonctions auxiliaires pour sInscrireActivité et gererActivites
     */
    private void choisirByActivite() {

        Scanner scanner = new Scanner(System.in);

        List<Activite> liste = Activite.getListedActivites();
        for (int i = 0; i < liste.size(); i++) {
            System.out.println((i + 1) + ". " + liste.get(i));
        }

        choix = scanner.nextInt();
        scanner.nextLine();

        // Il faut modifier comment gérer listedActivites
        Activite MonActivite = liste.get(choix - 1);
        MesActivites.add(MonActivite);

    }

    public void addMesInterets(Interet interet){
        listeInterets.add(interet);
    }
    private void choisirByInteret() {
        Scanner scanner = new Scanner(System.in);

        List<Activite> liste = Activite.getListedActivites();
        Interet.MapActivitesByInterets(liste);

        int index = 1;
        for (Interet interet : Interet.activitesByInteret.keySet()) {
            System.out.println(index + ". " + interet.getNom());
            index++;
        }

        int choix1 = scanner.nextInt();
        scanner.nextLine();

        Interet MonInteret = (Interet) Interet.activitesByInteret.keySet().toArray()[choix1 - 1];
        List<Activite> liste2 = Interet.activitesByInteret.get(MonInteret);

        for (int i = 0; i < liste2.size(); i++) {
            //System.out.println((i + 1) + ". " + liste2.get(i).getNom());
        }

        int choix2 = scanner.nextInt();
        scanner.nextLine();

        // Il faut modifier comment gérer listedActivites
        Activite MonActivite = liste2.get(choix2 - 1);
        MesActivites.add(MonActivite);
    }

    public void afficherMesActivites() {
        if (MesActivites.isEmpty()) {
            System.out.println("Vous n'avez pas encore d'activités.");
        } else {
            System.out.println("Les activités que vous maintenez :");
            for (Activite activite : MesActivites) {
                System.out.println(activite);
            }
        }
    }
    /* ************************************************************************************************ */

    @Override
    public void entrerCompagnie() {
        System.out.println("Entrez le nom de la compagnie (faire Enter si aucune).");
        compagnie = scanner.nextLine();
    }

    public void initialiserUtilisateurs() {

        Systeme.getInstance().ajouterUtilisateur(new Utilisateur("nom1", "Chaima", "aithu", "aithu@example.com", "motdepasse1", "1357924680", 0));
        Systeme.getInstance().ajouterUtilisateur(new Utilisateur("nom2", "prenom2", "nprenom2", "nprenom2@example.com", "motdepasse2", "9996778888", 0));
        Systeme.getInstance().ajouterUtilisateur(new Utilisateur("nom3","Sabrina", "miaou", "miaou@example.com", "motdepasse3", "9753124680", 0));
        Systeme.getInstance().ajouterUtilisateur(new Utilisateur("nom4", "Audrey", "aude", "aude@example.com", "motdepasse4", "0864213579", 0));
        Systeme.getInstance().ajouterUtilisateur(new Utilisateur("nom5", "prenom5", "nprenom5", "nprenom5@example.com", "motdepasse5", "3332221111", 0));
        Systeme.getInstance().ajouterUtilisateur(new Utilisateur("nom6", "prenom6", "nprenom6", "nprenom6@example.com", "motdepasse6", "2221113333", 0));
        Systeme.getInstance().ajouterUtilisateur(new Utilisateur("nom7", "prenom7", "nprenom7", "nprenom7@example.com", "motdepasse7", "6665554444", 0));
        Systeme.getInstance().ajouterUtilisateur(new Utilisateur("nom8", "prenom8", "nprenom8", "nprenom8@example.com", "motdepasse8", "5554446666", 0));
        Systeme.getInstance().ajouterUtilisateur(new Utilisateur("nom9", "prenom9", "nprenom9", "nprenom9@example.com", "motdepasse9", "8889997777", 0));
        Systeme.getInstance().ajouterUtilisateur(new Utilisateur("nom10", "prenom10", "nprenom10", "nprenom10@example.com", "motdepasse10", "9997778888", 0));



        Utilisateur utilisateur1 = Systeme.getInstance().getUtilisateurs().get(0); // Récupérer le premier utilisateur par exemple

        Activite VolleyBall = new Activite(); // Remplacez ... par les détails nécessaires de l'activité
        Activite peinture = new Activite(); // Remplacez ... par les détails nécessaires de l'activité
        Activite natationSynchro = new Activite();
        Activite atelierCuisine = new Activite();
        Activite Photographie = new Activite();


        // Ajouter les activités à l'utilisateur
        utilisateur1.addActivites(VolleyBall);
        utilisateur1.addActivites(peinture);
        utilisateur1.addActivites(natationSynchro);
        utilisateur1.addActivites(atelierCuisine);
        utilisateur1.addActivites(Photographie);

        VolleyBall.setProgression("Non débutée");
        peinture.setProgression("En cours");
        Photographie.setProgression("Terminée");


        Interet sport = new Interet("Sport");
        Interet musique = new Interet("Musique");
        Interet lecture = new Interet("Lecture");
        Interet cuisine = new Interet("Cuisine");
        Interet voyage = new Interet("Voyage");
        Interet photographie = new Interet("Photographie");
        Interet cinema = new Interet("Cinéma");
        Interet jeuxVideo = new Interet("Jeux vidéo");
        Interet jardinage = new Interet("Jardinage");
        Interet bricolage = new Interet("Bricolage");

// Ajout des intérêts à la liste d'intérêts de l'utilisateur
        utilisateur1.addMesInterets(sport);
        utilisateur1.addMesInterets(musique);
        utilisateur1.addMesInterets(lecture);
        utilisateur1.addMesInterets(cuisine);
        utilisateur1.addMesInterets(voyage);
        utilisateur1.addMesInterets(photographie);
        utilisateur1.addMesInterets(cinema);
        utilisateur1.addMesInterets(jeuxVideo);
        utilisateur1.addMesInterets(jardinage);
        utilisateur1.addMesInterets(bricolage);

         Robot robot1= new Robot();
        utilisateur1.getFlotte().getListeRobots().add(robot1);
        robot1.setNom("IJWFVN");
        robot1.setType("Mouvement");
        robot1.setNumeroDeSerie(1234);
        // Ajouter des états pour test
        Object[] etats = {"État1", "État2", "État3", "État4"};
        robot1.setEtats(etats);

    }
}

