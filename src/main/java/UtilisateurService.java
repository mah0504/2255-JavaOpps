import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class UtilisateurService {

    private ArrayList<Utilisateur> utilisateurs;
    boolean isMdpValide = true;

    /**
     * Crée une nouvelle instance de la classe UtilisateurService
     *Ell initialise une liste de {@link Utilisateur} vide
     */
    public UtilisateurService() {
        utilisateurs = new ArrayList<>();
    }

    public void setUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    /**
     * Permet d'ajouter un utilisateur à la liste utilisateurs
     * @param utilisateur l'utilisateur à ajouter
     */
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    /**
     * Permet de trouver un {@link Utilisateur} dans une liste d'utilisateurs
     * à partir de son attribut email
     *
     * @param email la variable d'instance quin permet de trouver l'utilisateur
     * @return utilisateur trouvé sinon elle ne retourne rien
     */
    public Utilisateur findUserByEmail(String email) {
        for (Utilisateur compte : utilisateurs) {
            if (compte.getEmail().equals(email)) {
                return compte;
            }
        }
        return null;
    }

    /**
     * Méthode qu'on souhaite tester
     * Permet de vérifier les informations d'un {@link Utilisateur}
     * pour permettre la connexion
     *
     * @param email email de l'utilisateur
     * @param mdp mot de passe de l'utilisateur
     * @return {@code true} si l'email, le mdp correspondent et la variable confirmed de L'utilisateur est vraie
     * {@code false} sinon
     */
    public boolean verifierConnexion(String email, String mdp){
        Utilisateur compte = findUserByEmail(email);
        if(compte != null && compte.getMdp().equals(mdp) && compte.getConfirmed()){
            return true;
        }
        return false;
    }

    public boolean isPseudoUnique(String pseudo){
        for(Utilisateur membre : utilisateurs){
            if(membre.getPseudo().equals(pseudo)){
                return false;
            }
        }
        return true;
    }

    private boolean isEmailUnique(String email){
        for(Utilisateur membre : utilisateurs){
            if(membre.getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }

    private boolean isMdpValide(String mdp){
        return mdp.length() > 7;
    }

    public void setMdpValide(boolean isValide) {
        this.isMdpValide = isValide;
    }

    private boolean isTelephoneValide(String telephone){
        return telephone.length() == 10;
    }

    public void envoyerMailConfirmation(Utilisateur utilisateur){

        String confirmationLien = UUID.randomUUID().toString();

        String DateExpiration = utilisateur.getConfirmationLienExpirationDate();

        utilisateur.setConfirmationLien(confirmationLien);
        utilisateur.setConfirmationLienExpirationDate(DateExpiration);

        System.out.println("Email de confirmation envoyé à : " + utilisateur.getEmail());
        System.out.println("Lien de confirmation : " + confirmationLien);

    }

    public void sInscrire(Utilisateur utilisateur) {

        boolean pseudoUnique = isPseudoUnique(utilisateur.getPseudo());
        boolean emailUnique = isEmailUnique(utilisateur.getEmail());
        boolean mdpValide = isMdpValide(utilisateur.getMdp());
        boolean telephoneValide = isTelephoneValide(utilisateur.getTelephone());

        if( pseudoUnique && emailUnique && mdpValide && telephoneValide){
            utilisateurs.add(utilisateur);
            envoyerMailConfirmation(utilisateur);
            System.out.println("Inscription réussie. En attente de la confirmation de l'email.");
        } else {

        }

    }


}
