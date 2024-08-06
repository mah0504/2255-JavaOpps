import java.util.ArrayList;

public class UtilisateurService {

    private ArrayList<Utilisateur> utilisateurs;

    public UtilisateurService() {
        utilisateurs = new ArrayList<>();
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    public Utilisateur findUserByEmail(String email) {
        for (Utilisateur compte : utilisateurs) {
            if (compte.getEmail().equals(email)) {
                return compte;
            }
        }
        return null;
    }

    public boolean verifierConnexion(String email, String mdp){
        Utilisateur compte = findUserByEmail(email);
        if(compte != null && compte.getMdp().equals(mdp) && compte.getConfirmed()){
            return true;
        }
        return false;
    }

}
