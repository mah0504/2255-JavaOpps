import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotificationTest {

    private Notification notification;

    @BeforeEach
    public void setUp() {
        // Initialiser une notification non lue
        notification = new Notification("1", "Message 1", "2021-12-06", false);
    }

    @Test
    public void testNotificationIsNotRead() {
        // Vérifier que la notification n'est pas lue
        assertFalse(notification.isLue(), "La notification devrait être non lue.");
    }

    @Test
    public void testNotificationIsRead() {
        // Mettre à jour la notification pour qu'elle soit lue
        notification.setLue(true);

        // Vérifier que la notification est marquée comme lue
        assertTrue(notification.isLue(), "La notification devrait être lue.");
    }
}