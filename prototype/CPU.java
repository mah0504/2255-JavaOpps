public class CPU extends Composantes {
    private int numDeSerie;

    public CPU(String nom, int inventaire) {
        super(nom, inventaire);
    }


    public int getNumDeSerie() {
        return numDeSerie;
    }
}
