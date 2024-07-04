import java.util.*;
import java.util.InputMismatchException;
public class Main {



    public static void main(String[] args) {
       // Gestionnaire donnees = new Gestionnaire();

        Main n = new Main();;
        n.choixProfil();
    }

    // Premier niveau d'imbrication du menu
    public void choixProfil() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" [1]: Profil publique\n [2]: Utilisateur \n [3] Fournisseur: \n ");

        try {
            int choix = scanner.nextInt();

            switch (choix) {

                case 1:
                    // Code for Profil publique
                    break;
                case 2:
                    choixConnecterInscrire();
                    break;
                case 3:
                    // Code for Fournisseur
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

    public void choixConnecterInscrire(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("[0]: Retour au menu principal \n [1]: Se connecter\n [2]: S'inscrire \n ");
        int choix1 = scanner.nextInt();
        boolean continuer = true;

        switch (choix1){
            case 0:
                choixProfil();
                break;

            case 1:
                while(continuer){
                    continuer= seConnecter();
                }
                break;

            case 2:
                while(continuer){
                    continuer=sInscrire();
                }
                choixConnecterInscrire(); // revenir au menu pour se connecter
                break;

            default:
                choixProfil();
                break;
        }

    }



    // return false si connexion réussie sinon true
    public boolean seConnecter(){
        return false;

        // continuer se connecter , ( update fichier avec l'utilisateur , ses données )
    }

    public boolean sInscrire(){
        System.out.println("Email de comfirmation envoyé!");
        return false;
    }

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
                "[6]: Récupérer la liste des fournisseurs \n [7]: Rechercher un fournisseur \n [8]: Voir le profil d'un" +
                "fournisseur \n [9]: Rechercher une composante \n ");
        try {
            int choix3 = scanner.nextInt();
            switch (choix3) {
                // mettre le lien avec la classe utilisateur
                case 0:
                    choixProfil();
                    break;
                case 1:

                    break;
                    // mettre les autres cases
                default:
                    afficherMenu(publique);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
            scanner.next();

        }


    }
    // menu des actions de l'Utilisateur
    public void afficherMenu(Utilisateur utilisateur) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir quelle fonctionnalité effectuer \n [0]: Retour au menu principal " +
                "[1] : Modifier son profil\n [2] Gérer sa flotte\n [3]: Gérer ses suiveurs \n [4]:Gérer ses activités" +
                " \n [5] : Gérer ses intérêts \n [6] : Suivre un utilisateur \n[7]: S'Inscrire à une activité \n [8] :" +
                "Se souscrire à un intérêt \n [9] : Voir létat de ses robots \n [10] : Voir les métriques \n [11]: Voir " +
                " ses + notifications \n");
        try {
            int choix2 = scanner.nextInt();
            switch (choix2) {
                // mettre le lien avec la classe utilisateur
                case 0:
                    choixProfil();
                    break;
                case 1:

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