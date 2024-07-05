public class BrouillonExec
{
    public static void main(String[] args) {
        Flotte flotte = new Flotte();

        // Initialisation des composantes avec leurs inventaires
        CPU cpu1 = new CPU("CPU", 2);
        flotte.getListeComposantes().add(cpu1);

        Roue roue1 = new Roue("roue",5);
        flotte.getListeComposantes().add(roue1);

        // Affichage des composantes disponibles
        System.out.println("Liste des composantes disponibles : ");
        for (Composantes c : flotte.getListeComposantes()) {
            System.out.println(c.getClass().getSimpleName() + " - Inventaire : " + c.getInventaire());
        }



        // Enregistrement d'un nouveau robot
        Robot robot = new Robot();
        flotte.enregistrerRobot(robot);
    }


}
