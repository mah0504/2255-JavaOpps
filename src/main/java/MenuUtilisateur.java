import java.util.Scanner;

public class MenuUtilisateur extends View{

    private ControllerUtilisateur controllerUtilisateur;

    public MenuUtilisateur(ControllerUtilisateur controllerUtilisateur) {
        this.controllerUtilisateur = controllerUtilisateur;
    }

    public void afficherMenuUtilisateur() {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("Menu :");
            System.out.println(" [0] : Retour au menu principal \n [1] : modifier mon profil  \n  [2] : Afficher les "
                    +"états  mes robots \n [3] : Gérer ma flotte  \n [4]:Gérer les activités  \n [5] :Trouver un " +
                    "fournisseur \n [5] : Voir mes notifications ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 0:
                    System.out.println("Au revoir !");
                    continuer = false;
                    break;

                case 1:
                    // rajouter modif profl
                    break;
                case 2:
                    controllerUtilisateur.afficherEtatsRobots(); // choisir un robot avant
                   break;
                case 3:
                    gererFlotte();
                    break;
                case 4:
                    controllerUtilisateur.gererActivites();
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
            System.out.println(" Bienvenue dans votre flotte ! \n Veuillez choisir quelle action effectuer:" +
                    "[1] : enregistrer un robot \n [2] : Supprimer un robot \n [3] : Trouver une composante \n [4] :" +
                    "Acheter une composante ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                   controllerUtilisateur.enregistrerRobot(new Robot());
                    break;
                case 2:
                    controllerUtilisateur.supprimerRobot();
                    break;
                case 3:
                    controllerUtilisateur.trouverComposante();
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
}
