import java.util.ArrayList;

public class UtilisateurService {

    private ArrayList<Utilisateur> utilisateurs;

    /**
     * Crée une nouvelle instance de la classe UtilisateurService
     *Ell initialise une liste de {@link Utilisateur} vide
     */
    public UtilisateurService() {
        utilisateurs = new ArrayList<>();
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

}
