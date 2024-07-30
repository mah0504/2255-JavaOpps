//import java.util.UUID;

public class Compte {

    private String id, pseudo, email, mdp, telephone;
    private boolean confirmerInscription;
    private boolean isConfirmed;
    private boolean confirmationLien, confirmationLienExpiration;
    private String DateConnexion;

    /**
     * Une nouvelle instance de la classe Compte (initialisation par défaut)
     */
    public Compte(){
        //setId();
    }

    /**
     * Une nouvelle instance de la classe Compte
     *
     * @param pseudo le pseudo du membre
     * @param email l'email du membre
     * @param mdp le mot de passe du membre
     * @param telephone le telephone du membre
     */
    public Compte(String pseudo, String email, String mdp, String telephone){
        //setId();
        this.pseudo = pseudo;
        this.email = email;
        this.mdp = mdp;
        this.telephone = telephone;
    }

    /**
     * Récupère l'id d'un Membre
     *
     * @return l'id du membre (utilisateur ou fournisseur)
     */
    public String getId(){
        return id;
    }

    /**
     *  Assigne un id unique au membre
     *
     * @param id Le nouvel id du membre
     */
    public void setId(String id){
        this.id = id;
        //this.id = UUID.randomUUID().toString();
    }

    /**
     * Récupère le pseudo d'un Membre
     *
     * @return le pseudo du membre (utilisateur ou fournisseur)
     */
    public String getPseudo(){
        return pseudo;
    }

    /**
     *  Assigne un pseudo au membre
     *
     * @param pseudo Le nouveau pseudo du Membre
     */
    public void setPseudo(String pseudo){
        this.pseudo = pseudo;
    }

    /**
     * Récupère l'email d'un Membre
     *
     * @return l'email du membre (utilisateur ou fournisseur)
     */
    public String getEmail(){
        return email;
    }

    /**
     *  Assigne un email au membre
     *
     * @param email Le nouvel email du Membre
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Récupère le mot de passe d'un Membre
     *
     * @return le mdp du membre (utilisateur ou fournisseur)
     */
    public String getMdp(){
        return mdp;
    }

    /**
     *  Assigne un mdp au membre
     *
     * @param mdp Le nouveau mdp du Membre
     */
    public void setMdp(String mdp){
        this.mdp = mdp;
    }

    /**
     * Récupère le telephone d'un Membre
     *
     * @return le telephone du membre (utilisateur ou fournisseur)
     */
    public String getTelephone(){
        return telephone;
    }

    /**
     *  Assigne un telephone au membre
     *
     * @param telephone Le telephone du Membre
     */
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    /**
     * Vérifie si l'inscription ou la connexion est réussie
     *
     * @return {@code  true} si elle est réussie, sinon {@code false}
     */
    public boolean confirmer(){
        return isConfirmed;
    }

    /**
     * Véridie si la désactivation du compte est réussie
     *
     * @return {@code  true} si elle est réussie, sinon {@code false}
     */
    public boolean desactiverCompte(){
        return isConfirmed;
    }

}
