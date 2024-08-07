public class Organisation {

    private int id;
    private String nom;

    /**
     * Une nouvelle instance de la classe Organisation (initialisation par défaut)
     */
    public Organisation() {

    }

    /**
     * Une nouvelle instance de la Classe Organisation
     *
     * @param id l'identifiant de l'organisation
     * @param nom le nom de l'organisation
     */
    public Organisation(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    /**
     * Récupère l'identifiant de l'organisation
     *
     * @return id L'id de l'organisation
     */
    public int getId() {
        return id;
    }

    /**
     * Assigne un identifiant à l'organisation
     *
     * @param id le nouvel id de l'organisation
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Récupère le nom de l'organisation
     *
     * @return nom Le nom de l'organisation
     */
    public String getNom() {
        return nom;
    }

    /**
     * Assigne un nouveau nom à l'organisation
     *
     * @param nom le nouveau nom de l'organisation
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

}
