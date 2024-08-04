import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

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

    /**
     * Récupère l'état de confirmation de l'email
     *
     * @return {@code true} si l'email est confirmé, {@code false} sinon
     */
    public boolean getConfirmed(){
        return confirmed;
    }

    /**
     * Assigne l'état de confirmation
     *
     * @param confirmed {@code true} pour confirmer l'email, {@code false} pour annuler la confirmation
     */
    public void isConfirmed(boolean confirmed){
        this.confirmed = confirmed;
    }

    /**
     * Récupère le lien de confirmation envoyé par email
     *
     * @return le lien de confirmation
     */
    public String getConfirmationLien(){
        String confirmationLien = UUID.randomUUID().toString();
        return confirmationLien;
    }

    /**
     * Assigne un lien de confirmation au compte à vérifier
     *
     * @param confirmationLien le lien de confirmation à vérifier
     */
    public void setConfirmationLien(String confirmationLien){
        this.confirmationLien = confirmationLien;
    }

    /**
     * Récupère la date d'expiration du lien de confirmation.
     * Elle est calculée par rapport à la date le lien de confirmation a été envoyé.
     * Utilise un Formatter pour retourner une Date en String
     *
     * @return string Date d'expiration du lien de confirmation
     */
    public String getConfirmationLienExpirationDate(){
        LocalDateTime expirationDate = LocalDateTime.now().plusHours(24);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String confirmationLienExpirationDate = expirationDate.format(formatter);
        return confirmationLienExpirationDate;
    }

    /**
     * Assigne une date d'expiration du lien de confirmation
     *
     * @param confirmationLienExpirationDate Date en String
     */
    public void setConfirmationLienExpirationDate(String confirmationLienExpirationDate){
        this.confirmationLienExpirationDate = confirmationLienExpirationDate;
    }


}
