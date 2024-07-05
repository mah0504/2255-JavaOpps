public class Composantes {

    private int inventaire=0;
    private String nom;
    private Float prix;
    private String type;



    public Composantes(String nom, int inventaire) {
        this.nom = nom;
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


   public String toString(Composantes composante ){
        return "type=" + type ;
   }

    // Setter for inventaire
    public void setInventaire(int inventaire) {
        this.inventaire = inventaire;
    }

}


