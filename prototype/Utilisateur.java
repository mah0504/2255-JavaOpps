import java.util.*;
import java.util.regex.*;
import java.time.LocalDateTime;

public class Utilisateur implements Acteur {
    private String nom, prenom, pseudo, motDePasse, email, telephone;
    Scanner scanner = new Scanner(System.in);
    Boolean continuer = true;
    private String nomDeCompagnie;
    private LocalDateTime dateInscription =  LocalDateTime.now(); //la date où on commence l'inscription
    private boolean confirmationEmail = false; //pour confirmer le mail
    private String codeConfirmation = genererCodeConfirmation(); //clé à retourner pour confirmer le mail
    private ArrayList<Utilisateur> listeUsers;
    private ArrayList<String> listePseudos;
    private ArrayList<Interet> listeInterets; // liste de 10 interets que suit l'utilisateur
    private ArrayList<Utilisateur> listeSuivis; // liste des utilisateurs que je suis
    private ArrayList<Utilisateur> listeSuiveurs; // liste des utilisateurs qui me suivent
    private ArrayList<Activite> listeActivitesbyUser; // liste des activités que maintiens l'utilisateur
    private int pointsGagnes;
    private int classement;
    List<Activite> MesActivites = new ArrayList<>();

    // todo : nom de la compagnie ( optionnel )

