import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class InscriptionTest {

    @Test
    public void inscriptionTest_unique(){
        ArrayList<Utilisateur> comptes = new ArrayList<>();
        UtilisateurService utilisateurService = new UtilisateurService();
        utilisateurService.setUtilisateurs(comptes);

        Utilisateur user = new Utilisateur("pseudo","pseudo@example.com","motdepasse","1234567890","prenom","nom");

        utilisateurService.sInscrire(user);

        assertFalse(utilisateurService.getUtilisateurs().isEmpty());
        Utilisateur user_test = comptes.get(0);
        assertEquals("pseudo", user_test.getPseudo());
        assertEquals("pseudo@example.com", user_test.getEmail());
        assertEquals("motdepasse", user_test.getMdp());
        assertEquals("1234567890", user_test.getTelephone());
        assertEquals("prenom", user_test.getPrenom());
        assertEquals("nom", user_test.getNom());

    }

    @Test
    public void inscriptionTest_nonUnique(){
        ArrayList<Utilisateur> comptes = new ArrayList<>();
        UtilisateurService utilisateurService = new UtilisateurService();
        utilisateurService.setUtilisateurs(comptes);

        Utilisateur user1 = new Utilisateur("pseudo","pseudo1@example.com","motdepasse1","1234567890","prenom1","nom1");
        utilisateurService.sInscrire(user1);

        Utilisateur user2 = new Utilisateur("pseudo","pseudo2@example.com","motdepasse1","1234567890","prenom2","nom2");
        utilisateurService.sInscrire(user2);

        assertEquals(1, utilisateurService.getUtilisateurs().size());

    }

    @Test
    public void inscriptionTest_mdpInvalide(){

        ArrayList<Utilisateur> comptes = new ArrayList<>();
        UtilisateurService utilisateurService = new UtilisateurService();
        utilisateurService.setUtilisateurs(comptes);

        Utilisateur user = new Utilisateur("pseudo","pseudo@example.com","mdp","1234567890","prenom","nom");
        utilisateurService.setMdpValide(false);

        utilisateurService.sInscrire(user);

        assertTrue(utilisateurService.getUtilisateurs().isEmpty());
    }
}
