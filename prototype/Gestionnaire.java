import java.util.ArrayList;

public abstract class Gestionnaire {


    private ArrayList<Tache> listeTaches;  // liste de tâches déjà créées

    private ArrayList<Action> listeActions ;


    public ArrayList<Action> getListeActions(){
        return listeActions;
    }


    public ArrayList<Tache> getListeTaches(){
        return listeTaches;

    }


    /*public abstract void ajouter(Gestionnaire gestionnaire);*/

    public abstract void supprimer(Gestionnaire gestionnaire, int index);

    public abstract void modifier(Gestionnaire gestionnaire, int index) ;

    // gestionnaire interface? ou classe mere de tache/ action/

}
