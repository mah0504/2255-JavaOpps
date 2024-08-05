public class FournisseurComposante {

    private Composante composante;
    private int quantite;

    /**
     * Une nouvelle instance de la classe FournisseurComposante
     * (initialisation par défaut)
     */
    public FournisseurComposante() {}

    /**
     * Une nouvelle instance de la classe FournisseurComposante
     * @param composante La composante que possède le fournisseur
     * @param quantite La quantité en unités de cette composante
     */
    public FournisseurComposante(Composante composante, int quantite) {
        this.composante = composante;
        this.quantite = quantite;
    }

    /**
     * Récupère la composante de chez le fournisseur
     *
     * @return composante La composante que fournit le Fournisseur
     */
    public Composante getComposante() {
        return composante;
    }


    /**
     * Assigne la composante à un fournisseur
     *
     * @param composante la composante à assigner
     */
    public void setComposante(Composante composante) {
        this.composante = composante;
    }

    /**
     * Récupère la quantité de la composante chez un fournisseur
     *
     * @return quantite La quantité en unités d'une composante
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Assigne une quantité en unités de la composante
     *
     * @param quantite la nouvelle quantité à assigner
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     * Vérifie si la quantité d'une composante est disponible
     *
     * @return {@code  true} si la composante est disponible, sinon {@code false}
     */
    public boolean isAvailable(){
        return quantite > 0;
    }

    /**
     * Augmente la quantité de l'inventaire d'une composante
     *
     * @param quantite la quantité à ajouter à l'inventaire
     */
    public void addQuantite(int quantite){
        this.quantite += quantite;
    }

    /**
     * Décrémente la quantité de l'inventaire d'une composante
     *
     * @param quantite la quantité à enlever de l'inventaire
     */
    public void subQuantite(int quantite){
        this.quantite -= quantite;
    }

}
