import java.util.*;

// Gestion des données stockées et maintenir les listes qui les contiennent.

public class Systeme {

    private static Systeme instance;
    private ArrayList<Utilisateur> utilisateurs;
    private ArrayList<Fournisseur> fournisseurs;
    private ArrayList<Interet> interets;



    private Systeme() {
        utilisateurs = new ArrayList<>();
        fournisseurs = new ArrayList<>();
    }

    // Utilise le modèle Singleton pour une unique instance du Systeme.
    public static Systeme getInstance() {
        if (instance == null) {
            instance = new Systeme();
        }
        return instance;
    }

    // Utilisateurs du système
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    public ArrayList<Utilisateur> getUtilisateurs() {
        return utilisateurs; // return new ArrayList<>(utilisateurs); pour copie qui ne peut pas être modifiée???
    }



    public void ajouterinterets(Interet interet) {
        interets.add(interet); // return new ArrayList<>(utilisateurs); pour copie qui ne peut pas être modifiée???
    }


    public ArrayList<Interet> getInterets(){
        return interets;
    }

    // Fournisseurs du système
    public void ajouterFournisseur(Fournisseur fournisseur) {
        fournisseurs.add(fournisseur);
    }

    public ArrayList<Fournisseur> getFournisseurs() {
        return fournisseurs;
    }



}
