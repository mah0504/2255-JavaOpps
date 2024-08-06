import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuUtilisateur{

    private Scanner scanner;
    private Utilisateur utilisateur;
    private ControllerUtilisateur controllerUtilisateur;

    /**
     * Crée une instance pour la classe MenuUtilisateur.
     * Elle permet d'initialiser le scanner aussi
     *
     * @param utilisateur l'utilisateur associé à ce menu.
     * @param controllerUtilisateur le {@link ControllerUtilisateur} qui permet de gérer la
     *                              logique entre les options du menu et les actions que peut faire
     *                              un {@link Utilisateur}
     */
    public MenuUtilisateur(Utilisateur utilisateur,ControllerUtilisateur controllerUtilisateur){
        this.scanner = new Scanner(System.in);
        this.utilisateur = utilisateur;
        this.controllerUtilisateur = controllerUtilisateur;
    }

    /**
     * Permet d'afficher les options du Menu Fournisseur
     */
    public void afficherMenuUtilisateur(){
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        while (continuer) {
            try{
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
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
