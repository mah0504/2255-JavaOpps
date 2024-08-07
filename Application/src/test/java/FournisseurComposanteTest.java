import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FournisseurComposanteTest {

    @Test
    public void testIsAvailableWithPositiveQuantity() {

        Composante composante = new Composante(); //instancier la classe

        FournisseurComposante fournisseurComposante = new FournisseurComposante(composante, 10);

        // doit retourner true
        assertTrue(fournisseurComposante.isAvailable(), "La composante devrait être disponible avec une quantité positive.");
    }

    @Test
    public void testIsAvailableWithZeroQuantity() {

        Composante composante = new Composante();
        FournisseurComposante fournisseurComposante = new FournisseurComposante(composante, 0);

        // doit retourner false
        assertFalse(fournisseurComposante.isAvailable(), "La composante ne devrait pas être disponible avec une quantité de zéro.");
    }

    @Test
    public void testIsAvailableWithNegativeQuantity() {
        Composante composante = new Composante();
        FournisseurComposante fournisseurComposante = new FournisseurComposante(composante, -5);

        // doit retourner false
        assertFalse(fournisseurComposante.isAvailable(), "La composante ne devrait pas être disponible avec une quantité négative.");
    }
}
