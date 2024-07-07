import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Composantes {

    private int inventaire=0;
    private String nom;
    private Float prix;
    private String description;
    private String type ;
    private static List<Class<? extends Composantes>> listeSousClasses = new ArrayList<>(List.of(
            CPU.class, Roue.class, Bras.class, Helice.class, Camera.class, HautParleur.class, Micro.class, Ecran.class));

    // todo: ajouter Description à assigner composante !!!!



   public Composantes choisirCompo(){
       System.out.println("Veuillez choisir quelle composante utiliser :");
       Scanner scanner =new Scanner(System.in);
       for (int i = 0; i < listeSousClasses.size(); i++) {
           System.out.println("[" + i +"]"+ ": " + listeSousClasses.get(i).getSimpleName());
       }
       int choix= scanner.nextInt();
       scanner.close(); // Fermer le scanner après usage

       // Utiliser un switch pour retourner le type de classe correspondant à l'index choisi
       // Vérifier que l'index choisi est valide
       if (choix >= 0 && choix < listeSousClasses.size()) {
           try {
               // Utiliser la réflexion pour instancier la classe correspondante
               System.out.println( listeSousClasses.get(choix));

               Class<? extends Composantes> classeChoisie = listeSousClasses.get(choix);
               return classeChoisie.getDeclaredConstructor().newInstance();

           } catch (Exception e) {
               e.printStackTrace();
           }

       } System.out.println("Veuillez effectuer un choix valide !");
       return null;
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
    public void incrementInventaire() {
        this.inventaire++;
    }

    // Setter for inventaire
    public void setInventaire(int inventaire) {
        this.inventaire = inventaire;
    }

}


