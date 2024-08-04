import java.util.Scanner;

public class MenuFournisseur{

    private Scanner scanner;
    private ControllerFournisseur controllerFournisseur;
    public MenuFournisseur(ControllerFournisseur controllerFournisseur ){
        this.scanner = new Scanner(System.in);
        this.controllerFournisseur = controllerFournisseur;
    }

    public void afficherMenuFournisseur() {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("Menu :");
            System.out.println(" [0] : Retour au menu principal \n " +
                    "[1] : modifier mon profil  \n " +
                    "[2] :  \n " +
                    "[3] :   \n " +
                    "[4] :   \n " +
                    "[5] :   \n " +
                    "[6] :  ");
            int choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 0:
                    continuer = false;
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:

                default:
                    System.out.println("Choix invalide. Veuillez entrer un nombre valide.");
            }
        }
    }
}
