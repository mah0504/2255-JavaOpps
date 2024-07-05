import java.util.*;

public class Flotte {
    private List<Robot> listeRobots =new ArrayList<>();
    private List<Composantes> listeComposantes =new ArrayList<>();
    private int nomRobot; // id, numero de serie
    private boolean disponibilite;

    public Flotte() {
        this.listeRobots = new ArrayList<>();
        this.listeComposantes = new ArrayList<>();
    }

    public List<Robot> getListeRobots() {
        return listeRobots;
    }

    public List<Composantes> getListeComposantes() {
        return listeComposantes;
    }

    public int getNumeroDeSerie(Robot robot) {
        return nomRobot;
        // definir une arraylist / hashmap pr les robots aussi ?
    }

    public boolean estIlDispo() {
        return disponibilite;
    }


    public void setListeRobots(Robot robot){
        listeRobots.add(robot);
    }




    public void acheterComposante(Composantes composante) {
        if (verifierComposante(composante)) {
            composante.incrementInventaire();
        } else {
            listeComposantes.add(composante);
            composante.setInventaire(1); // Réinitialisation de l'inventaire
        }
    }


    public boolean verifierComposante(Composantes composante) {
        for (Composantes c : getListeComposantes()) {
            if (c.getClass() == composante.getClass()) {
                return true; // Retourne true dès qu'une composante similaire est trouvée
            }
        }
        return false; // Retourne false si aucune composante similaire n'est trouvée
    }


    // robot déjà créé
    public void assignerComposante(Robot robot, Composantes composante) {
        for (Composantes c : robot.getListCompoRobot()) {
            if (composante.getClass()== c.getClass() && verifierComposante(composante)){  // si le robot a déjà une composante du même type
                c.incrementInventaire();
                composante.setInventaire(composante.getInventaire() -1 );
                return;

            }  else if (!(composante.getClass()== c.getClass()) && verifierComposante(composante) ){

               robot.getListCompoRobot().add(composante); // on ajoute la composante à la liste des composantes du robot
                composante.setInventaire(composante.getInventaire() -1 ); // on décrémente la composante dans notre flotte
                return;

                // le robot n'a pas de composante de ce type mais l'a dans sa flotte donc il peut l'assigner
                // on ajoute la nouvelle composante a la liste des composantes du robot
                // on decremente de la liste des composantes de la flotte
            } else{
                System.out.println("La composante choisie n'est pas disponible veuillez l'acheter!");
                // la composante est introuvable dans la flotte
            }
        }
    }

    public void enregistrerRobot(){

    }


}