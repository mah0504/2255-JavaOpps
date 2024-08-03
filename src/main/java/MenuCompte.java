import java.util.Scanner;

public class MenuCompte {

    private ControllerUtilisateur controllerUtilisateur;
    private MenuUtilisateur menuUtilisateur;
    private ControllerFournisseur controllerFournisseur;
    private CompteView compteView;

    public MenuCompte() {
        controllerUtilisateur = new ControllerUtilisateur();
        controllerFournisseur = new ControllerFournisseur();
        menuUtilisateur = new MenuUtilisateur();
        compteView = new CompteView();
    }

    public void afficherMenu() {

        boolean continuer = true;
        while (continuer) {
            int choixCompteType = compteView.getCompteType();

            switch (choixCompteType) {
                case 1:
                    afficherMenuUtilisateurConnexion();
                    break;
                case 2:
                    afficherMenuFournisseurConnexion();
                    break;
                case 3 :
                    System.out.println("Au Revoir !");
                    continuer = false;
                    break;
                default:
                    compteView.AfficherMessage("Choix invalide, réessayez!");
            }

        }
    }

    public void afficherMenuUtilisateurConnexion() {
        boolean retourner = false;
        while (!retourner) {
            int choixActionType = compteView.getActionType();

            switch (choixActionType) {
                case 0:
                    compteView.AfficherMessage("Retour au Menu Principal");
                    retourner = true;
                    break;
                case 1:
                    connecterUtilisateur();
                    break;
                case 2:
                    creerUtilisateur();
                    break;
                case 3:
                    confirmerUtilisateur();
                    break;
                default:
                    compteView.AfficherMessage("Choix invalide, réessayez !");
            }
        }

    }

    public void afficherMenuFournisseurConnexion() {

        int choixActionType = compteView.getActionType();

        switch (choixActionType) {
            case 0:
                compteView.AfficherMessage("Retour au Menu Principal");
                break;
            case 1:
                connecterFournisseur();
                break;
            case 2:
                creerFournisseur();
                break;
            case 3:
                confirmerFournisseur();
                break;
            default:
                compteView.AfficherMessage("Choix invalide, réessayez !");
        }

    }

    public void creerUtilisateur() {
        String pseudo = getPseudoUnique();
        String nom = compteView.getNom();
        String prenom = compteView.getPrenom();
        String email = getEmailUnique();
        String motDePasse = getMdpValid();
        String telephone = getTelephoneValid();

        controllerUtilisateur.creerUtilisateur(pseudo, email, motDePasse, telephone, prenom, nom);

    }

    public void connecterUtilisateur() {
        String email = compteView.getEmail();
        String mdp = compteView.getMotDePasse();

        boolean connected = controllerUtilisateur.verifierConnexion(email, mdp);
        if (connected) {
            compteView.AfficherMessage("Connexion réussie !");
            menuUtilisateur.afficherMenuUtilisateur();
        } else {
            compteView.AfficherMessage("Email ou mot de passe incorrect");
        }
    }

    public void confirmerUtilisateur() {
        String confirmationLien = compteView.getConfirmationLien();
        boolean success = controllerUtilisateur.confirmerCompte(confirmationLien);
        if (success) {
            compteView.AfficherMessage("Compte confirmé avec succès !");
        } else {
            compteView.AfficherMessage("Échec de la confirmation du compte.");
        }
    }

    public void creerFournisseur() {
        String pseudo = getPseudoUnique();
        String nomCompagnie = compteView.getCompagnie();
        String email = getEmailUnique();
        String motDePasse = getMdpValid();
        String telephone = getTelephoneValid();

        controllerFournisseur.creerFournisseur(pseudo, email, motDePasse, telephone, nomCompagnie);
    }

    public void connecterFournisseur() {
        compteView.AfficherMessage("Connexion du fournisseur...");
    }

    public void confirmerFournisseur() {
        String confirmationLien = compteView.getConfirmationLien();
        boolean success = controllerFournisseur.confirmerCompte(confirmationLien);
        if (success) {
            compteView.AfficherMessage("Compte confirmé avec succès !");
        } else {
            compteView.AfficherMessage("Échec de la confirmation du compte.");
        }
    }

    public String getPseudoUnique(){
        String pseudo = compteView.getPseudo();
        while (!controllerUtilisateur.isPseudoUnique(pseudo) || !controllerFournisseur.isPseudoUnique(pseudo)) {
            compteView.AfficherMessage("Ce pseudo existe déjà, réessayez !");
            pseudo = compteView.getPseudo();
        }
        return pseudo;
    }

    private String getEmailUnique() {
        String email = compteView.getEmail();
        while (!controllerUtilisateur.isEmailUnique(email) || !controllerFournisseur.isEmailUnique(email)) {
            compteView.AfficherMessage("Cet email existe déjà, réessayez !");
            email = compteView.getEmail();
        }
        return email;
    }

    private String getMdpValid() {
        String mdp = compteView.getMotDePasse();
        while (!controllerUtilisateur.isMdpValide(mdp)) {
            compteView.AfficherMessage("Le mot de passe doit avoir au moins 8 caractères.");
            mdp = compteView.getMotDePasse();
        }
        return mdp;
    }

    private String getTelephoneValid() {
        String telephone = compteView.getTelephone();
        while (!controllerUtilisateur.isTelephoneValide(telephone)) {
            compteView.AfficherMessage("Le numéro de téléphone n'est pas valide.");
            telephone = compteView.getTelephone();
        }
        return telephone;
    }


}
