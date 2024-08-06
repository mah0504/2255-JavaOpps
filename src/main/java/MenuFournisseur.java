import java.util.Scanner;

public class MenuFournisseur{

    private Scanner scanner;
    private Fournisseur fournisseur;
    private ControllerFournisseur controllerFournisseur;

    /**
     * Crée une instance pour la classe MenuFournisseur.
     * Elle permet d'initialiser le scanner aussi
     *
     * @param fournisseur le fournsiseur associé à ce menu.
     * @param controllerFournisseur le {@link ControllerFournisseur} qui permet de gérer la
     *                              logique entre les options du menu et les actions que peut faire
     *                              un {@link Fournisseur}
     */
    public MenuFournisseur(Fournisseur fournisseur, ControllerFournisseur controllerFournisseur ){
        this.scanner = new Scanner(System.in);
        this.fournisseur = fournisseur;
        this.controllerFournisseur = controllerFournisseur;
    }

    /**
     * Permet d'afficher les options du Menu Fournisseur
     */
    public void afficherMenuFournisseur(){
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        while (continuer) {
            try {
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.next();
            }

        }
    }
}
