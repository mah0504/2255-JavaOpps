import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utilisateur extends Compte {

    private String prenom, nom;
    private float points;
    private Map<String, StatutActivite> activites;
    private ArrayList<Robot> listeRobots ;
    private Map<String, ComposanteType> composantes;
    private ArrayList<Notification> notifs;
    /**
     * Une nouvelle instance de la classe Utilisateur (initialisation par défaut)
     */
    public Utilisateur(){
        this.listeRobots = new ArrayList<>();
        this.notifs = new ArrayList<>();
        this.listeRobots=getListeRobots();

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
     *
     */
    public Utilisateur(String pseudo, String email, String mdp, String telephone, String prenom, String nom){
        super(pseudo, email, mdp, telephone);
        this.prenom = prenom;
        this.nom = nom;
        this.activites = new HashMap<>();
        this.listeRobots = new ArrayList<>();
        this.composantes = new HashMap<>();
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

    /**
     * Récupère un dictionnaire de Composantes que possède l'utilisateur
     *
     * @return composnates un dictionnaire de composantes
     */
    public Map<String, ComposanteType> getComposantesFlotte(){ return composantes;}

    /**
     * Récupère une liste de Robots que possède l'utilisateur
     *
     * @return listeRobots la liste des Robots
     */
    public ArrayList<Robot> getListeRobots(){return listeRobots;}

    /**
     * Assigne une liste à l'utilisateur
     *
     * @param listeRobots la nouvelle liste de Robots
     */
    public void setListeRobots(ArrayList<Robot> listeRobots) {
        this.listeRobots = listeRobots;
    }

    /**
     * Récupère les Notifications de L'utilisateur
     *
     * @return notifs une liste de notifications
     */
    public ArrayList<Notification> getNotifis(){
        return notifs;
    }

    /**
     * Assigne une nouvelle liste de notifications à l'utilisateur
     *
     * @param notifs la nouvelle Liste de notifications
     */
    public void setNotifs(ArrayList<Notification> notifs) {
        this.notifs = notifs;
    }



}
