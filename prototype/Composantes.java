import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public   class Composantes {

    private int inventaire=0;
    private String nom;
    private Float prix;
    private String description;
    private String type ;
    private static List<Class<? extends Composantes>> listeSousClasses = new ArrayList<>(List.of(
            CPU.class, Roue.class, Bras.class, Helice.class, Camera.class, HautParleur.class, Micro.class, Ecran.class));

    // todo: ajouter Description à assigner composante !!!!


    public static Class<? extends Composantes> choisirCompo() {
        Scanner scanner = new Scanner(System.in);

            // Affichage des options disponibles
            for (int i = 0; i < listeSousClasses.size(); i++) {
                System.out.println("[" + i + "] : " + listeSousClasses.get(i).getSimpleName());
            }

            // Lecture de l'entrée utilisateur
        while (true) {
            try {
                System.out.println("Veuillez choisir quelle composante utiliser :");
                int choix = scanner.nextInt();

                // Vérification de l'index choisi
                if (choix >= 0 && choix < listeSousClasses.size()) {

                    // Utilisation de la réflexion pour instancier la classe choisie
                    Class<? extends Composantes> classeChoisie = listeSousClasses.get(choix);
                    System.out.println(classeChoisie.getSimpleName());

                    return classeChoisie;
                } else {
                    System.out.println("Choix invalide. Veuillez choisir un nombre parmi les options listées.");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Entrée non valide. Veuillez entrer un nombre.");
                scanner.next(); // Consommer l'entrée incorrecte
            }
        }
    }


   public void setNom(String nom) {
       this.nom = nom;
   }
   public String getNom(){
       return nom;
   }
   public void setPrix(Float prix) {
       this.prix = prix;
   }
   public void setDescription(String description) {
       this.description = description;
   }
   public void setType(String type) {
       this.type = type;
   }
   public void setQuantite(int inventaire){
       this.inventaire = inventaire;
   }

    // Getter pour accéder au tableau
    public Float getPrix() {
        return prix;
    }
    // au moins 2 composantes



    public int getInventaire() {
        return inventaire;
    }
    // incrementer et decrementer

    // Method to increment inventaire
    public  void incrementInventaire(){
        inventaire++;
    }


    // Setter for inventaire
    public  void setInventaire(int inventaire){
        this.inventaire = inventaire;
    }


}


