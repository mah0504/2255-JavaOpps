import java.util.*;

public class Flotte {
    private List<Robot> listeRobots;
    private List<Composantes> listeComposantes;

    private int nbrRobots; 
    private int nomRobot; // id, numero de serie
    private boolean disponibilite; 


    public int getNumeroDeSerie(Robot robot){
        return nomRobot; 
        // definir une arraylist / hashmap pr les robots aussi ?
    }
    
    public boolean estIlDispo(){
        return disponibilite;
    }

    public void acheterComposante(Composantes composante){
        boolean contientMemeType = false;

        for ( Composantes c : listeComposantes){
            if (composante.getClass() == c.getClass() ){
                contientMemeType = true;
                c.incrementInventaire();  // incrémenter le stock de cette composante
                break; // Exit the loop if a match is found
            }

            // Ajouter la composante à la liste
            if (!contientMemeType) {
                listeComposantes.add(composante);
                composante.setInventaire(composante.getInventaire() + 1);
                // changer en + aesthetic audrey
            }

        }
    }


}
