public class Activite {

    private String nom, description;

    /**
     * Une nouvelle instance de la classe Activité
     * (initialisée par défaut)
     */
    public Activite() {}

    /**
     * Une nouvelle instance de la classe Activité
     *
     * @param nom le nom de l'activité
     * @param description la description de l'activité
     */
    public Activite(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    /**
     * Récupère le nom de l'activité
     *
     * @return nom le nom de l'activité
     */
    public String getNom() {
        return nom;
    }

    /**
     * Assigne un nom à l'activité
     *
     * @param nom le nouveau nom de l'activité
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la description de l'activité
     *
     * @return description la description de l'activité
     */
    public String getDescription() {
        return description;
    }

    /**
     * Assigne une description l'activité
     *
     * @param description la nouvelle description de l'activité
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Méthode toString() pour afficher un objet Activité
     *
     * @return nom + " : " + description
     */
    @Override
    public String toString() {
        return nom + " : " + description;
    }
}
