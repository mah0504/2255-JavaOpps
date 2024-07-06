import java.util.ArrayList;

public class Interets{
    private String nom;

    // Constructor
    public Interets(String nom) {
        this.nom = nom;
    }

    // Getters and Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Méthodes
    public void souscrire(Utilisateur utilisateur) {
        ArrayList<Interets> listeInterets = utilisateur.getListeInterets();
        if (listeInterets.size() < 10) {
            listeInterets.add(this);
        } else {
            System.out.println("Vous avez déjà 10 intérêts !.");
        }
    }

    public void associerActivite() {
        // pour chaque actvité, associer un interet
    }

    public static void initialiserListeInterets() {
        ArrayList<Interets> listeInterets = new ArrayList<>();
        listeInterets.add(new Interets("Education"));
        listeInterets.add(new Interets("Lecture"));
        listeInterets.add(new Interets("Création"));
        listeInterets.add(new Interets("Art"));
        listeInterets.add(new Interets("PacMan"));
        listeInterets.add(new Interets("Jeu d'échec"));
        listeInterets.add(new Interets("Sport"));
        listeInterets.add(new Interets("Musique"));
        listeInterets.add(new Interets("Cinema"));
        listeInterets.add(new Interets("Domotique"));

    }
}
