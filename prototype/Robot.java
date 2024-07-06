import java.util.*;

public class Robot {


    // utiliser constructeur avec les données comme paramètre à chaque fois?
    private int numeroDeSerie;
    private String nom;
    private String type;
    private boolean disponibilite;
    private Object[] etats = new Object[4]; // Tableau pour stocker les états



    public void afficherInformations() {
        System.out.println("Numéro de série : " + numeroDeSerie);
        System.out.println("Nom : " + nom);
        System.out.println("Type : " + type);
        System.out.println("Disponibilité : " + (disponibilite ? "Disponible" : "Non disponible"));
        System.out.println("États : ");
        getEtats();
    }


    public Object[] getEtats(){
        for (int i=0;i<etats.length; i++){
            System.out.println(getEtats()[i]); // imprimer les elements du tableau
        }
        return this.etats;
    }

    private List<Composantes> composantesRobot = new ArrayList<>() ;

    public Composantes getElemComposantesRobot(int i){
        return this.composantesRobot.get(i);  // retourne un element recherche de la liste
    }

    public List<Composantes> getListCompoRobot(){
        return this.composantesRobot;
    }

//    public void ParcourirCompo(List<Composantes> composantesRobot, Composantes composante){
//        this.composantesRobot = composantesRobot;
//
//    }

    public void setNumeroDeSerie(int numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;  //  celui fournit depuis le CPU
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override

    public String toString() {
        return "Robot{" +
                "nom='" + nom  + ", type='" + type + ", numeroDeSerie=" + numeroDeSerie +
                ", composantesRobot=" + composantesRobot + '}';
    }

}

