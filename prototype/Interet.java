import java.util.*;

public class Interet {

    private String nom;
    private static ArrayList<Interet> listeInteretsGeneraux;
    public static Map<Interet, List<Activite>> activitesByInteret;

    // Constructor
    public Interet(String nom) {
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
        ArrayList<Interet> listeInterets = utilisateur.getListeInterets();
        if (listeInterets.size() < 10) {
            listeInterets.add(this);
        } else {
            System.out.println("Vous avez déjà 10 intérêts !.");
        }
    }

    // Récupérer tous les intérêts de notre Robotix
    public static ArrayList<Interet> getListeInteretsGeneraux() {
        return listeInteretsGeneraux;
    }

    public void associerActivite() {
        // voir ajouterActiviter() dans Activite
    }


    /*
        Cette méthode permet de trier une liste d'activitées par les intérets
        Pour chaque activité, on l'ajoute une liste d'activité associé à l'interet
     */
    public static void MapActivitesByInterets(List<Activite> listedActivites) {
        Map<Interet, List<Activite>> activitesByInteret = new HashMap<>();

        for (Activite activite : listedActivites) {
            for (Interet interet : activite.getListedInterets()) {
                if (!activitesByInteret.containsKey(interet)) {
                    activitesByInteret.put(interet, new ArrayList<>());
                }

                activitesByInteret.get(interet).add(activite);
            }
        }
    }

    public static void initialiserListeInterets() {
        ArrayList<Interet> listeInteretsGeneraux = new ArrayList<>();
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

