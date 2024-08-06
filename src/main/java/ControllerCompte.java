import java.util.*;
public abstract class ControllerCompte<T extends Compte>{

    protected View view;
    protected MenuCompte menuCompte;
    protected ArrayList<T> comptes;

    public ControllerCompte(MenuCompte menuCompte, View view) {
        this.view = view;
        this.menuCompte = menuCompte;
        this.comptes =  new ArrayList<>();
    }

    /**
     * Permet de se Connecter à notre application
     * Requiert et vérifie un email et un mot de passe pour se connecter
     *
     * La vérification mène au menu spécifique de chaque membre
     */
    public void seConnecter(){
        Scanner scanner = new Scanner(System.in);
        view.getEmailView();
        String email = scanner.nextLine();
        view.getMotDePasseView();
        String motDePasse = scanner.nextLine();

        for (T compte : comptes) {
            if (verifierConnexion(compte.getEmail(),compte.getMdp())) {
                menuCompte.setCompte(compte);
                return;
            }
        }
        view.afficherMessage("Erreur de connexion");
    }

    /**
     * Permet d'ajouter un compte à la liste des données stockées
     *
     * @param compte Peut-être Utilisateur ou Fournisseur
     */
    public void sInscrire(T compte){
        comptes.add(compte);

    }

    /**
     * Vérifie si le Pseudo est Unique
     *
     * @param pseudo le pseudo du membre
     * @return {@code true} si le pseudo est unique {@code false} sinon
     */
    public boolean isPseudoUnique(String pseudo){
        for(T membre : comptes){
            if(membre.getPseudo().equals(pseudo)){
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si l'email est unique
     *
     * @param email l'email du membre
     * @return {@code true} si l'email est unique {@code false} sinon
     */
    private boolean isEmailUnique(String email){
        for(T membre : comptes){
            if(membre.getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si le mot de passe est valide ( au moins 8 caractères)
     *
     * @param mdp le mot de passe du membre
     * @return {@code true} si le mdp est valide {@code false} sinon
     */
    private boolean isMdpValide(String mdp){
        return mdp.length() > 7;
    }

    /**
     * Vérifie si le téléphone est valide ( 10 carctères)
     * @param telephone le téléphone du membre
     *
     * @return {@code true} si le telephone est valide {@code false} sinon
     */
    private boolean isTelephoneValide(String telephone){
        return telephone.length() == 10;
    }

    /**
     * Récupère le pseudo du membre après avoir vérifié qu'il est unique
     *
     * @return pseudo unique
     */
    public String getPseudoUnique(){
        Scanner scanner = new Scanner(System.in);
        view.getPseudoView();
        String pseudo = scanner.nextLine();

        while (!isPseudoUnique(pseudo)) {
            view.afficherMessage("Ce pseudo existe déjà, réessayez !");
            view.getPseudoView();
            pseudo = scanner.nextLine();
        }
        return pseudo;
    }

    /**
     * Récupère l'email après avoir vérifié qu'il est unique
     *
     * @return email unique
     */
    public String getEmailUnique() {
        Scanner scanner = new Scanner(System.in);
        view.getEmailView();
        String email = scanner.nextLine();

        while (!isEmailUnique(email)) {
            view.afficherMessage("Cet email existe déjà, réessayez !");
            view.getEmailView();
            email = scanner.nextLine();
        }
        return email;
    }

    /**
     * Récupère le mot de passe après avoir vérifié qu'il est valide
     * @return mdp valide
     */
    public String getMdpValid() {
        Scanner scanner = new Scanner(System.in);
        view.getMotDePasseView();
        String mdp = scanner.nextLine();

        while (!isMdpValide(mdp)) {
            view.afficherMessage("Le mot de passe doit avoir au moins 8 caractères.");
            view.getMotDePasseView();
            mdp = scanner.nextLine();
        }
        return mdp;
    }

    /**
     * Récupère le téléphone après avoir vérifié qu'il est valide
     *
     * @return téléphone valide
     */
    public String getTelephoneValid() {
        Scanner scanner = new Scanner(System.in);
        view.getTelephoneView();
        String telephone = scanner.nextLine();

        while (!isTelephoneValide(telephone)) {
            view.afficherMessage("Le numéro de téléphone n'est pas valide.");
            view.getTelephoneView();
            telephone = scanner.nextLine();
        }
        return telephone;
    }

    /**
     * Permet d'envoyer un mail avec un lien de confirmation au membre après
     * l'inscription
     *
     * @param compte le compte (Utilisateur ou Fournisseur) qui vient de s'inscrire
     */
    public void envoyerMailConfirmation(T compte){

        //Initialiser le lien de confirmation
        String confirmationLien = UUID.randomUUID().toString();

        //Initialiser la Date d'expiration de ce lien
        String DateExpiration = compte.getConfirmationLienExpirationDate();

        //Assigner les nouvelles valeurs
        compte.setConfirmationLien(confirmationLien);
        compte.setConfirmationLienExpirationDate(DateExpiration);

        //envoyer le mail de confirmation
        System.out.println("Email de confirmation envoyé à : " + compte.getEmail());
        System.out.println("Lien de confirmation : " + confirmationLien);

    }

    /**
     * Permet de retouver un membre par son email
     * @param email l'email du membre qu'on souhaite retrouver
     * @return Utilisateur ou fournisseur qu'on veut trouver
     */
    public T findUserByEmail(String email) {
        for (T compte : comptes) {
            if (compte.getEmail().equals(email)) {
                return compte;
            }
        }
        return null;
    }

    /**
     * Permet de vérifier si un compte est connecté
     * Nécessite de vérifier l'exactitude du mot de passe par rapport à l'email
     * et si l'instance confirmed est {@code true}
     * @param email email qu'on utilise pour la connexion
     * @param mdp le mot de passe associé à l'email
     * @return {@code true} si la connexion est vérifiée, {@code false} sinon
     */
    private boolean verifierConnexion(String email, String mdp){
        T compte = findUserByEmail(email);
        if(compte != null && compte.getMdp().equals(mdp) && compte.getConfirmed()){
            return true;
        }
        return false;
    }

}
