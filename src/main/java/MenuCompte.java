import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCompte {

    private Scanner scanner = new Scanner(System.in);

    private View view;
    private ControllerUtilisateur controllerUtilisateur;
    private ControllerFournisseur controllerFournisseur;

    private MenuUtilisateur menuUtilisateur;
    private ControllerRobot controllerRobot;
    private MenuFournisseur menuFournisseur;
    private CompteView compteView;

    public MenuCompte(View view) {
        this.view = view;
        this.controllerUtilisateur = new ControllerUtilisateur(this, view);
        this.controllerFournisseur = new ControllerFournisseur(this, view);
    }

    /*public MenuCompte(ControllerUtilisateur controllerUtilisateur, ControllerFournisseur controllerFournisseur, ControllerRobot controllerRobot) {
        this.controllerUtilisateur =  controllerUtilisateur;
        this.controllerFournisseur = controllerFournisseur;
        this.controllerRobot= controllerRobot;
        //this.menuUtilisateur = new MenuUtilisateur(controllerUtilisateur);
        //this.menuFournisseur= new MenuFournisseur(controllerFournisseur);

        compteView = new CompteView();
    }*/

    public void setCompte(Compte compte){
        if (compte instanceof Utilisateur){
            new MenuUtilisateur((Utilisateur) compte, controllerUtilisateur).afficherMenuUtilisateur();
        } else if (compte instanceof Fournisseur){
            new MenuFournisseur((Fournisseur) compte, controllerFournisseur).afficherMenuFournisseur();
        }

    }

    /*public void afficherMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        int choix;

        while (continuer) {
            try {
                compteView.compteType();
                choix = scanner.nextInt();

                switch (choix) {
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
            } catch (InputMismatchException e) {
                System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
                scanner.next(); // Clear the invalid input
            }
        }
    }*/

    public void afficherMenuPrincipal(){
        boolean continuer = true;
        while(continuer){

            view.home();
            int choix = Integer.parseInt(scanner.nextLine());

            switch (choix){
                case 1:
                    afficherMenuUtilisateurConnexion();
                    break;
                case 2 :
                    afficherMenuFournisseurConnexion();
                    break;
                case 0 :
                    view.afficherMessage("Au Revoir !");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayez !");

            }
        }
    }




    public void afficherMenuUtilisateurConnexion() {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            try {
                view.homeCompte();
                int choix = Integer.parseInt(scanner.nextLine());

                switch (choix) {
                    case 0:
                        view.afficherMessage("Retour au Menu Principal");
                        continuer = false;
                        break;
                    case 1:
                        controllerUtilisateur.seConnecter();
                        break;
                    case 2:
                        controllerUtilisateur.sInscrire();
                        break;
                    case 3:
                        controllerUtilisateur.confirmerInscription();
                        break;
                    default:
                        compteView.AfficherMessage("Choix invalide, réessayez!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    public void afficherMenuFournisseurConnexion() {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            try {
                view.homeCompte();
                int choix = Integer.parseInt(scanner.nextLine());

                switch (choix) {
                    case 0:
                        view.afficherMessage("Retour au Menu Principal");
                        continuer = false;
                        break;
                    case 1:
                        controllerFournisseur.seConnecter();
                        break;
                    case 2:
                        controllerFournisseur.sInscrire();
                        break;
                    case 3:
                        controllerFournisseur.confirmerInscription();
                        break;
                    default:
                        view.afficherMessage("Choix invalide, réessayez !");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    /*public void creerUtilisateur() {
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

        Utilisateur utilisateur = controllerUtilisateur.verifierConnexion(email, mdp);
        if (utilisateur != null) {
            compteView.AfficherMessage("Connexion réussie !");
            controllerUtilisateur.setUtilisateur(utilisateur);
            menuUtilisateur.afficherMenuUtilisateur(utilisateur);
        } else {
            compteView.AfficherMessage("Email ou mot de passe incorrect");
        }
    }

    public void confirmerUtilisateur() {
        String email = compteView.getEmail();
        String confirmationLien = compteView.getConfirmationLien();

        boolean success = controllerUtilisateur.confirmerCompte(email,confirmationLien);
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
        String email = compteView.getEmail();
        String mdp = compteView.getMotDePasse();

        Fournisseur fournisseur = controllerFournisseur.verifierConnexion(email, mdp);
        if (fournisseur != null) {
            compteView.AfficherMessage("Connexion réussie !");
            controllerFournisseur.setFournisseur(fournisseur);
            menuFournisseur.afficherMenuFournisseur();
        } else {
            compteView.AfficherMessage("Email ou mot de passe incorrect");
        }
    }

    public void confirmerFournisseur() {
        String email = compteView.getEmail();
        String confirmationLien = compteView.getConfirmationLien();
        boolean success = controllerFournisseur.confirmerCompte(email, confirmationLien);
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
    }*/


}
