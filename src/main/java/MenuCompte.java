import java.util.Scanner;

public class MenuCompte {

    private Scanner scanner;

    /**
     * Une nouvelle instance de la classe MenuCompte
     * Permet d'initialiser le scanner pour lire les entrées
     *
     */
    public MenuCompte() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Récupère le type de Compte (Utilisateur ou Fournisseur)
     *
     * @return entier pour représenter le choix
     *          1 = Utilisateur
     *          2 = Fournisseur
     *          3 = Quitter
     */
    public int getCompteType(){
        System.out.println("Inscription : ");
        System.out.println("1: S'inscrire comme Utilisateur");
        System.out.println("2: S'inscrire comme Fournisseur");
        System.out.println("3: Quitter");
        return scanner.nextInt();
    }

    /**
     * Récupère le pseudo lors de l'inscription
     *
     * @return pseudo saisi par le membre
     */
    public String getPseudo(){
        System.out.println("Entrez un Pseudo :");
        return scanner.nextLine();
    }

    /**
     * Récupère l'email lors de l'inscription
     *
     * @return email saisi par le membre
     */
    public String getEmail(){
        System.out.println("Entrez un Email :");
        return scanner.nextLine();
    }

    /**
     * Récupère le mot de Passe lors de l'inscription
     *
     * @return mdp saisi par le membre
     */
    public String getMotDePasse(){
        System.out.println("Entrez un mot de passe :");
        return scanner.nextLine();
    }

    /**
     * Récupère le telephone lors de l'inscription
     *
     * @return telephone saisi par le membre
     */
    public String getTelephone(){
        System.out.println("Entrez un telephone :");
        return scanner.nextLine();
    }

    /**
     * Récupère le nom de la compagnie lors de l'inscription
     *
     * @return nom de comapagnie saisi par le membre
     */
    public String getCompagnie(){
        System.out.println("Entrez le nom de votre compagnie : ");
        return scanner.nextLine();
    }

    /**
     * Permet d'afficher un message
     *
     * @param message le message à afficher
     */
    public void AfficherMessage(String message){
        System.out.println(message);
    }

}
