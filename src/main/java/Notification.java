public class Notification {

    private String id, message;
    private String DateCreation;
    private boolean lue;

    /**
     * Une nouvelle instance de la classe Notification
     * (initialisée par défaut)
     */
    public Notification() {}

    /**
     * Une nouvelle instance de la classe Notification
     *
     * @param id l'identifiant d'une notification
     * @param message le message d'une notification
     * @param DateCreation la date de Création d'une notification
     * @param lue vérifie si le message est lu
     */
    public Notification(String id, String message, String DateCreation, boolean lue) {
        this.id = id;
        this.message = message;
        this.DateCreation = DateCreation;
        this.lue = lue;
    }

    /**
     * Récupère l'identifiant de la notification
     *
     * @return id l'identifiant de la notification
     */
    public String getId() {
        return id;
    }

    /**
     * Assigne l'identifiant de la notification
     *
     * @param id le nouveau id de la notification
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Récupère le message de la notification
     *
     * @return message le message de la notification
     */
    public String getMessage() {
        return message;
    }

    /**
     * Assigne le nouveau message de la notification
     *
     * @param message le nouveau message de la notification
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Récupère la Date de Création de la notitfication
     *
     * @return DateCreation la DateCreation de la notification
     */
    public String getDateCreation() {
        return DateCreation;
    }

    /**
     * Assigne la nouvelle Date de Création de la notification
     *
     * @param DateCreation la nouvelle Date de Création de la notification
     */
    public void setDateCreation(String DateCreation) {
        this.DateCreation = DateCreation;
    }

    /**
     * Vérifie si la notification est lue
     *
     * @return {@code  true} si elle est lue, sinon {@code false}
     */
    public boolean isLue() {
        return lue;
    }

}
