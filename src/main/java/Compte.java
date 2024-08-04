
public abstract class Compte {

    private String pseudo, email, mdp, telephone;
    private boolean confirmed;
    private String confirmationLien;
    private String confirmationLienExpirationDate;

    /**
     * Une nouvelle instance de la classe Compte (initialisation par défaut)
     */
    public Compte(){}

    /**
     * Une nouvelle instance de la classe Compte
     *
     * @param pseudo le pseudo du membre
     * @param email l'email du membre
     * @param mdp le mot de passe du membre
     * @param telephone le telephone du membre
     */
    public Compte(String pseudo, String email, String mdp, String telephone){
        this.pseudo = pseudo;
        this.email = email;
        this.mdp = mdp;
        this.telephone = telephone;
        this.confirmed = false;
        this.confirmationLien = null;
        this.confirmationLienExpirationDate = null;
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

    public boolean getConfirmed(){
        return confirmed;
    }

    public void isConfirmed(boolean confirmed){
        this.confirmed = confirmed;
    }

    public String getConfirmationLien(){
        return confirmationLien;
    }

    public void setConfirmationLien(String confirmationLien){
        this.confirmationLien = confirmationLien;
    }

    public String getConfirmationLienExpirationDate(){
        return confirmationLienExpirationDate;
    }

    public void setConfirmationLienExpirationDate(String confirmationLienExpirationDate){
        this.confirmationLienExpirationDate = confirmationLienExpirationDate;
    }


}
