import java.util.Scanner;

public class MenuFournisseur extends View{
    private Compte loggedAccount;
    private ControllerFournisseur controllerFournisseur;

    /**
     * Affiche le menu pour le fournisseur et permet de naviguer entre différentes options.
     *
     * Cette méthode affiche un menu interactif où le fournisseur peut choisir parmi les options suivantes :
     * - Retourner au menu principal
     * - Modifier son profil
     * - Afficher ses composantes
     * - Gérer ses composantes
     *
     */
    public void afficherMenuFournisseur() {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("Menu :");
            System.out.println("[0] : \n Retour au menu principal \n [1] : Modifier mon profil \n [2] : " +
                    "Afficher mes composantes + \n [3] : Gérer mes composantes");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 0:
                    System.out.println("Au revoir !");
                    continuer = false;
                    break;


            }
        }
    }



    /**
     * Gère les différentes actions possibles sur les composantes.
     *
     * Cette méthode permet à l'utilisateur de choisir parmi les options suivantes :
     * - Retourner au menu principal
     * - Enregistrer une composante
     * - Modifier une composante
     * - Supprimer une composante
     */
    /*public void gererComposantes(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Veuillez choisir quelle action effectuer ! \n [0] : retour au menu" +
                    "[1] : Enregistrer une composante \n [2] : Modifier une composante \n [3 ]:  Supprimer une " +
                    "composante");

            int choix = scanner.nextInt();
            switch (choix){
                case 0:
                    System.out.println("Au revoir !");
                    break;
                case 1:
                    controllerFournisseur.enregistrerComposante();
                    break;
                case 2:
                    controllerFournisseur.modifierComposante();
                    break;
                case 3:
                    controllerFournisseur.supprimerComposante();
                    break;
                default:
                    System.out.println("Veuillez choisir une option  correcte!");
            }
        }catch (Exception e){
            System.out.println("veuillez choisir un nombre valide  !");
        }
    }*/

}
