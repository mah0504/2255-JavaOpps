public class Interet {

    private String id, nom, description;

    /**
     * Une nouvelle instance de la classe Interet (initialisation par défaut)
     */
    public Interet() {}

    // pensez à incrémenter l'identifiant pour chaque nouvel intérêt
    /**
     * Une nouvelle instance de la classe Interet
     *
     * @param id l'identifiant de l'interet
     * @param nom le nom de l'interet
     * @param description la description de l'interet
     *
     */
    public Interet(String id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    /**
     * Récupère l'identifinat de l'intérêt
     *
     * @return id l'id de l'intérêt
     */
    public String getId() {
        return id;
    }

    /**
     * Assigne un nouvel id à l'intérêt
     *
     * @param id le nouvel id de l'intérêt
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Récupère le nom de l'intérêt
     *
     * @return nom le nom de l'intérêt
     */
    public String getNom() {
        return nom;
    }

    /**
     * Assigne un nouveau nom à l'intérêt
     * @param nom le nouveau nom de l'intérêt
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la descrpition de l'intérêt
     *
     * @return description La description de l'intérêt
     */
    public String getDescription() {
        return description;
    }

    /**
     * Assigne une nouvelle description à l'intérêt
     *
     * @param description la nouvelle description de l'intérêt
     */
    public void setDescription(String description) {
        this.description = description;
    }
}

