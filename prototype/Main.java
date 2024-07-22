import java.util.*;
import java.util.InputMismatchException;

public class Main {
    private static Fournisseur fournisseur = new Fournisseur();
    private static Utilisateur utilisateur = new Utilisateur();
    private static Activite activite = new Activite();
    private static Interet interet = new Interet();

    private static Robot robot = new Robot();
    private static Boolean continuer = true;
    private static int choix = -1;
    private static int index = -1;

    public static void main(String[] args) {
        initialiser();
        choixProfil();
    }

    private static void initialiser() {
        interet.initialiserListeInterets();
        utilisateur.initialiserUtilisateurs();
        fournisseur.initialiserListeFournisseurs();
    }

    // Premier niveau d'imbrication du menu ***************************************************************************
    public static void choixProfil() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("  [1]: Utilisateur \n [2]: Fournisseur \n ");

            try {
                choix = scanner.nextInt();
                switch (choix) {
                    case 1:
                        index = choixConnecterInscrire(utilisateur);
                        if (index >= 0) {
                            afficherMenu(Systeme.getInstance().getUtilisateurs().get(index));
                        }
                        break;

                    case 2:
                        index = choixConnecterInscrire(fournisseur);
                        if (index >= 0) {
                            afficherMenu(Systeme.getInstance().getFournisseurs().get(index));
                        }
                        break;

                    default:
                        System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
                        choixProfil();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
                scanner.next(); // effacer
            }
        }
    }

    public static <T extends Acteur> int choixConnecterInscrire(T acteur) {
        Scanner scanner = new Scanner(System.in);
        continuer = true;

        while (continuer) {
            try {
                System.out.println(" [0]: Reto" +
                        "ur au menu principal \n [1]: Se connecter\n [2]: S'inscrire \n ");
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
                scanner.next(); // Clear the invalid input
            }
        }
        System.out.println("Accès au menu refusé.");
        return -1;
    }

    // Deuxième niveau d'imbrication du menu *************************************************************************
    public static void afficherMenu(Fournisseur fournisseur) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir une fonctionnalité à effectuer \n [0]: Retourner au menu principal\n" +
                " [1]: Modifier mon profil \n [2]: Afficher mes composantes \n [3]: Gérer mes composantes");

        try {
            int choix3 = scanner.nextInt();
            switch (choix3) {
                case 0:
                    choixProfil();
                    break;
                case 1:
                    fournisseur.modifierProfil();
                    break;
                case 2:
                    fournisseur.AfficherComposantes();
                    break;
                case 3:
                    fournisseur.gererComposantes();  // dans gérer composantes ajouter ,modif + supp composante
                    break;
                default:
                    System.out.println("Veuillez choisir un nombre valide :");
                    afficherMenu(fournisseur);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
            scanner.next(); // effacer
        }
    }


    public static void afficherMenu(Utilisateur utilisateur) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir quelle fonctionnalité effectuer \n [0]: Retour au menu principal " +
                "[1] : Modifier son profil\n [2] : Afficher l'état de robots \n [3]: Gérer ma flotte " +
                "\n [4] : Gérer mes activités \n [5] : Chercher un fournisseur\n [6]: Voir ses notifications \n"
        +"[7]: Rechercher une composante" );

        try {
            int choix2 = scanner.nextInt();
            switch (choix2) {
                case 0:
                    return;
                case 1:
                    utilisateur.modifierProfil();
                    break;
                case 2:
                    utilisateur.afficherLesEtats();
                    break;
                case 3:
                    utilisateur.gererFlotte();
                    break;
                case 4:
             //       utilisateur.gererActivites();  // s'inscrire +  desinscrire
                    break;
                case 5:
                    // chercher un fournisseur
                    break;
                case 6:
                //    utilisateur.voirNotifications();
                    break;
                case 7:
                    utilisateur.rechercherComposante();
                    break;
                default:
                    System.out.print("Veuillez choisir une action valide!");
                    afficherMenu(utilisateur);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
            scanner.next(); // effacer
        }
    }
}

