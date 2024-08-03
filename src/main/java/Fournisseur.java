import java.util.ArrayList;
import java.util.HashMap;

public class Fournisseur extends Compte{

    private String nomCompagnie;
    private HashMap<String, FournisseurComposante> composantesFournisseurs;

    /**
     * Une nouvelle instance de la classe Fournisseur (initialisation par défaut)
     */
    public Fournisseur(){}

    /**
     * Une nouvelle instance de la classe Fournisseur
     *
     * @param nomCompagnie le nom de la Compagnie du Fournisseur
     */
    public Fournisseur(String pseudo, String email, String mdp, String telephone, String nomCompagnie){
        super(pseudo, email, mdp, telephone);
        this.nomCompagnie = nomCompagnie;
        this.composantesFournisseurs = new HashMap<>();
    }

    /**
     * Récupère le nom de la Compagnie
     *
     * @return nomCompagnie Le nom de la Compagnie du Fournisseur
     */
    public String getNomCompagnie(){
        return nomCompagnie;
    }

    /**
     *  Assigne un nom de Compagnie du Fournisseur
     *
     * @param nomCompagnie Le nouveau nom de la Compagnie
     */
    public void setNomCompagnie(String nomCompagnie){
        this.nomCompagnie = nomCompagnie;
    }

    /**
     * écupère la liste des composantes que fournit un Fournisseur
     *
     * @return composantes La liste des Composantes d'un Fournisseur
     */
    public HashMap<String, FournisseurComposante> getComposantes(){
        return composantesFournisseurs;
    }

    /**
     * Permet d'ajouter les informations sur une Composante à la liste des
     * Composantes d'un Fournisseur
     *
     * @param composante la Composante et sa quantité à ajouter
     */
    public void addComposante(FournisseurComposante composante){
        this.composantesFournisseurs.put(composante.getComposante().getNom(), composante);
    }
}
