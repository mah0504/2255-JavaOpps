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
                    "[2] : enregistrer une composante  \n " +
                    "[3] : modifier une composante  \n " +
                    "[4] : supprimer une composante \n " );
            int choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 0:
                    continuer = false;
                    break;
                case 1:
                    controllerFournisseur.modifierProfil();
                    break;
                case 2:
                    controllerFournisseur.enregistrerComposante();
                    break;
                case 3:
                    controllerFournisseur.supprimerComposante();
                    break;
                case 4:
                    controllerFournisseur.modifierComposante();
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez entrer un nombre valide.");
                    break;
            }
        }
    }
}
