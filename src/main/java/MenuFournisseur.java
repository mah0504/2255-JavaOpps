import java.util.Scanner;

public class MenuFournisseur{

    private Scanner scanner;
    private Fournisseur fournisseur;
    private ControllerFournisseur controllerFournisseur;

    public MenuFournisseur(Fournisseur fournisseur, ControllerFournisseur controllerFournisseur ){
        this.scanner = new Scanner(System.in);
        this.fournisseur = fournisseur;
        this.controllerFournisseur = controllerFournisseur;
    }

    public void afficherMenuFournisseur(){
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        while (continuer) {
            System.out.println("Que souhaitez-vous faire ?");
            System.out.println("[0] : Se déconnecter (Retour au Menu Principal)");
            System.out.println("[1] : Modifier le profil");
            System.out.println("[2] : Enregistrer une composante");
            System.out.println("[3] : Modifier une composante");
            System.out.println("[4] : Supprimer une composante");
            System.out.println("[5] : Voir ses composantes");
            int choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 0:
                    continuer = false;
                    break;
                case 1:
                    controllerFournisseur.modifierProfilFournissuer(fournisseur);
                    break;
                case 2:
                    controllerFournisseur.enregistrerComposante(fournisseur);
                    break;
                case 3:
                    controllerFournisseur.modifierComposante(fournisseur);
                    break;
                case 4:
                    controllerFournisseur.supprimerComposante(fournisseur);
                    break;
                case 5 :
                    controllerFournisseur.afficherComposantes(fournisseur);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez entrer nombre valide dan la borne.");
            }
        }
    }

    /**public void afficherMenuFournisseur() {
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
    }*/
}
