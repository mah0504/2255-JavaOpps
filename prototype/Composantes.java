public class Composantes {

    private int inventaire=0;
    private String nom;
    private Float prix;



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


