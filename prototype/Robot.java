import java.util.*;

public class Robot {

    private int numeroDeSerie;
    private String nom;
    private String type;

    private Object[] etats = new Object[4]; // Tableau pour stocker les états
    private List<Composantes> composantesRobot = new ArrayList<>();
    private List<Metrique> metriquesRobot = new ArrayList<>();
    // todo : controle des mouvements


    public void afficherInformations() {

        System.out.println("Numéro de série : " + numeroDeSerie);
        System.out.println("Nom : " + nom);
        System.out.println("Type : " + type);
        System.out.println("États : ");

    }


// getters et setters
    public String getNom(){ return this.nom; }
    public List<Composantes> getListCompoRobot(){return this.composantesRobot;}
    public Object[] getEtats(){ return this.etats; }

    public List<Metrique> getMetriquesRobot(){ return this.metriquesRobot;}

    public void setNumeroDeSerie(int numeroDeSerie) {this.numeroDeSerie = numeroDeSerie;}

    public void setNom(String nom) {this.nom = nom;}

    public void setType(String type) {this.type = type;}

    public void addMetriquesRobot( Metrique metrique){ metriquesRobot.add(metrique);}

    public void setMetriquesRobot(List<Metrique> me){ this.metriquesRobot=me;}

    public void setEtats(Object[] etats) {this.etats = etats; }

    @Override
    public String toString() {
        return "Robot{" +
                "numeroDeSerie=" + numeroDeSerie +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", etats=" + Arrays.toString(etats) +
                ", composantesRobot=" + composantesRobot +
                '}';
    }
}
