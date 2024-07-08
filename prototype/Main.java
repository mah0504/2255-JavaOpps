import java.util.*;
import java.util.InputMismatchException;
public class Main {
    //private Publique publique;
    private Fournisseur fournisseur = new Fournisseur();
    private Utilisateur utilisateur = new Utilisateur();
    private Publique publique = new Publique();
    private Boolean continuer = true;
    private int choix = -1;
    private int index = -1;

    public static void main(String[] args) {

        Main n = new Main();
        Interet interet = new Interet();
        interet.initialiserListeInterets();

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.initialiserUtilisateurs();
        n.choixProfil();

    }

    // Premier niveau d'imbrication du menu ***************************************************************************
    public void choixProfil() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(" [1]: Profil publique\n [2]: Utilisateur \n [3]: Fournisseur \n ");

            try {
                choix = scanner.nextInt();

                switch (choix) {

                    case 1:
                        afficherMenu(publique);
                        break;

                    case 2:

                        index = choixConnecterInscrire(utilisateur);
                        System.out.println(index);
                        if (index >= 0) {
                            afficherMenu(Systeme.getInstance().getUtilisateurs().get(index));
                        }

                        break;

                    case 3:
                        index = choixConnecterInscrire(fournisseur);
                        if (index >= 0) {
                            afficherMenu(Systeme.getInstance().getFournisseurs().get(index));
                        }
                        break;

                    default:
                        System.out.println("Choix invalide. Veuillez entrer 1, 2 ou 3.");
                        choixProfil();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur: Veuillez entrer un nombre entier valide.");

                // affichage
                scanner.next(); // Clear the invalid input
            }
        }
    }

    public <T extends Acteur> int choixConnecterInscrire(T acteur) {
        Scanner scanner = new Scanner(System.in);

        continuer = true;

        while (continuer) {
            try {
                System.out.println(" [0]: Retour au menu principal \n [1]: Se connecter\n [2]: S'inscrire \n ");
                choix = scanner.nextInt();

                switch (choix) {
                    case 0:
                        choixProfil();
                        continuer = false;
                        break;

                    case 1:
                        index = acteur.seConnecter();
                        return index;

                    case 2:
                        acteur.sInscrire();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
            }
        }
        System.out.println("Accès au menu refusé.");
        return -1;
    }

    // Deuxième niveau d'imbrication du menu *************************************************************************
    public void afficherMenu(Fournisseur fournisseur){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisier une fonctionnalité à effectuer \n [0]: Retourner au menu principal\n" +
                " [1]: Modifier son profil \n [2]: Gérer ses composantes \n [3]: Enregistrer une composante");
        try{
            int choix3 = scanner.nextInt();
            switch (choix3){
                case 0:
                    choixProfil();
                    break;
                case 1:
                    // rajouter les methodes de fournisseur -> chaima
                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:

                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
            scanner.next();
        }

    }
    public void afficherMenu(Publique publique){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir quelle fonctionnalité effectuer \n [0]: Retour au menu principal " +" " +
                "[1]: Récupérer la liste des utilisateurs \n [2]: Rechercher un utilisateur \n [3]: Voir le profil" +
                "d'un utilisateur \n [4]: Récupérer la liste des activités \n [5]: Récupérer la liste des intérêts \n" +
                " [6]: Récupérer la liste des fournisseurs \n [7]: Rechercher un fournisseur \n [8]: Voir le profil d'un" +
                "fournisseur \n [9]: Rechercher une composante \n ");
        try {
            int choix3 = scanner.nextInt();
            switch (choix3) {
                // mettre le lien avec la classe utilisateur
                case 0:
                    choixProfil();
                    break;
                case 1:
                    publique.RecupListeUtilisateur(utilisateur);
                    break;
                case 2:

                    break;
                case 3:
                    //       publique.voirProfilU();
                    break;
                case 4:
                    //     publique.RecupListeActivites();
                    break;
                case 5:
                    //   publique.RecupListeInterets();
                    break;
                case 6:
                    // publique.RecupListeFournisseur();
                    break;
                case 7:
                    //  publique.rechercherComposante();
                    break;

                case 8:
                    //  publique.voirProfil(fournisseur);
                    break;
                default:
                    afficherMenu(publique);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
            scanner.next();

        }


    }

    public void afficherMenu(Utilisateur utilisateur) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir quelle fonctionnalité effectuer \n [0]: Retour au menu principal " +
                "[1] : Modifier son profil\n [2] Gérer sa flotte\n [3]: Gérer ses suiveurs \n [4]:Gérer ses activités" +
                " \n [5] : Gérer ses intérêts \n [6] : Suivre un utilisateur \n [7]: S'Inscrire à une activité \n [8] :" +
                "Se souscrire à un intérêt \n [9] : Voir létat de ses robots \n [10] : Voir les métriques \n [11]: Voir " +
                " ses + notifications \n");
        // pk pas de gerer tache dans les fonctionnalités de l"utilisateur?
        try {
            int choix2 = scanner.nextInt();
            switch (choix2) {
                // mettre le lien avec la classe utilisateur
                case 0:
                    choixProfil();
                    break;
                case 1:
                    utilisateur.modifierProfil();
                    break;

                case 2:
                    //utilisateur.gererFlotte(utilisateur.getFlotte(), ); //2 e argument composante
                    break;
                case 3:

                    break;
                case 4:
                    utilisateur.gererActivites();
                    break;
                case 5:
                    utilisateur.gererInterets();
                    break;
                case 6:
                    //   utilisateur.suivreUtilisateur();
                    break;
                case 7:
                    utilisateur.sInscrireActivite();
                    break;
                case 8:
                    // souscrire interet
                    break;
                case 9:
                    //utilisateur.afficherLesEtats(robot); // voir quoi mettre a l'inter
                    break;
                default:

                    afficherMenu(utilisateur);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
            scanner.next();

        }
    }

}
