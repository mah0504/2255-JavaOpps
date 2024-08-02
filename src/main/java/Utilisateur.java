import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utilisateur extends Compte {

    private String prenom, nom;
    private float points;
    private Map<String, StatutActivite> activites;
    private ArrayList<Robot> listeRobots ;
    private ArrayList<Composante> composantesFlotte;
    /**
     * Une nouvelle instance de la classe Utilisateur (initialisation par défaut)
     */
    public Utilisateur(){
    }


    /**
     * Une nouvelle instance de la classe Utilisateur
     *
     * @param pseudo le pseudo de l'utilisateur
     * @param email l'email de l'utilisateur
     * @param mdp le mot de passe de l'utilisateur
     * @param telephone le telephone de l'utilisateur
     * @param prenom le prenom de l'utilisateur
     * @param nom le nom de l'utilisateur
     * @param points les points d'un utilisateur
     *
     */
    public Utilisateur(String pseudo, String email, String mdp, String telephone, String prenom, String nom, float points){
        super(pseudo, email, mdp, telephone);
        this.prenom = prenom;
        this.nom = nom;
        this.points = points;
        this.activites = new HashMap<>();
    }

    @Override
    public String toString(){
        return " Utilisateur = [ " + getPseudo() + " , " + points +
                " , Activites : " + activites + " ]";
    }
    /**
     * Récupère le prenom d'un Utilisateur
     *
     * @return prenom le prenom de l'utilisateur
     */
    public String getPrenom(){
        return prenom;
    }


    public ArrayList<Composante> getComposantesFlotte(){ return composantesFlotte;}

    public ArrayList<Robot> getListeRobots(){return listeRobots;}

    /**
     *  Assigne un prenom à l'utilisateur
     *
     * @param prenom Le nouveau prenom de l'utilisateur
     */
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    /**
     * Récupère le nom d'un Utilisateur
     *
     * @return nom le nom de l'utilisateur
     */
    public String getNom(){
        return nom;
    }

    /**
     *  Assigne un nom à l'utilisateur
     *
     * @param nom Le nouveau nom de l'utilisateur
     */
    public void setNom(String nom){
        this.nom = nom;
    }

    /**
     * Récupère les points d'un Utilisateur
     *
     * @return points les points gagnés l'utilisateur
     */


    /**
     * Récupère la liste des robots du fournisseur
     * @return Liste des Robots de l'utilisateur
     */



    public float getPoints(){
        return points;
    }

    /**
     *  Assigne des points à l'utilisateur
     *
     * @param points Les nouveaux points de l'utilisateur
     */
    public void setPoints(float points){
        this.points = points;
    }

    /**
     * Permet d'update les points d'un utilisateur
     *
     * @param nvPoints Les points que vient de gagner l'Utilisateur
     */
    public void updatePoints(float nvPoints){
        this.points += nvPoints;
    }


    @Override
    public String getPath() {
        return "src/main/resources/META-INF/utilisateurs.json";
    }


    /**
     * Récupère les activités auxquelles l'utilisateur est inscrit
     *
     * @return activites les activités de l'utilisateur
     */
    public Map<String, StatutActivite> getActivites(){
        return activites;
    }

    /**
     * Permet d'ajouter une activité aux activités que suit l'utilisateur
     *
     * @param nomActivite le nom de l'activite
     * @param statut le statut de l'activité
     */
    public void ajouterActivite(String nomActivite, StatutActivite statut){
        this.activites.put(nomActivite, statut);
    }

    /**
     * Permet de supprimer une activité des activités que suit l'utilisateur
     *
     * @param nomActivite le nom de l'activite
     */
    public void supprimerActivite(String nomActivite){
        this.activites.remove(nomActivite);
    }
}

