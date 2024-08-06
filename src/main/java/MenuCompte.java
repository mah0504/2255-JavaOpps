import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCompte {

    private Scanner scanner = new Scanner(System.in);

    private View view;
    private ControllerUtilisateur controllerUtilisateur;
    private ControllerFournisseur controllerFournisseur;

    private MenuUtilisateur menuUtilisateur;
    private ControllerRobot controllerRobot;
    private MenuFournisseur menuFournisseur;
    private CompteView compteView;

    public MenuCompte(View view) {
        this.view = view;
        this.controllerUtilisateur = new ControllerUtilisateur(this, view);
        this.controllerFournisseur = new ControllerFournisseur(this, view);
    }

    /*public MenuCompte(ControllerUtilisateur controllerUtilisateur, ControllerFournisseur controllerFournisseur, ControllerRobot controllerRobot) {
        this.controllerUtilisateur =  controllerUtilisateur;
        this.controllerFournisseur = controllerFournisseur;
        this.controllerRobot= controllerRobot;
        //this.menuUtilisateur = new MenuUtilisateur(controllerUtilisateur);
        //this.menuFournisseur= new MenuFournisseur(controllerFournisseur);

        compteView = new CompteView();
    }*/

    /**
     * Définit le compte en tant qu'un Utilisateur ou un Fournisseur et affiche le
     * ménu correspondant. En fonction du cas, le menu spécifique s'affiche, peut-être
     * une instance de {@link MenuUtilisateur} ou {@link MenuFournisseur}
     *
     * @param compte l'objet {@link Compte} à traiter, peut-être une instance {@link Utilisateur}
     *                ou une instance {@link Fournisseur}
     */
    public void setCompte(Compte compte){
        if (compte instanceof Utilisateur){
            new MenuUtilisateur((Utilisateur) compte, controllerUtilisateur).afficherMenuUtilisateur();
        } else if (compte instanceof Fournisseur){
            new MenuFournisseur((Fournisseur) compte, controllerFournisseur).afficherMenuFournisseur();
        }

    }

    /**
     * Permet d'afficher les options pour le rôle.
     * 0 = Quitter L'application
     * 1 = Les différentes actions en tant qu'Utilisateur
     * 2 = Les différentes actions en tant qe Fournisseur
     */
    public void afficherMenu(){
        boolean continuer = true;
        while(continuer){
            try{
                view.home();
                int choix = Integer.parseInt(scanner.nextLine());

                switch (choix){
                    case 1:
                        afficherMenuUtilisateurConnexion();
                        break;
                    case 2 :
                        afficherMenuFournisseurConnexion();
                        break;
                    case 0 :
                        view.afficherMessage("Au Revoir !");
                        continuer = false;
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez réessayez !");

                }
            } catch (InputMismatchException e){
                System.out.println(e.getMessage());
                scanner.next();
            }
        }
    }

    /**
     * Permet d'afficher les options du menu de connexion pour l'utilisateur
     * 0 = Retour en arrière
     * 1 = Se connecter et puis mène vers le menu spécifique pour l'utilisateur
     * 2 = S'Inscrire
     * 3 = Confirmer l'inscription
     */
    public void afficherMenuUtilisateurConnexion() {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            try {
                view.homeCompte();
                int choix = Integer.parseInt(scanner.nextLine());

                switch (choix) {
                    case 0:
                        view.afficherMessage("Retour au Menu Principal");
                        continuer = false;
                        break;
                    case 1:
                        controllerUtilisateur.seConnecter();
                        break;
                    case 2:
                        controllerUtilisateur.sInscrire();
                        break;
                    case 3:
                        controllerUtilisateur.confirmerInscription();
                        break;
                    default:
                        compteView.AfficherMessage("Choix invalide, réessayez!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    /**
     * Permet d'afficher les options du menu de connexion pour le fournisseur
     * 0 = Retour en arrière
     * 1 = Se connecter et puis mène vers le menu spécifique pour le fournisseur
     * 2 = S'Inscrire
     * 3 = Confirmer l'inscription
     */
    public void afficherMenuFournisseurConnexion() {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            try {
                view.homeCompte();
                int choix = Integer.parseInt(scanner.nextLine());

                switch (choix) {
                    case 0:
                        view.afficherMessage("Retour au Menu Principal");
                        continuer = false;
                        break;
                    case 1:
                        controllerFournisseur.seConnecter();
                        break;
                    case 2:
                        controllerFournisseur.sInscrire();
                        break;
                    case 3:
                        controllerFournisseur.confirmerInscription();
                        break;
                    default:
                        view.afficherMessage("Choix invalide, réessayez !");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

}
