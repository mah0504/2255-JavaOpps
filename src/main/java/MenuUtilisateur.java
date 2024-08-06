import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuUtilisateur{

    private Scanner scanner;
    private Utilisateur utilisateur;
    private ControllerUtilisateur controllerUtilisateur;

    public MenuUtilisateur(Utilisateur utilisateur,ControllerUtilisateur controllerUtilisateur){
        this.scanner = new Scanner(System.in);
        this.utilisateur = utilisateur;
        this.controllerUtilisateur = controllerUtilisateur;


    }


    /*public void afficherMenuUtilisateur(Utilisateur utilisateur) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        while (continuer) {
            System.out.println("Menu :");
            System.out.println(" [0] : Retour au menu principal \n " +
                    "[1] : modifier mon profil  \n " +
                    "[2] : Afficher les états  mes robots \n " +
                    "[3] : Gérer ma flotte  \n " +
                    "[4] : Gérer les activités  \n " +
                    "[5] : Trouver un fournisseur \n " +
                    "[6] : Voir mes notifications \n" +
                    "[7] : Choisir fournisseur ");
            int choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 0:
                    continuer = false;
                    break;
                case 1:
                    controllerUtilisateur.modifierProfil();
                    break;
                case 2:
                    controllerUtilisateur.afficherEtatsRobots();
                    break;
                case 3:
                    gererFlotte();
                    break;
                case 4:
                    //  controllerUtilisateur.
                    // truc pr les activites
                    break;
                case 5:
                    trouverFournisseur();
                    break;
                case 6:
                    controllerUtilisateur.voirNotifs();
                    break;
                case 7:
                    controllerUtilisateur.choisirFournisseur();
                    //juste pr tester
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez entrer nombre valide dan la borne.");
            }
        }
    }*/

    public void afficherMenuUtilisateur(){
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        while (continuer) {
            System.out.println("Que souhaitez-vous faire ?");
            System.out.println("0 : Se déconnecter (Retour au Menu Principal)");
            System.out.println("1 : Modifier le profil");
            System.out.println("2 : Gérer mes activités");
            System.out.println("3 : Afficher l'état de mes robots");
            System.out.println("4 : Gérer ma flotte");
            System.out.println("5 : Trouver un fournisseur");
            System.out.println("6 : Choisir un fournisseur");
            System.out.println("7 : Voir mes notifications");
            int choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 0:
                    continuer = false;
                    break;
                case 1:
                    controllerUtilisateur.modifierProfilUtilisateur(utilisateur);
                    break;
                case 2:
                    controllerUtilisateur.gererActivites(utilisateur);
                    break;
                case 3:
                    controllerUtilisateur.afficherEtatsRobots(utilisateur);
                    break;
                case 4:
                    gererFlotte(utilisateur);
                    break;
                case 5:
                    trouverFournisseur();
                    break;
                case 6:
                    controllerUtilisateur.choisirFournisseur();
                    break;
                case 7:
                    controllerUtilisateur.voirNotifs(utilisateur);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez entrer nombre valide dan la borne.");
            }
        }
    }



    /**
     * Permet à l'utilisateur de gérer sa flotte de robots.
     *
     * Cette méthode affiche un menu permettant à l'utilisateur de choisir une action à effectuer
     * sur sa flotte de robots : enregistrer un robot, supprimer un robot, trouver une composante ou
     * acheter une composante.
     *
     */

    // verifier si affchage recherche ou liste ou autre

    public void gererFlotte(Utilisateur utilisateur) {
        Scanner scanner = new Scanner(System.in);
        boolean continueMenu = true;

        while (continueMenu) {
            try {
                System.out.println("Bienvenue dans votre flotte !" +
                        "\nVeuillez choisir quelle action effectuer:" +
                        "\n[1] : enregistrer un robot" +
                        "\n[2] : Supprimer un robot" +
                        "\n[3] : Trouver une composante" +
                        "\n[4] : Acheter une composante" +
                        "\n[0] : Quitter");

                int choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                    //    controllerUtilisateur.choisirComposanteFlotte(ComposanteType.ROUE);
                        controllerUtilisateur.enregistrerRobot(utilisateur);
                      break;
                    case 2:
                        controllerUtilisateur.supprimerRobot(utilisateur);
                        break;
                    case 3:
                        trouverComposante();
                        break;
                    case 4:
                        controllerUtilisateur.acheterComposante(utilisateur);
                        break;
                    case 0:
                        continueMenu = false;
                        System.out.println("Merci d'avoir utilisé notre service. À bientôt !");
                        break;
                    default:
                        System.out.println("Veuillez choisir un nombre approprié.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next(); // Consommer l'entrée invalide pour éviter une boucle infinie
            }
        }
    }

    public void trouverComposante(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Veuillez choisir comment chercher votre composante: \n [1] : Par nom \n [2] " +
                    ": Par type \n [3] : Par fournisseur");
            int choix = scanner.nextInt();
            switch (choix){
                case 1:
                    // affichage general trv comment ajouter le non general aussi


                            controllerUtilisateur.trouverComposanteSelonNom() ;
                    break;
                case 2:

                            controllerUtilisateur.trouverComposanteSelonType() ;
                    break;
                case 3:
                            controllerUtilisateur.trouverComposanteSelonFournisseur();
                    break;
            }
        }catch (Exception e){
            System.out.println(" Veuillez entrer un nombre valide.");
        }
    }

    public void trouverFournisseur(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir une option de recherche de fournisseur:\n " +
                "[1] : Par nom \n [2] : Par type \n ");
        int choix = scanner.nextInt();
        switch (choix){
            case 1:
                controllerUtilisateur.trouverFournisseurSelonNom();
                break;
            case 2:
                controllerUtilisateur.trouverFournisseurSelonType();
                break;
            default:
                System.out.println("Veuillez choisir correctement 1 ou 2");

        }
    }
}
