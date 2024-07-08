import java.util.*;
import java.util.InputMismatchException;

public class Main {
    private static Fournisseur fournisseur = new Fournisseur();
    private static Utilisateur utilisateur = new Utilisateur();
    private static Activite activite = new Activite();
    private static Interet interet = new Interet();
    private static Publique publique = new Publique();
    private static Composantes composante = new Composantes();
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
            System.out.println(" [1]: Profil publique\n [2]: Utilisateur \n [3]: Fournisseur \n ");

            try {
                choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                        afficherMenu(publique);
                        break;

                    case 2:
                        index = choixConnecterInscrire(utilisateur);
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
                scanner.next(); // effacer
            }
        }
    }

    public static <T extends Acteur> int choixConnecterInscrire(T acteur) {
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
                " [1]: Modifier son profil \n [2]: Gérer ses composantes \n [3]: Enregistrer une composante");

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
                    fournisseur.gererComposantes();
                    break;
                case 3:
                    fournisseur.enregistrerComposante();
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

    public static void afficherMenu(Publique publique) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir quelle fonctionnalité effectuer \n [0]: Retour au menu principal " +
                "[1]: Récupérer la liste des utilisateurs \n [2]: Rechercher un utilisateur \n [3]: Voir le profil" +
                "d'un utilisateur \n [4]: Récupérer la liste des activités \n [5]: Récupérer la liste des intérêts \n" +
                " [6]: Récupérer la liste des fournisseurs \n [7]: Rechercher un fournisseur \n [8]: Voir le profil d'un" +
                "fournisseur \n [9]: Rechercher une composante \n ");

        try {
            int choix3 = scanner.nextInt();
            switch (choix3) {
                case 0:
                    choixProfil();
                    break;
                case 1:
                    publique.RecupListeUtilisateur();
                    break;
                case 2:
                    publique.RecupListeActivites();
                    break;
                case 3:
                    publique.voirProfilU(utilisateur);
                    break;
                case 4:
                    publique.RecupListeActivites();
                    break;
                case 5:
                    publique.RecupListeInterets();
                    break;
                case 6:
                    publique.RecupListeFournisseur();
                    break;
                case 7:
                    publique.rechercherComposante();
                    break;
                case 8:
                    publique.voirProfilF(fournisseur);
                    break;
                default:
                    System.out.println("Veuillez choisir un nombre valide :");
                    afficherMenu(publique);
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
                "[1] : Modifier son profil\n [2] Gérer sa flotte\n [3]: Gérer ses suiveurs \n [4]:Gérer ses activités" +
                " \n [5] : Gérer ses intérêts \n [6] : Suivre un utilisateur \n [7]: S'Inscrire à une activité \n [8] :" +
                "Se souscrire à un intérêt \n [9] : Voir l'état de ses robots \n [10] : Voir les métriques \n [11]: Voir " +
                " ses notifications \n");

        try {
            int choix2 = scanner.nextInt();
            switch (choix2) {
                case 0:
                    choixProfil();
                    break;
                case 1:
                    utilisateur.modifierProfil();
                    break;
                case 2:
                    utilisateur.gererFlotte(utilisateur.getFlotte(), composante); // 2e argument composante
                    break;
                case 3:
                    utilisateur.gererSuiveurs();
                    break;
                case 4:
                    utilisateur.gererActivites();
                    break;
                case 5:
                    utilisateur.gererInterets();
                    break;
                case 6:
                    utilisateur.suivreUtilisateur(utilisateur);
                    break;
                case 7:
                    utilisateur.sInscrireActivite();
                    break;
                case 8:
                    interet.souscrire(utilisateur);
                    break;
                case 9:
                    utilisateur.afficherLesEtats(utilisateur.getFlotte().choisirRobot());
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

