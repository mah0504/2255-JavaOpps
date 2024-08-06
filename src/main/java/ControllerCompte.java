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

    public void sInscrire(T compte){
        comptes.add(compte);

    }

    public boolean isPseudoUnique(String pseudo){
        for(T membre : comptes){
            if(membre.getPseudo().equals(pseudo)){
                return false;
            }
        }
        return true;
    }

    private boolean isEmailUnique(String email){
        for(T membre : comptes){
            if(membre.getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }

    private boolean isMdpValide(String mdp){
        return mdp.length() > 7;
    }

    private boolean isTelephoneValide(String telephone){
        return telephone.length() == 10;
    }

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

    public T findUserByEmail(String email) {
        for (T compte : comptes) {
            if (compte.getEmail().equals(email)) {
                return compte;
            }
        }
        return null;
    }

    private boolean verifierConnexion(String email, String mdp){
        T compte = findUserByEmail(email);
        if(compte != null && compte.getMdp().equals(mdp) && compte.getConfirmed()){
            return true;
        }
        return false;
    }

}
