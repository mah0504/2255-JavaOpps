import java.util.Scanner;

public class MenuUtilisateur{

    private Scanner scanner;
    private ControllerUtilisateur controllerUtilisateur;

    public MenuUtilisateur(){
        this.scanner = new Scanner(System.in);
    }

    public void afficherMenuUtilisateur() {
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
                    "[6] : Voir mes notifications ");
            int choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 0:
                    continuer = false;
                    break;
                case 1:

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
                    controllerUtilisateur.trouverFournisseur();
                    break;
                case 6:
                    controllerUtilisateur.voirNotifs();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
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
    public void gererFlotte(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(" Bienvenue dans votre flotte !" +
                    " \n Veuillez choisir quelle action effectuer: \n" +
                    "[1] : enregistrer un robot \n " +
                    "[2] : Supprimer un robot \n " +
                    "[3] : Trouver une composante \n" +
                    "[4] :Acheter une composante \n ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    controllerUtilisateur.enregistrerRobot(new Robot());
                    break;
                case 2:
                    controllerUtilisateur.supprimerRobot();
                    break;
                case 3:
                    trouverComposante();
                    break;
                case 4:
                    controllerUtilisateur.acheterComposante();
                    break;
                default:
                    System.out.println("Veuillez entrer un nombre valide.");
                    break;
            }

        }catch (Exception e){
            System.out.println(" Veuillez entrer un nombre valide.");
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

                    controllerUtilisateur.afficherCompoRecherche(
                            controllerUtilisateur.trouverComposanteSelonNom() );
                    break;
                case 2:
                    controllerUtilisateur.afficherCompoRecherche(
                            controllerUtilisateur.trouverComposanteSelonType()) ;
                    break;
                case 3:
                    controllerUtilisateur.afficherCompoRecherche(
                            controllerUtilisateur.trouverComposanteSelonFournisseur());
                    break;
            }
        }catch (Exception e){
            System.out.println(" Veuillez entrer un nombre valide.");
        }
    }

}
