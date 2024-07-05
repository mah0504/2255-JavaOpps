import java.util.*;

public class Flotte {
    private List<Robot> listeRobots;
    private List<Composantes> listeComposantes;

    private int nbrRobots;
    private int nomRobot; // id, numero de serie
    private boolean disponibilite;


    public int getNumeroDeSerie(Robot robot) {
        return nomRobot;
        // definir une arraylist / hashmap pr les robots aussi ?
    }

    public boolean estIlDispo() {
        return disponibilite;
    }

    public void acheterComposante(Composantes composante) {
        boolean contientMemeType = false;

        for (Composantes c : listeComposantes) {
            if (composante.getClass() == c.getClass()) {
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

    // si la composante se trouve dans la liste de composantes de la flotte on l'attribue à un robot
    // et on décrémente le stock dans la liste
    // si le robot a déjà une composante du même type on l'incrémente et on la décrémente de la liste de stock
    // des composantes non associées aux robots
    // si le robot n'a pas de composante de même type alors on la rajoute et décrémente de la liste des compo ...
    // choisir la composante depuis listeComposantes

    public void assignerComposante(Robot robot, Composantes composante) {
        // traiter le cas particulier si la composante ajoutée est un CPU ?

        boolean trouve = false;

        for (Composantes c : robot.getComposantesRobot()) {
            if (c.getClass() == composante.getClass()) {
                trouve = true;
                if (composante.getInventaire() > 0) {
                    c.incrementInventaire(); // Incrémenter l'inventaire du robot sélectionné
                    composante.setInventaire(composante.getInventaire() - 1); // Décrémenter de la liste globale de composantes
                    // indépendantes
                } else {
                    System.out.println("La composante " + composante.getClass().getSimpleName() + " est introuvable en " +
                            "inventaire ! Veuillez l'acheter ! \n");
                }
                break; // Sortir de la boucle si une correspondance est trouvée
            }
        }

        if (!trouve) {
            if (composante.getInventaire() > 0) {
                robot.getComposantesRobot().add(composante); // rajouter dans la liste des composantes du robot
                composante.setInventaire(composante.getInventaire() - 1);
                // Décrémenter de la liste des composantes distinctes

            } else {
                System.out.println("La composante " + composante.getClass().getSimpleName() + " est introuvable en" +
                        " inventaire ! Veuillez l'acheter ! \n");
            }
        }

    }

    public void ajouterRobot (Robot robot){
        // instancier robot dans main puis l'ajouter ici?


        // avant d'ajouter le robot faut appeler assigner une composante avec au moins CPU et une autre
        // composante, inclu verifier l'inventaire etc

        listeRobots.add(robot);
        // lui associer son CPU ...


    }
}