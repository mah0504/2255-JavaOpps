
import java.util.*;
public class Action{

    private ArrayList<Action> listeActions;


    public ArrayList<Action> getListeActions(){
        return listeActions;
    }

    public void supprimer(Action action, int index) {
        action.getListeActions().remove(index);

         // index ?
    }


    public void modifier(Action action, int index) {

    }
}
