import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class ControllerUtilisateurTest {

    @Test
    public void testVoirNotifs() {
        // Création d'une instance de Notification
        Notification notif1 = new Notification("1", "Message 1", "2021-12-06", false);
        Notification notif2 = new Notification("2", "Message 2", "2024-08-07", true);

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNotifs(new ArrayList<>());
        utilisateur.getNotifis().add(notif1);
        utilisateur.getNotifis().add(notif2);

        MenuCompte menuCompte = new MenuCompte(null);
        View view = new View();
        ControllerUtilisateur controllerUtilisateur = new ControllerUtilisateur(menuCompte, view);

        // Redirection de la sortie standard
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        controllerUtilisateur.voirNotifs(utilisateur);

        String expectedOutput = notif1.toString() + "\n" + notif2.toString() + "\n";
        String actualOutput = outContent.toString();

        assertEquals(expectedOutput, actualOutput, "La sortie des notifications n'est pas correcte.");

        System.setOut(System.out);
    }
}
