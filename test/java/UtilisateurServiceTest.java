import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilisateurServiceTest {

    @Test
    public void testVerifierConnexion_allTrue(){
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur user_test1 = new Utilisateur("pseudo1","pseuo1@example.com","motdepasse1","1234567890","prenom1","nom1");
        user_test1.isConfirmed(true);
        utilisateurService.ajouterUtilisateur(user_test1);

        assertTrue(utilisateurService.verifierConnexion("pseuo1@example.com","motdepasse1"));

    }

    @Test
    public void testVerifierConnexion_MdpFalse(){
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur user_test2 = new Utilisateur("pseudo2","pseuo2@example.com","motdepasse2","1234567890","prenom2","nom2");
        user_test2.isConfirmed(true);
        utilisateurService.ajouterUtilisateur(user_test2);

        assertFalse(utilisateurService.verifierConnexion("pseuo2@example.com","motdepasse1"));
    }

    @Test
    public void testVerifierConnexion_notConfirmed(){
        UtilisateurService utilisateurService = new UtilisateurService();
        Utilisateur user_test3 = new Utilisateur("pseudo3","pseuo3@example.com","motdepasse3","1234567890","prenom3","nom3");
        user_test3.isConfirmed(false);
        utilisateurService.ajouterUtilisateur(user_test3);

        assertFalse(utilisateurService.verifierConnexion("pseuo3@example.com","motdepasse3"));
    }


}
