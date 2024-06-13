public class Composantes {
    private String[] composantes;

    public Composantes() {
        this.composantes = new String[]{ "CPU", "Roue", "Bras", "Hélice", "Caméra", "Haut-parleur", "Micro", "Écran"};


        // on crée des tableaux de composantes dans fournisseur et utilisateur et on les remplie avec les 
        // elements de celui-là selon les bsns choix blabla
    }

    // Getter pour accéder au tableau
    public String[] getComposantes() {
        return composantes;
    } 
}

