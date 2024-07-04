
import java.util.*;
public class Action{

    private ArrayList<Action> listeActions;
    private String type;
    private String nom;


    public ArrayList<Action> getListeActions(){
        return listeActions;
    }

    public void supprimer(Action action, int index) {
        action.getListeActions().remove(index);

         // index ?
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


}
