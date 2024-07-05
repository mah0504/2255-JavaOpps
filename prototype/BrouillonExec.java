public class BrouillonExec
{

    // executer des parties de code sans afficher le menu à chaque fois
    public static void main(String[] args) {
        Flotte flotte = new Flotte();

        // Initialisation des composantes avec leurs inventaires
        CPU cpu = new CPU("CPU", 2);
        flotte.getListeComposantes().add(cpu);

        Roue roue = new Roue("roue",5);
        flotte.getListeComposantes().add(roue);

        // Affichage des composantes disponibles
        System.out.println("Liste des composantes disponibles : ");
        for (Composantes c : flotte.getListeComposantes()) {
            System.out.println(c.getClass().toString() + " - Inventaire : " + c.getInventaire());

        }

        System.out.println("Les robots de la flotte:");
        for (Robot r: flotte.getListeRobots()){
            System.out.println(r);
        }

        // Enregistrement d'un nouveau robot
        Robot robot = new Robot();
        flotte.enregistrerRobot(robot);
    }


}
