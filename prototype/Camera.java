public class Camera extends Composantes{
    private int inventaire;
    // Method to increment inventaire
    @Override
    public void incrementInventaire() {
        this.inventaire++;
    }

    // Setter for inventaire
    @Override
    public void setInventaire(int inventaire) {
        this.inventaire = inventaire;
    }
}
