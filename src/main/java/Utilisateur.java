import java.util.ArrayList;

public class Utilisateur extends Compte {

    private String prenom, nom;
    private float points;
    private ArrayList<Utilisateur> followers; //liste des Suiveurs
    private ArrayList<Utilisateur> following; //liste des Suivis


    /**
     * Une nouvelle instance de la classe Utilisateur (initialisation par défaut)
     */
    public Utilisateur(){
    }

    /**
     * Une nouvelle instance de la classe Utilisateur
     *
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
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
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
     * Récupère la liste des Suiveurs
     *
     * @return followers la liste des Suiveurs
     */
    public ArrayList<Utilisateur> getFollowers(){
        return followers;
    }

    /**
     *  Assigne une liste de suiveurs à l'utilisateur
     *
     * @param followers La nouvelle liste de Suiveurs de l'utilisateur
     */
    public void setFollowers(ArrayList<Utilisateur> followers){
        this.followers = followers;
    }

    /**
     * Récupère la liste des Suivis
     *
     * @return following la liste des Suivis
     */
    public ArrayList<Utilisateur> getFollowing(){
        return following;
    }

    /**
     *  Assigne une liste de suivis à l'utilisateur
     *
     * @param following La nouvelle liste de Suivis de l'utilisateur
     */
    public void setFollowing(ArrayList<Utilisateur> following){
        this.following = following;
    }

    /**
     * Permet d'ajouter des Followers à la liste des Followers
     *
     * @param follower Un Utilisateur qui suit this Utilisateur
     */
    public void addFollower(Utilisateur follower){
        this.followers.add(follower);
    }

    /**
     * Permet d'ajouter des Utilisateurs à la liste des Following
     *
     * @param suivi Un Utilisateur que suit this Utilisateur
     */
    public void addFollowing(Utilisateur suivi){
        this.following.add(suivi);
    }

    /**
     * Permet d'update les points d'un utilisateur
     *
     * @param nvPoints Les points que vient de gagner l'Utilisateur
     */
    public void updatePoints(float nvPoints){
        this.points += nvPoints;
    }

}

