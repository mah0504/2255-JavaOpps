import java.util.*;

public class Robot {

    private int numeroDeSerie;
    private String nom;
    private String type;
    private boolean disponibilite;
    private Object[] etats = new Object[4]; // Tableau pour stocker les états
    private List<Composantes> composantesRobot = new ArrayList<>();

    // todo : controle des mouvements


    public void afficherInformations() {

        System.out.println("Numéro de série : " + numeroDeSerie);
        System.out.println("Nom : " + nom);
        System.out.println("Type : " + type);
        System.out.println("Disponibilité : " + (disponibilite ? "Disponible" : "Non disponible"));
        System.out.println("États : ");
        for (Object etat : etats) {
            System.out.println(etat);

        }
    }

    public List<Composantes> getListCompoRobot(){
        return this.composantesRobot;
    }

    public void setNumeroDeSerie(int numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "numeroDeSerie=" + numeroDeSerie +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", disponibilite=" + disponibilite +
                ", etats=" + Arrays.toString(etats) +
                ", composantesRobot=" + composantesRobot +
                '}';
    }
}
