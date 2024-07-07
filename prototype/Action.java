
import java.util.*;
public class Action{

    private ArrayList<Action> listeActions = new ArrayList<>();
    private String type;
    private String nom;

    // todo:

    public ArrayList<Action> getListeActions(){
        return listeActions;
    }

    public void supprimer(Action action, int index) {
        action.getListeActions().remove(index);

    }

    public void modifier(Action action, int index) {

    }

    // verifier composantes
    // caract
    public String setNom(){
        return "egrvd";
    }

    public String setType(){
        return "egrvd";
    }  // modifier apres


    // mouvement , diffusion de son, parler , ecouter, afficher du texte/ graphique
}
