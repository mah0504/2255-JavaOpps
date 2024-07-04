import java.util.*;


// petit PSA à audrey surtt: les commentaires sont temporaires pls don't yap abt
//  how unprofessional they are gracias ;-(


// parallele ou sequentielle ? preciser
// prorio de transition
public class Tache {
    private String nom;

    private ArrayList<Tache> listeTaches;  // liste de tâches déjà créées

    private ArrayList<Action> singTask; // "singular" vu qu'1 tâche est un ensemble d'actions


    public void modifier(Tache tache){
        // remove / add action on appelle action ig?
    }

    public void  creer(Tache tache){

        // à détailler
    }


    public void supprimer(Tache tache){
        listeTaches.remove(tache);  // enlever la tachee selectionnee de la ArrayList

    }

}
