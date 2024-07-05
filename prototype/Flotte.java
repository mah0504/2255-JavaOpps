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


    // robot déjà créé
    public boolean assignerComposante(Robot robot, Composantes composante) {
        for (Composantes c : robot.getComposantesRobot()) {
            if (c.getClass() == composante.getClass()) {
                if (composante.getInventaire() > 0) {
                    c.incrementInventaire(); // Incrémenter l'inventaire du robot sélectionné
                    composante.setInventaire(composante.getInventaire() - 1); // Décrémenter de la liste globale de composantes
                    return true;
                } else {
                    System.out.println("La composante " + composante.getClass().getSimpleName() + " est introuvable en inventaire ! Veuillez l'acheter ! \n");
                    return false;
                }
            }
        }

        if (composante.getInventaire() > 0) {
            robot.getComposantesRobot().add(composante); // ajouter à la liste des composantes du robot
            composante.setInventaire(composante.getInventaire() - 1); // Décrémenter de la liste des composantes distinctes
            return true;
        } else {
            System.out.println("La composante " + composante.getClass().getSimpleName() + " est introuvable en inventaire ! Veuillez l'acheter ! \n");
            return false;
        }
    }


    public void enregistrerRobot(Robot robot) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Veuillez choisir quelles composantes ajouter : ");

        for (int i = 0; i < listeComposantes.size(); i++) {

            System.out.println(i + ": " + listeComposantes.get(i) ); // imprime la composante et son index

        }

        int choix = scan.nextInt(); // le choix entré par l'utilisateur
        Composantes composanteChoisie = listeComposantes.get(choix);

        // Assigner le CPU et vérifier la disponibilité
        boolean cpuAssigne = false;
        for (Composantes c : listeComposantes) {
            if (c instanceof CPU) {

                cpuAssigne = assignerComposante(robot, c);
                break;
            }
        }

        if (!cpuAssigne) {
            System.out.println("Aucun CPU disponible. Enregistrement du robot annulé.");
            return; // Annuler l'enregistrement si aucun CPU n'est disponible
        }

        // Assigner la composante choisie et vérifier la disponibilité
        if (!assignerComposante(robot, composanteChoisie)) {
            System.out.println("La composante choisie n'est pas disponible. Enregistrement du robot annulé.");
            return; // Annuler l'enregistrement si la composante choisie n'est pas disponible
        }

        // Ajouter le robot à la flotte
        listeRobots.add(robot);
        System.out.println(listeRobots.get(0));

        System.out.println("Entrez le type du robot: ");
        String type = scan.next();
        robot.setType(type);

        System.out.println("Entrez le nom du robot: ");
        String nom = scan.next();
        robot.setNom(nom);
        System.out.println(listeRobots.get(0));
        // setnumeroDeSerie du robot avec celui du CPU choisit



        System.out.println("Le robot a été enregistré avec succès.");

    }


}


