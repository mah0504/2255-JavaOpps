import java.util.Scanner;

public class MenuUtilisateur{

    private Scanner scanner;

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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez entrer 1 ou 2.");
            }
        }
    }
}
