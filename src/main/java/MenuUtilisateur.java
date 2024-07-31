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
            System.out.println("1. Gérer les activités");
            System.out.println("2. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    controllerUtilisateur.gererActivites();
                    break;
                case 2:
                    System.out.println("Au revoir !");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
            }
        }
    }
}
