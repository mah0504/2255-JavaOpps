public class Activite {

    private String nom, description;

    /**
     * Une nouvelle instance de la classe Activite
     * (initialisée par défaut)
     */
    public Activite() {}

    /**
     * Une nouvelle instance de la classe Activite
     *
     * @param nom le nom de l'activite
     * @param description la description de l'activite
     */
    public Activite(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    /**
     * Récupère le nom de l'activite
     *
     * @return nom le nom de l'activite
     */
    public String getNom() {
        return nom;
    }

    /**
     * Assigne un nom à l'activite
     *
     * @param nom le nouveau nom de l'activite
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la description de l'activite
     *
     * @return description la description de l'activite
     */
    public String getDescription() {
        return description;
    }

    /**
     * Assigne une description l'activite
     *
     * @param description la nouvelle description de l'activite
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * la méthode toString() pour une afficher un objet Activite
     *
     * @return nom + " : " + description
     */
    @Override
    public String toString() {
        return nom + " : " + description;
    }
}
