import java.util.*;

// petit PSA à audrey surtt: les commentaires sont temporaires pls don't yap abt
//  how unprofessional they are gracias ;-)  #météo

// parallele ou sequentielle ? preciser
// prorio de transition

public class Tache extends Gestionnaire{
    private String nom;


//    @Override
//    public void ajouter(Gestionnaire gestionnaire) {
//        // à détailler
//        gestionnaire.getListeTaches().add();
//
//    }

    @Override
    public void supprimer(Gestionnaire gestionnaire, int index) {
        gestionnaire.getListeTaches().remove(index);  //enlever l'elem de la liste
    }

    @Override
    public void modifier(Gestionnaire gestionnaire, int index) {

    }

}