    public Utilisateur(String nom, String prenom, String pseudo, String email, String motDePasse, String telephone, int pointsGagnes) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.pointsGagnes = pointsGagnes;
        listePseudos.add(pseudo);
    }

    public Utilisateur() {

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
        return motDePasse;
    }

    public void setMdp(String mdp) {
        this.motDePasse = mdp;
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

    public int getPoints() {
        return pointsGagnes;
    }

    public void setPoints(int pointsGagnes) {
        this.pointsGagnes = pointsGagnes;
    }

    public ArrayList<Utilisateur> getListeUsers() {
        return listeUsers;
    }

    public void setListeUsers(ArrayList<Utilisateur> listeUsers) {
        this.listeUsers = listeUsers;
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

    public ArrayList<Activite> getListeActivitesbyUser() {
        return listeActivitesbyUser;
    }

    public void setListeActivitesbyUser(ArrayList<Activite> listeActivitesbyUser) {
        this.listeActivitesbyUser = listeActivitesbyUser;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
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

        //L'utilsateurs entre jusqu'à 10 Intérêts
        gererInterets();

        //initialisé à 0 car l'utilisateur n'a pas commencé les activités
        int pointsGagnes = 0;

        //envoyer mail de confirmation
        envoyerEmail(email);
        //modifie ou pas la variable booléene confirmationEmail
        confirmerInscription();

        Utilisateur utilisateur = new Utilisateur(nom, prenom, pseudo, email, motDePasse, telephone, pointsGagnes);
        if(confirmationEmail){
            listeUsers.add(utilisateur);
        }
        System.out.println("Inscription réussie!");

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
                motDePasse = scanner.nextLine();

                int index = chercherPseudo(pseudo);
                if (index >= 0) {
                    utilisateur = Systeme.getInstance().getUtilisateurs().get(index);
                    if (! motDePasse.equals(utilisateur.getMdp())) {
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

    /*
     * Cette méthode permet de confirmer l'inscription
     * elle modifie ou pas la valeur de la variable booléene confirmationEmail
     * Si l'utilisateur entre la bonne clé après 24 heures, la variable confirmationEmail renvoie un True
     */

    @Override
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

    public void gererFlotte(Flotte flotte, Composantes composantes ) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Veuillez choisir quelle fonctionnalité effectuer dans votre flotte: " +
                    "[1] : Acheter une composante \n [2] : Assigner une composante à un robot \n [3]" +
                    " : Enregistrer un robot \n");
            int choix = scanner.nextInt();

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
    public void voirMetriques( Robot robot){

        for ( Metrique m: robot.getMetriquesRobot() ){
            System.out.println( "La metrique : " +m );
        }
    }

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

                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
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

        int choix = scanner.nextInt();
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
        ArrayList<Interet> listeInteretsGeneraux = Interet.getListeInteretsGeneraux();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Choisissez vos intérêts (entrez le numéro, 0 pour aucun choix):");
            for (int i = 0; i < listeInteretsGeneraux.size(); i++) {
                System.out.println((i + 1) + ". " + listeInteretsGeneraux.get(i).getNom());
            }

            int nbChoix = 0;
            while (nbChoix < 10) {
                System.out.print("Choix " + (nbChoix + 1) + ": ");
                int choix = scanner.nextInt();
                scanner.nextLine();

                if (choix == 0) {
                    break; // L'utilisateur ne choist aucun intéret proposé
                } else if (choix >= 1 && choix <= listeInteretsGeneraux.size()) {
                    Interet interet = listeInteretsGeneraux.get(choix - 1);
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
                listeInteretsGeneraux.add(nvInteret);
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

        int choix = scanner.nextInt();
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

    private Utilisateur rechercherParPseudo(String pseudo) {
        for (Utilisateur utilisateur : listeUsers) {
            if (utilisateur.getPseudo().equals(pseudo)) {
                return utilisateur;
            }
        }
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

    private String genererCodeConfirmation() {
        return UUID.randomUUID().toString();
    }

    private void envoyerEmail(String destinataire) {
        System.out.println("Envoi d'email à : " + destinataire);
        System.out.println("Sujet : Confirmation de l'inscription" );
        System.out.println("Contenu : Cliquez sur le lien pour confirmer votre inscription  et saisisez le code :" + codeConfirmation);
    }
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

        int choix = scanner.nextInt();
        scanner.nextLine();

        // Il faut modifier comment gérer listedActivites
        Activite MonActivite = liste.get(choix - 1);
        MesActivites.add(MonActivite);

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

    /*public void initialiserUtilisateurs() {

        Utilisateur utilisateur1 = new Utilisateur("Dannane", "Chaima", "aithu", "aithu@example.com", "motdepasse1", "1357924680", 0);
        listeUsers.add(utilisateur1);

        Utilisateur utilisateur2 = new Utilisateur("nom2", "Maha", "maha", "maha@example.com", "motdepasse2", "2468013579",0);
        listeUsers.add(utilisateur2);

        Utilisateur utilisateur3 = new Utilisateur("nom3","Sabrina", "miaou", "miaou@example.com", "motdepasse3", "9753124680", 0);
        listeUsers.add(utilisateur3);

        Utilisateur utilisateur4 = new Utilisateur("nom4", "Audrey", "aude", "aude@example.com", "motdepasse4", "0864213579", 0);
        listeUsers.add(utilisateur4);

        Utilisateur utilisateur5 = new Utilisateur("nom5", "prenom5", "nprenom5", "nprenom5@example.com", "motdepasse5", "3332221111", 0);
        listeUsers.add(utilisateur5);

        Utilisateur utilisateur6 = new Utilisateur("nom6", "prenom6", "nprenom6", "nprenom6@example.com", "motdepasse6", "2221113333", 0);
        listeUsers.add(utilisateur6);

        Utilisateur utilisateur7 = new Utilisateur("nom7", "prenom7", "nprenom7", "nprenom7@example.com", "motdepasse7", "6665554444", 0);
        listeUsers.add(utilisateur7);

        Utilisateur utilisateur8 = new Utilisateur("nom8", "prenom8", "nprenom8", "nprenom8@example.com", "motdepasse8", "5554446666", 0);
        listeUsers.add(utilisateur8);

        Utilisateur utilisateur9 = new Utilisateur("nom9", "prenom9", "nprenom9", "nprenom9@example.com", "motdepasse9", "8889997777", 0);
        listeUsers.add(utilisateur9);

        Utilisateur utilisateur10 = new Utilisateur("nom10", "prenom10", "nprenom10", "nprenom10@example.com", "motdepasse10", "9997778888", 0);
        listeUsers.add(utilisateur10);

        //l'utilisateur2 suit l'utilisateur 1
        utilisateur2.suivreUtilisateur(utilisateur1);

    }*/
}

