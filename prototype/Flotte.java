import java.util.*;

public class Flotte {

    // numero de serie comme cle, les info de chaque robot comme valeur dans le tableau? Peut etr
    // mettre Object au lieu de String ? pk generaliser
    HashMap<Integer, Object[]> flotte = new HashMap<>();

    private int nbrRobots; 
    private int nomRobot; // id, numero de serie
    private boolean disponibilite; 


    public Flotte() {
        this.nomRobot = -1; // Valeur pr 1er essai , 

        this.disponibilite = false; // Valeur par défaut
        this.nbrRobots = 0; // Valeur par défaut


        Object[] tableau= new Object[3];
        tableau[0]= nomRobot;
        tableau[1]= disponibilite;
        tableau[2]= nbrRobots; 
        //  j juste mis des trucs aleatoires pr que le tableau ne soit pas null mais changer
        // selon les info que le fournisseur a de chaque robot 

        flotte.put(nomRobot, tableau);

    }




    public int getNumeroDeSerie(){ 
        return nomRobot; 
        // definir une arraylist / hashmap pr les robots aussi ?
    }
    
    public boolean EstIlDispo(){
        return disponibilite;
    }

}
