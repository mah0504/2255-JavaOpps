
import java.util.*;
public class Action extends Gestionnaire{

    private ArrayList<Action> listeActions;

    public List<Action> getAction(){
        return listeActions;
    }


//    @Override
//    public void ajouter(Gestionnaire gestionnaire) {
//
//    }

    @Override
    public void supprimer(Gestionnaire gestionnaire, int index) {
        gestionnaire.getListeActions().remove(index);

         // index ?
    }

    @Override
    public void modifier(Gestionnaire gestionnaire, int index) {

    }
}
