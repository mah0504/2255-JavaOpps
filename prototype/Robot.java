import java.util.*;

public class Robot {

    private int numeroDeSerie;
    private String nom;
    private String type;

    private Etat etats = new Etat(); // Les etats du robot


    private List<Composantes> composantesRobot = new ArrayList<>();


    public String afficherInfoGenerales() {

        return "Nom : " + nom + "\n" +
                "Type : " + type + "\n" +
                "Niv de batterie : " + etats.getNivDeBatterie();
    }


    public String afficherInfoCompletes(){
        return "Nom : " + nom + "\n" +
                "Type : " + type + "\n" +
                "Niv de batterie : " + etats.getNivDeBatterie()+" Numéro de série : " + numeroDeSerie + "\n" +
                " Position : " + etats.getPosition() + "\n" +
                "Vitesse : " + etats.getVitesse() + "\n" +
                " Consommation mémoire : " + etats.getConsoMem() + "\n" +
                "Consommation CPU : " + etats.getConsoCPU() ;

    }





// getters et setters
    public String getNom(){ return this.nom; }
    public List<Composantes> getListCompoRobot(){return this.composantesRobot;}
    public Etat getEtats(){ return this.etats; }

    public void setNumeroDeSerie(int numeroDeSerie) {this.numeroDeSerie = numeroDeSerie;}

    public void setNom(String nom) {this.nom = nom;}

    public void setType(String type) {this.type = type;}


    public void setEtats(Etat etats) {this.etats = etats; }

}
