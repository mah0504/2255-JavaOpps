import org.junit.Test;
import static org.junit.Assert.*;

public class ComposanteTest {

    @Test
    public void testConstructor() {
        // Test du constructeur avec paramètres
        Composante c = new Composante("PreSonus PRM1", "micro blabla", ComposanteType.MICRO, 40.03f);
        assertEquals("PreSonus PRM1", c.getNom());
        assertEquals("micro blabla", c.getDescription());
        assertEquals(ComposanteType.MICRO, c.getType());
        assertEquals(40.03f, c.getPrix(), 0.01f);
    }

    @Test
    public void testSettersAndGetters() {
        // Création d'une composante pour tester les setters et getters
        Composante composante = new Composante();
        composante.setNom("CPUfireFRFR");
        composante.setDescription("Le CPU, véritable héros tragique, porte le poids du monde technologique sur " +
                "ses épaules sans relâche, accomplissant chaque cycle avec une précision quasi-mythique");
        composante.setType(ComposanteType.CPU);
        composante.setPrix(225);

        assertEquals("CPUfireFRFR", composante.getNom());
        assertEquals("Le CPU, véritable héros tragique, porte le poids du monde technologique " +
                "sur ses épaules sans relâche, accomplissant chaque cycle avec une précision quasi-mythique", composante.getDescription());
        assertEquals(ComposanteType.CPU, composante.getType());
        assertEquals(225, composante.getPrix(), 0.01f);
    }

    @Test
    public void testToString() {
        // Création d'une composante pour tester la méthode toString
        Composante composante = new Composante("NomTest", "DescriptionTest", ComposanteType.CPU, 100.0f);
        String expected = "NomTest : { typeComposante :  CPU , prix : 100.0 }";
        assertEquals(expected, composante.toString());
    }
}
