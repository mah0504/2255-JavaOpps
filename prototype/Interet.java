import java.util.*;

public class Interet {

    private String nom;
    private static ArrayList<Interet> listeInteretsGeneraux;
    public static Map<Interet, List<Activite>> activitesByInteret;

    // Constructor
    public Interet(String nom) {
        this.nom = nom;
    }
    public Interet(){

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

    public void initialiserListeInterets() {

        Systeme.getInstance().ajouterinterets(new Interet("Education"));
        Systeme.getInstance().ajouterinterets(new Interet("Education"));
        Systeme.getInstance().ajouterinterets(new Interet("Lecture"));
        Systeme.getInstance().ajouterinterets(new Interet("Création"));
        Systeme.getInstance().ajouterinterets(new Interet("Art"));
        Systeme.getInstance().ajouterinterets(new Interet("PacMan"));
        Systeme.getInstance().ajouterinterets(new Interet("Jeu d'échec"));
        Systeme.getInstance().ajouterinterets(new Interet("Sport"));
        Systeme.getInstance().ajouterinterets(new Interet("Musique"));
        Systeme.getInstance().ajouterinterets(new Interet("Cinema"));
        Systeme.getInstance().ajouterinterets(new Interet("Domotique"));

    }
}

