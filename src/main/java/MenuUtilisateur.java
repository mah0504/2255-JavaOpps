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
            System.out.println(" [0] : Quitter \n [1] : Gérer les qactivités \n  [2] : Afficher les " +
                    "états généraux mes robots \n [3] : Enregistrer un robot \n [4]: Supprimer un robot  ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 0:
                    System.out.println("Au revoir !");
                    continuer = false;
                    break;

                case 1:
                    controllerUtilisateur.gererActivites();
                    break;
                case 2:
                    controllerUtilisateur.afficherEtatsRobots(); // choisir un robot avant
                   break;
                case 3:
                  //  controllerUtilisateur.enregistrerRobot();
                    break;
                case 4:
                     controllerUtilisateur.supprimerRobot();
                     break;
                case 5:

                case 6:


                default:
                    System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
            }
        }
    }
}
