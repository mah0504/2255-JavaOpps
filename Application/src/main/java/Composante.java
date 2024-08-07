import java.util.UUID;

public class Composante {

    private String nom, description;
    private ComposanteType type;
    float prix;

    /**
     * Une nouvelle instance de la classe Composante
     * (initialisation par défaut)
     */
    public Composante(){
    }

    /**
     * Une nouvelle instance de la classe Composante
     *
     * @param nom le nom de la composante
     * @param description la description de la composante
     * @param type le type de la composante
     * @param prix le prix de la composante
     */
    public Composante(String nom, String description, ComposanteType type, float prix) {
        this.nom = nom;
        this.description = description;
        this.type = type;
        this.prix = prix;
    }

    /**
     * Récupère le nom de la composante
     *
     * @return nom le nom de la composante
     */
    public String getNom() {
        return nom;
    }

    /**
     * Assigne un nouveau à la composante
     *
     * @param nom le nouveau nom de la composante
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère la description de la composante
     *
     * @return description la description de la composante
     */
    public String getDescription() {
        return description;
    }

    /**
     * Assigne la nouvelle description à la Composante
     *
     * @param description la nouvelle description de la composante
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Récupère le Type de Composante
     *
     * @return type Le type de la composante
     */
    public ComposanteType getType() {
        return type;
    }

    /**
     * Assigne le nouveau type à la Composante
     *
     * @param type le nouveau type de la composante
     */
    public void setType(ComposanteType type) {
        this.type = type;
    }

    /**
     * Récupère le prix de la Composante
     *
     * @return prix le prix de la composante
     */
    public float getPrix() {
        return prix;
    }

    /**
     * Assigne un nouveau prix à la composante
     *
     * @param prix le nouveau de la composante
     */
    public void setPrix(float prix) { this.prix = prix;}

    /**
     * Méthode toString() pour afficher un objet Composante
     *
     * @return nom + " : { typeComposante :  " + type + " , prix : " + prix + " }"
     */
    @Override
    public String toString(){
        return nom + " : { typeComposante :  " + type + " , prix : " + prix + " }";
    }

}
