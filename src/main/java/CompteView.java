import java.util.Scanner;

public class CompteView {

    private Scanner scanner;

    public CompteView() {
        this.scanner = new Scanner(System.in);
    }

    public void fermerScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

    /**
     * Récupère le type de Compte (Utilisateur ou Fournisseur)
     *
     * @return entier pour représenter le choix
     *          1 = Utilisateur
     *          2 = Fournisseur
     */
    public int getCompteType(){
        System.out.println("Rôle : ");
        System.out.println("1:Utilisateur");
        System.out.println("2:Fournisseur");
        System.out.println("3:Quitter");
        return Integer.parseInt(scanner.nextLine());
    }

    public int getActionType(){
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("0 : Retour au Menu Principal");
        System.out.println("1: Se Connecter");
        System.out.println("2: S'Inscrire");
        System.out.println("3: Confirmer Inscription");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Récupère le pseudo lors de l'inscription
     *
     * @return pseudo saisi par le membre
     */
    public String getPseudo(){
        System.out.println("Entrez un Pseudo :");
        return scanner.nextLine().trim();
    }

    public String getNom(){
        System.out.println("Entrez un nom : ");
        return scanner.nextLine().trim();
    }

    public String getPrenom(){
        System.out.println("Entrez un prenom : ");
        return scanner.nextLine().trim();
    }

    /**
     * Récupère l'email lors de l'inscription
     *
     * @return email saisi par le membre
     */
    public String getEmail(){
        System.out.println("Entrez un Email :");
        return scanner.nextLine().trim();
    }

    /**
     * Récupère le mot de Passe lors de l'inscription
     *
     * @return mdp saisi par le membre
     */
    public String getMotDePasse(){
        System.out.println("Entrez un mot de passe :");
        return scanner.nextLine().trim();
    }

    /**
     * Récupère le telephone lors de l'inscription
     *
     * @return telephone saisi par le membre
     */
    public String getTelephone(){
        System.out.println("Entrez un telephone :");
        return scanner.nextLine().trim();
    }

    /**
     * Récupère le nom de la compagnie lors de l'inscription
     *
     * @return nom de comapagnie saisi par le membre
     */
    public String getCompagnie(){
        System.out.println("Entrez le nom de votre compagnie : ");
        return scanner.nextLine().trim();
    }

    /**
     * Permet d'afficher un message
     *
     * @param message le message à afficher
     */
    public void AfficherMessage(String message){
        System.out.println(message);
    }

    public String getConfirmationLien(){
        System.out.println("Entrez la confirmation du lien : ");
        return scanner.nextLine().trim();
    }

}
