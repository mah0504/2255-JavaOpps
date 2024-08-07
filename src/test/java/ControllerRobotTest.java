import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerRobotTest {

    private ControllerRobot controller;
    private Robot robot;

    @BeforeEach
    public void setUp() {
        controller = new ControllerRobot();
        robot = new Robot("12345", "Type1", "robotWW", "Départ", 10.0f, 23.0f);
        robot.setCpu(2.5f);
        robot.setMemory(8.0f);
        controller.setRobot(robot);
    }


    @Test
    public void testAfficherVueComplete() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        controller.afficherVueComplete(robot);

        System.setOut(originalOut);


        String output = outputStream.toString().trim();
        System.out.println("Sortie réelle :\n" + output);

        String expectedOutput = "Nom: robotWW\nType: Type1\nNiveau de batterie: 23.0\nNuméro de série: 12345\nPosition: Départ\nVitesse: 10.0\nConsommation CPU: 2.5\nMémoire: 8.0";
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testRobotNotNull() {
        assertNotNull(robot);
    }


}