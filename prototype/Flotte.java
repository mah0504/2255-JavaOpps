import java.util.*;

public class Flotte {

    private List<Robot> listeRobots =new ArrayList<>();
    private List<Composantes> listeComposantes = new ArrayList<>();
    private int nomRobot; // id, numero de serie
    private boolean disponibilite;


    // bsn pour assigner les composantes, les taches aussi plus tars et activites
    public Robot choisirRobot( ) {
        Scanner scanner = new Scanner(System.in);
        int choix = -1;

        while (true) {
            for (int i = 0; i < listeRobots.size(); i++) {
                System.out.println("[" + i + "]" + ": " + listeRobots.get(i).toString());
            }

            try {
                System.out.println("Veuillez choisir le robot selon son index : ");
                choix = scanner.nextInt();
                if (choix >= 0 && choix < listeRobots.size()) {
                    return listeRobots.get(choix);
                } else {
                    System.out.println("Veuillez effectuer un choix valide !");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide, veuillez entrer un numéro valide.");
                scanner.next(); // Clear the invalid input
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

// getters and setters
    public List<Robot> getListeRobots() {
        return listeRobots;
    }

    public List< Composantes> getListeComposantes() {
        return listeComposantes;
    }

    public int getNumeroDeSerie(Robot robot) {return nomRobot;}

    public void setListeRobots(Robot robot){
        listeRobots.add(robot);
    }

    public void setListeComposantes( List<Composantes> listeComposantes){
        this.listeComposantes=listeComposantes; };

    public  <T extends Composantes> void acheterComposante(T composante) {

        if (verifierComposante(composante)) {
            composante.incrementInventaire();
        } else {
            listeComposantes.add(composante);
            composante.setInventaire(1); // Réinitialisation de l'inventaire
        }
    }


    public  <T extends Composantes> boolean verifierComposante(T composante) {
        for (Composantes c :listeComposantes) {
            if (c.getClass() == composante.getClass() && composante.getInventaire()>0 ){
                return true; // Retourne true dès qu'une composante similaire est trouvée
            }
        }
        return false; // Retourne false si aucune composante similaire n'est trouvée
    }


    public  void assignerComposante(Robot robot, Composantes composante) {



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




    public  boolean verifAutreCompo() {
        for (Composantes c : listeComposantes) {
            if (c != null && !(c instanceof CPU) && c.getInventaire() > 0) {
                return true;
            }
        }
        return false;
    }

    public void afficherListeCompo(){
        System.out.println("Liste des composantes disponibles : ");
        for (Composantes c : this.listeComposantes) {
            System.out.println(c.getClass().toString() + " - Inventaire : " + c.getInventaire());
        }
    }


    public void enregistrerRobot() {
            // voir le diagramme
            Robot robot = new Robot(); // check si bsn d'acceder a la liste de fournisseurs etc
            CPU cpu = new CPU();

            boolean cpuDispo = verifierComposante(cpu); // Checker si on a un cpu en inventaire
            boolean autreCompoDispo = verifAutreCompo();

            if (!cpuDispo || !autreCompoDispo) {
                System.out.println("Pas de composantes disponibles dans" +
                        "la flotte  pour ajouter le robot! Veuillez en acheter !");

            } else {
                robot.getListCompoRobot().add(cpu);  // on ajoute le CPU à la liste
                cpu.getNumDeSerie(); // on a le num de serie on doit l'assigner au robot ?


                afficherListeCompo();
            Scanner scanner =new Scanner(System.in);
           System.out.println("Veuillez choisir le nombre de composantes à ajouter ");

           int choix = scanner.nextInt(); // exple 2 mains on rajoute la composante 2 fois ?

           if ( choix>= listeComposantes.get(choix).getInventaire()) {
                for (int i = 0; i < choix; i++) {
                    System.out.println("Veuillez choisir la composante à ajouter ");

                    afficherListeCompo();
                    Scanner scann = new Scanner(System.in);
                    int choix1= scann.nextInt();

                    Composantes nvlCompo =listeComposantes.get(choix1); // la composante à ajouter
                    robot.getListCompoRobot().add(nvlCompo);

                    comfirmer(); //apres check du stockage et que valid on comfirme avec l'utilisateur

                    if (comfirmer()){
                        // on retire les elements de la liste des composantes et tt est bien qui finit bien
                        for (int k = 0; k < choix; k++) {
                            nvlCompo.setInventaire(nvlCompo.getInventaire() - 1);
                        }
                        cpu.setInventaire(cpu.getInventaire() - 1); // reverifier cette ligne !!!!

                        listeRobots.add(robot); // on ajoute finalement le robot
                        robot.setNumeroDeSerie(cpu.getNumDeSerie());


                    } else {
                        // de base on retire le robot de la listerobot et les composantes nn?
                       robot.getListCompoRobot().remove(cpu);
                       robot.getListCompoRobot().remove(nvlCompo); // on les retire de la lst de

                    }
                }

            } else {
                System.out.println("Nombre de composantes dans la flotte insuffisant , veuillez en acheter!");
           }

            }

        }
}