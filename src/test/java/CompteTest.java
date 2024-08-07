import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CompteTest {

    public void envoyerMailConfirmation(Utilisateur utilisateur){

        //Initialiser le lien de confirmation
        String confirmationLien = UUID.randomUUID().toString();

        //Initialiser la Date d'expiration de ce lien
        String DateExpiration = utilisateur.getConfirmationLienExpirationDate();

        //Assigner les nouvelles valeurs
        utilisateur.setConfirmationLien(confirmationLien);
        utilisateur.setConfirmationLienExpirationDate(DateExpiration);

        //envoyer le mail de confirmation
        System.out.println("Email de confirmation envoyé à : " + utilisateur.getEmail());
        System.out.println("Lien de confirmation : " + confirmationLien);

    }

    @Test
    public void TestEnvoyerMailConfirmation(){
        Utilisateur user= new Utilisateur("pseudo","pseudo@example.com","motdepasse","1234567890","prenom","nom");

        envoyerMailConfirmation(user);
        assertNotNull(user.getConfirmationLien());

        String dateExpiration = user.getConfirmationLienExpirationDate();
        assertNotNull(dateExpiration);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime expirationDate = LocalDateTime.parse(dateExpiration, formatter);
        assertNotNull(expirationDate);

    }
}
