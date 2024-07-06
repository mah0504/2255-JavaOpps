import java.util.*;

public class Robot {
   // private Object[] metriques;

    private int numeroDeSerie;
    private String nom;
    private String type;
    private boolean disponibilite;

    private List<Composantes> composantesRobot = new ArrayList<>() ;

    public Composantes getElemComposantesRobot(int i){
        return composantesRobot.get(i);  // retourne un element recherche de la liste
        // faut-il ajouter un getString?
    }
    public List<Composantes> getListCompoRobot(){
        return composantesRobot;
    }



    public void setNumeroDeSerie(int numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;  //  celui fournit depuis le CPU
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    // pr
    @Override
    public String toString() {
        return "Robot{" +
                "nom='" + nom  + ", type='" + type + ", numeroDeSerie=" + numeroDeSerie +
                ", composantesRobot=" + composantesRobot + '}';
    }

// L'état d'un robot présente sa position, sa vitesse, son niveau de batterie, sa consommation CPU et mémoire

// Les métriques (indicateurs, ratios agrégés par période)



}
