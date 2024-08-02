import java.util.ArrayList;

public class ControllerRobot {
    private Robot robot;


    /**
     * Affiche les informations générales du robot, son nom type et niveay de batterie
     * @param robot
     */
    public void afficherVueGenerale(Robot robot) {
        System.out.println("Nom:" + robot.getName() +"\n" +"Type" +robot.getType() + "\n"
                +"Niveau de batterie:" + robot.getBatteryLevel());

    }


    /**
     * Affiche les détails complets pour un robot spécifique, en plus des générales on a accès aussi
     * au numéro de série, vitesse, capacité CPU , position, et mémoire
     * @param robot
     */
    public void afficherVueComplete(Robot robot) {
        System.out.println("Nom: " + robot.getName() + "\n" + "Type: " + robot.getType() + "\n" +
                "Niveau de batterie: " + robot.getBatteryLevel() + "\n" +
                "Numéro de série: " + robot.getId() + "\n" + "Position: " + robot.getPosition() + "\n" +
                "Vitesse: " + robot.getSpeed() + "\n" + "Consommation CPU: " + robot.getCpu() + "\n" +
                "Mémoire: " + robot.getMemory());
    }

}



