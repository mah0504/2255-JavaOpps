import java.util.*;

public class Flotte {
    private List<Robot> listeRobots =new ArrayList<>();
    private List<Composantes> listeComposantes =new ArrayList<>();
    private int nomRobot; // id, numero de serie
    private boolean disponibilite;


    // gerer sa flotte - menu de recherche pr invoquer chaque methode ig ?
    public Flotte() {
        this.listeRobots = new ArrayList<>(); // liste
        this.listeComposantes = new ArrayList<>(); // liste des composantes de  la flotte
    }


    // bsn pour assigner les composantes, les taches aussi plus tars et activites
    public Robot choisirRobot( ){

        System.out.println("Veuillez choisir quelle composante utiliser :");
        Scanner scanner =new Scanner(System.in);
        for (int i = 0; i < listeRobots.size(); i++) {
            System.out.println("[" + i +"]"+ ": " + listeRobots.get(i));
        }
        int choix= scanner.nextInt();

        if (choix >= 0 && choix < listeRobots.size()) {

            try {

                System.out.println( listeRobots.get(choix));
                return listeRobots.get(choix);

            } catch (Exception e) {

                e.printStackTrace();

            }

        } System.out.println("Veuillez effectuer un choix valide !");
        return null;

    }

    public void afficherChoix(Composantes composantes){
        Scanner scanner= new Scanner(System.in);
        System.out.println("Veuillez choisir quelle fonctionnalité effectuer dans votre flotte: " +
                "[1] : Acheter une composante \n [2] : Assigner une composante à un robot \n [3]" +
                " : Enregistrer un robot \n");
        int choix = scanner.nextInt();
        switch (choix){
            case 1:
                acheterComposante(composantes.choisirCompo());
                break;
            case 2:
                assignerComposante(choisirRobot(), composantes.choisirCompo());
                break;
            case 3:
                enregistrerRobot( new Robot(), composantes.choisirCompo() );
                break;
            default:
                System.out.println("Veuillez entrer un choix valide !");
                break;
        }
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


    // robot déjà créé ajouter une classe ou on selectionne le robot depuis notre Arrayliste
    // traiter les exceptions
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

// peut etre mettre la fonction ailleurs ?
    public boolean comfirmer(){
        boolean comfi;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Comfirmer ?");
        String choix = scanner.nextLine();

        switch (choix){
            case "oui":
                comfi= true ;
                break;
            case "non":
                comfi= false;
                break;
            default:
                comfi= false;
                System.out.println("Operation annulee !");
                break;
        }
        return comfi;

    }


    // peut etre simplifieee pour meilleur couplage ig ?
    public void enregistrerRobot(Robot robot, Composantes composante){
        // voir le diagramme

        CPU cpu = new CPU();
        verifierComposante(cpu); // Checker si on a un cpu en inventaire
        verifierComposante(composante);
        if ( !(verifierComposante(cpu) && verifierComposante(composante)) ){
            System.out.println("Pas de composantes disponibles dans" +
                    "la flotte  pour ajouter le robot! Veuillez en acheter !");
            return;
        }
        else {

            robot.getListCompoRobot().add(cpu);  // on ajoute le CPU à la liste
            cpu.getNumDeSerie(); // on a le num de serie on doit l'assigner au robot ?

            System.out.println("Liste des composantes disponibles : ");
            for (Composantes c : getListeComposantes()) {
                System.out.println(c.getClass().toString() + " - Inventaire : " + c.getInventaire());
            }

            Scanner scanner =new Scanner(System.in);
            System.out.println("Veuillez choisir le nombre de composantes à ajouter ");

            int choix = scanner.nextInt(); // exple 2 mains on rajoute la composante 2 fois ?

            if ( choix>= composante.getInventaire()) {
                for (int i = 0; i < choix; i++) {
                    robot.getListCompoRobot().add(composante);

                    comfirmer(); // apres check du stockage et que valid on comfirme avec l'utilisateur

                    if (comfirmer()){
                        // on retire les elements de la liste des composantes et tt est bien qui finit bien
                        for (int k = 0; k < choix; k++) {
                            composante.setInventaire(composante.getInventaire() - 1);
                        }
                        cpu.setInventaire(composante.getInventaire() - 1);
                        listeRobots.add(robot); // on ajoute finalement le robot
                        // on doit set son nom , numero de serie...

                    } else {
                        // de base on retire le robot de la listerobot et les composantes nn?
                        robot.getListCompoRobot().remove(cpu);
                        for (int j = 0; j < choix; j++) {
                            robot.getListCompoRobot().remove(composante); // on les retire de la lst de
                        }
                        return;
                    }
                }

            } else {
                System.out.println("Nombre de composantes dans la flotte insuffisant , veuillez en acheter!");
                return;
            }
        }
    }

}