import java.util.ArrayList;

public class Fournisseur extends Compte{

    private String nomCompagnie;

    /**
     * Une nouvelle instance de la classe Fournisseur (initialisation par défaut)
     */
    public Fournisseur(){
    }

    /**
     * Une nouvelle instance de la classe Fournisseur
     *
     * @param nomCompagnie le nom de la Compagnie du Fournisseur
     */
    public Fournisseur(String nomCompagnie){
        this.nomCompagnie = nomCompagnie;
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
}
