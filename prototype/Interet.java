import java.util.ArrayList;

public class Interet {

    private String nom;

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






//
//    public Interet choisirInteret(){
//        return Interet interet ;
//    }




    public void associerActivite() {
        // pour chaque actvité, associer un interet
    }

    public static void initialiserListeInterets() {
        ArrayList<Interet> listeInterets = new ArrayList<>();
        listeInterets.add(new Interet("Education"));
        listeInterets.add(new Interet("Lecture"));
        listeInterets.add(new Interet("Création"));
        listeInterets.add(new Interet("Art"));
        listeInterets.add(new Interet("PacMan"));
        listeInterets.add(new Interet("Jeu d'échec"));
        listeInterets.add(new Interet("Sport"));
        listeInterets.add(new Interet("Musique"));
        listeInterets.add(new Interet("Cinema"));
        listeInterets.add(new Interet("Domotique"));

    }
}

