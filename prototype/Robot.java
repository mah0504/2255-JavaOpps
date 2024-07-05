import java.util.*;

public class Robot {
   // private Object[] metriques;

    private int numeroDeSerie;
    private int nom;
    private String type;
    private boolean disponibilite;

    private List<Composantes> composantesRobot ;


    public List<Composantes> getComposantesRobot(){
        return composantesRobot;
    }

    private void ParcourirCompo(List<Composantes> composantesRobot, Composantes composante){
        this.composantesRobot = composantesRobot;
        for ( Composantes c :  composantesRobot){

        }

    }
// L'état d'un robot présente sa position, sa vitesse, son niveau de batterie, sa consommation CPU et mémoire

// Les métriques (indicateurs, ratios agrégés par période)




}
