public class Composantes {
    private String[] composantes;

    public Composantes() {
        this.composantes = new String[]{ "CPU", "Roue", "Bras", "Hélice", "Caméra", "Haut-parleur", "Micro", "Écran"};
    }

    // Getter pour accéder au tableau
    public String[] getComposantes() {
        return composantes;
    }

    // Une méthode pour imprimer un message de test
    public void printEssai() {
        System.out.print("essai1");
    }

    public static void main(String[] args) {
        Composantes composantes = new Composantes();
        composantes.printEssai();
    }
}
