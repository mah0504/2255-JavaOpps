import java.util.*;

// petit PSA à audrey surtt: les commentaires sont temporaires pls don't yap abt
//  how unprofessional they are gracias ;-)  #météo

// parallele ou sequentielle ? preciser
// prorio de transition


public class Tache {


    private String nom;
    private ArrayList<Tache> listeTaches= new ArrayList<>();  // liste de tâches déjà créées

    public Tache() {

    }



    public ArrayList<Tache> getListeTaches(){
        return listeTaches;
    }

    public void supprimer(Tache tache, int index) {
        getListeTaches().remove(index);  //enlever l'elem de la liste
    }

    public void modifier(Tache tache, int index) {
    }
}
