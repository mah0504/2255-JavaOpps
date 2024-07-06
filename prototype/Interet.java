import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Interet{
    private String nom;
    private static ArrayList<Interet> listeInteretsGeneraux;
    private static Map<Interet, ArrayList<Activite>> ActivitesByInteret = new HashMap<>();
    private ArrayList<Activite> listeActivites; // liste des Activités associés à cet intéret

    // Constructor
    public Interet(String nom) {
        this.nom = nom;
    }
    public Interet() {
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
        ArrayList<Interet> listeInterets = utilisateur.getListeInterets();
        if (listeInterets.size() < 10) {
            listeInterets.add(this);
        } else {
            System.out.println("Vous avez déjà 10 intérêts !.");
        }
    }

    // Associer une activité à un intérêt
    public void associerActivite(Activite activite) {
        listeActivites.add(activite);
    }

    // Récupérer toutes les activités ayant le même intérêt
    public ArrayList<Activite> getlisteActivites() {
        return listeActivites;
    }

    // Récupérer tous les intérêts de notre Robotix
    public static ArrayList<Interet> getListeInteretsGeneraux() {
        return listeInteretsGeneraux;
    }

    public static void initialiserListeInterets() {
        ArrayList<Interet>  listeInteretsGeneraux = new ArrayList<>();
        listeInteretsGeneraux.add(new Interet("Education"));
        listeInteretsGeneraux.add(new Interet("Lecture"));
        listeInteretsGeneraux.add(new Interet("Création"));
        listeInteretsGeneraux.add(new Interet("Art"));
        listeInteretsGeneraux.add(new Interet("PacMan"));
        listeInteretsGeneraux.add(new Interet("Jeu d'échec"));
        listeInteretsGeneraux.add(new Interet("Sport"));
        listeInteretsGeneraux.add(new Interet("Musique"));
        listeInteretsGeneraux.add(new Interet("Cinema"));
        listeInteretsGeneraux.add(new Interet("Domotique"));

    }
}
