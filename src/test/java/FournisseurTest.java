import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class FournisseurTest {

    private Fournisseur fournisseur;
    private Composante composante;
    private FournisseurComposante fournisseurComposante;

    @BeforeEach
    public void setUp() {
        // Initialisation des objets pour chaque test
        fournisseur = new Fournisseur("pseudo", "email@example.com", "password", "1234567890", "Compagnie Test");
        composante = new Composante();
        composante.setNom("Composante A");
        composante.setDescription("Description de Composante A");
        composante.setType(ComposanteType.CPU);
        composante.setPrix(100.0f);
        fournisseurComposante = new FournisseurComposante(composante, 10);
    }

    @Test
    public void testAddComposante() {
        // Ajouter une composante
        fournisseur.addComposante(fournisseurComposante);

        // Vérifier si la composante a été ajoutée
        HashMap<String, FournisseurComposante> composantes = fournisseur.getComposantes();
        assertTrue(composantes.containsKey("Composante A"), "La composante devrait être présente dans la liste.");

        // Vérifier les détails de la composante ajoutée

    }
}
