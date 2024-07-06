import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.*;

public class Utilisateur implements Acteur {
    private String nom;
    private String prenom;
    private String pseudo;
    private String motDePasse;
    private String email;
    private String telephone;
    private LocalDateTime dateInscription =  LocalDateTime.now(); //la date où on commence l'inscription
    private boolean confirmationEmail = false; //pour confirmer le mail
    private String codeConfirmation = genererCodeConfirmation(); //clé à retourner pour confirmer le mail
    private ArrayList<Utilisateur> listeUsers;
    private ArrayList<String> listePseudos;
    private ArrayList<Interet> listeInterets; // liste de 10 interets que suit l'utilisateur
    private ArrayList<Utilisateur> listeSuivis; // liste des utilisateurs que je suis
    private ArrayList<Utilisateur> listeSuiveurs; // liste des utilisateurs qui me suivent
    private ArrayList<Activite> listeActivitesbyUser; // liste des activités que maintiens l'utilisateur
    private int pointsGagnes;
    private int classement;

    public Utilisateur(String nom, String prenom, String pseudo, String email, String motDePasse, String telephone, int pointsGagnes) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.pointsGagnes = pointsGagnes;
        listePseudos.add(pseudo);
    }

    public Utilisateur(){

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getPoints() {
        return pointsGagnes;
    }

    public void setPoints(int pointsGagnes) {
        this.pointsGagnes = pointsGagnes;
    }

    public ArrayList<Utilisateur> getListeUsers() {
        return listeUsers;
    }

    public void setListeUsers(ArrayList<Utilisateur> listeUsers) {
        this.listeUsers = listeUsers;
    }

    public ArrayList<Interet> getListeInterets() {
        return listeInterets;
    }

    public void setListeInterets(ArrayList<Interet> listeInterets) {
        this.listeInterets = listeInterets;
    }

    public ArrayList<Utilisateur> getListeSuivis() {
        return listeSuivis;
    }

    public void setListeSuivis(ArrayList<Utilisateur> listeSuivis) {
        this.listeSuivis = listeSuivis;
    }

    public ArrayList<Utilisateur> getListeSuiveurs() {
        return listeSuiveurs;
    }

    public void setListeSuiveurs(ArrayList<Utilisateur> listeSuiveurs) {
        this.listeSuiveurs = listeSuiveurs;
    }

    public ArrayList<Activite> getListeActivitesbyUser() {
        return listeActivitesbyUser;
    }

    public void setListeActivitesbyUser(ArrayList<Activite> listeActivitesbyUser) {
        this.listeActivitesbyUser = listeActivitesbyUser;
    }


    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", points accumulés=" + pointsGagnes +
                '}';
    }

    // Méthodes
    @Override
    public void sInscrire() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Entrez votre nom: ");
            String nom = scanner.nextLine();
            if (!nom.matches("[A-Za-z]+")) {
                throw new IllegalArgumentException("Le nom ne doit pas contenir de chiffres.");
            }

            System.out.print("Entrez votre prénom: ");
            String prenom = scanner.nextLine();
            if (!prenom.matches("[A-Za-z]+")) {
                throw new IllegalArgumentException("Le prénom ne doit pas contenir de chiffres.");
            }

            System.out.print("Entrez votre pseudo: ");
            String pseudo = scanner.nextLine();
            if (listePseudos.contains(pseudo)) {
                throw new IllegalArgumentException("Ce pseudo existe déjà, rééssayez!");
            }

            System.out.print("Entrez votre mot de passe: ");
            String motDePasse = scanner.nextLine();

            System.out.print("Entrez votre email: ");
            String email = scanner.nextLine();
            if (!emailValide(email)) {
                throw new IllegalArgumentException("L'email entré n'est pas valide.");
            }

            System.out.print("Entrez votre numéro de téléphone: ");
            String telephone = scanner.nextLine();
            if (telephone.length() > 10 || !telephone.matches("[0-9]+")) {
                throw new IllegalArgumentException("Le numéro de téléphone doit contenir uniquement des chiffres.");
            }

            gererInterets();

            int pointsGagnes = 0;

            //enoyer mail de confirmation
            envoyerEmail(email);
            confirmerInscription();

            Utilisateur utilisateur = new Utilisateur(nom, prenom, pseudo, email, motDePasse, telephone, pointsGagnes);
            if(confirmationEmail){
                listeUsers.add(utilisateur);
            }


        } catch (Exception e) {
            System.out.println("Erreur lors de l'inscription : " + e.getMessage());
        }

        System.out.println("Inscription réussie!");
    }

    @Override
    public void seConnecter() {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Entrez votre pseudo : ");
            String pseudo = scanner.nextLine();
            System.out.print("Entrez votre mot de passe : ");
            String motDePasse = scanner.nextLine();

            boolean trouve = false;
            for (Utilisateur utilisateur : listeUsers) {
                if (utilisateur.getPseudo().equals(pseudo) && utilisateur.getMotDePasse().equals(motDePasse)) {
                    System.out.println("Connexion réussie !!");
                    trouve = true;
                    break;
                }
            }

            if (!trouve) {
                System.out.println("Pseudo ou mot de passe invalide ou inexistants.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
        }
    }

    @Override
    public void modifierProfil() {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Que voulez-vous modifier ?");
            System.out.println("1. Nom");
            System.out.println("2. Prénom");
            System.out.println("3. Email");
            System.out.println("4. Mot de passe");
            System.out.println("5. Numéro de téléphone");
            System.out.println("0. Quitter");

            int choix = -1;
            while (choix != 0) {
                System.out.print("Votre choix : ");
                choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        System.out.print("Nouveau nom : ");
                        String nouveauNom = scanner.nextLine();
                        if (!nouveauNom.matches("[A-Za-z]+")) {
                            throw new IllegalArgumentException("Le nom ne doit pas contenir de chiffres.");
                        }
                        this.nom = nouveauNom;
                        break;
                    case 2:
                        System.out.print("Nouveau prénom : ");
                        String nouveauPrenom = scanner.nextLine();
                        if (!nouveauPrenom.matches("[A-Za-z]+")) {
                            throw new IllegalArgumentException("Le prénom ne doit pas contenir de chiffres.");
                        }
                        this.prenom = nouveauPrenom;
                        break;
                    case 3:
                        System.out.print("Nouvel email : ");
                        String nouvelEmail = scanner.nextLine();
                        if (!emailValide(nouvelEmail)) {
                            throw new IllegalArgumentException("L'email entré n'est pas valide.");
                        }
                        this.email = nouvelEmail;
                        break;
                    case 4:
                        System.out.print("Nouveau mot de passe : ");
                        String nouveauMotDePasse = scanner.nextLine();
                        this.motDePasse = nouveauMotDePasse;
                        break;
                    case 5:
                        System.out.print("Nouveau numéro de téléphone : ");
                        String nouveauTelephone = scanner.nextLine();
                        if (nouveauTelephone.length() > 10 || !nouveauTelephone.matches("[0-9]+")) {
                            throw new IllegalArgumentException("Le numéro de téléphone doit contenir uniquement des chiffres.");
                        }
                        this.telephone = nouveauTelephone;
                        break;
                    case 0:
                        System.out.println("Modification terminée.");
                        break;
                    default:
                        System.out.println("Choix invalide. Réessayez.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la modification du profil : " + e.getMessage());
        }
    }

    @Override
    public void confirmerInscription() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Entrez le code de confirmation reçu par email : ");
            String cle = scanner.nextLine();

            if (codeConfirmation.equals(cle) && dateInscription.plusHours(24).isAfter(LocalDateTime.now())) {
                this.confirmationEmail = true;
                System.out.println("Inscription avec succès !");
            } else {
                System.out.println("Lien de confirmation invalide ou expiré.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la confirmation : " + e.getMessage());
        }
    }

    public void gererFlotte() {
        //afficher les robots de la flotte
        //ajouter/supprimer/màj des actions et tâches de chaque robot
    }

    public void gererSuiveurs() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuer = true;

            while (continuer) {
                System.out.println("Que voulez-vous faire ?");
                System.out.println("1. Suivre un utilisateur");
                System.out.println("2. Voir les utilisateurs que vous suivez");
                System.out.println("3. Voir les utilisateurs qui vous suivent");
                System.out.println("0. Quitter");

                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        System.out.print("Qui voulez vous suivre ?");
                        Utilisateur utilisateurSuivi = rechercherParPseudo(scanner.nextLine());
                        if (utilisateurSuivi != null) {
                            suivreUtilisateur(utilisateurSuivi);
                        } else {
                            System.out.println("Utilisateur non trouvé.");
                        }
                        break;
                    case 2:
                        voirSuivis();
                        break;
                    case 3:
                        voirSuiveurs();
                        break;
                    case 0:
                        continuer = false;
                        break;
                    default:
                        System.out.println("Choix invalide. Réessayez !");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la gestion des suivis : " + e.getMessage());
        }
    }

    public void gererActivites() {
        //afficher les actvités auxquelles je suis inscrits
        //Calculer les Points
        //Montrer le classement
    }

    public void gererInterets() {
        ArrayList<Interet> listeInteretsGeneraux = Interet.getListeInteretsGeneraux();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Choisissez vos intérêts (entrez le numéro, 0 pour aucun choix):");
            for (int i = 0; i < listeInteretsGeneraux.size(); i++) {
                System.out.println((i + 1) + ". " + listeInteretsGeneraux.get(i).getNom());
            }

            int nbChoix = 0;
            while (nbChoix < 10) {
                System.out.print("Choix " + (nbChoix + 1) + ": ");
                int choix = scanner.nextInt();
                scanner.nextLine();

                if (choix == 0) {
                    break; // L'utilisateur ne choist aucun intéret proposé
                } else if (choix >= 1 && choix <= listeInteretsGeneraux.size()) {
                    Interet interet = listeInteretsGeneraux.get(choix - 1);
                    interet.souscrire(this);
                    nbChoix++;
                } else {
                    System.out.println("Choix invalide. Réessayez.");
                }
            }

            // Si l'utilisateur n'a pas sélectionné 10 choix, lui demander d'ajouter de nouveaux intérêts
            while (nbChoix < 10) {
                System.out.print("Entrez un nouvel intérêt : ");
                Interet nvInteret = new Interet(scanner.nextLine());
                listeInteretsGeneraux.add(nvInteret);
                nvInteret.souscrire(this);
                nbChoix++;
            }

        } catch (Exception e) {
            System.out.println("Erreur lors de la gestion des intérêts : " + e.getMessage());
        }
    }

    public void suivreUtilisateur(Utilisateur utilisateur) {
        if (!this.listeSuivis.contains(utilisateur)) {
            this.listeSuivis.add(utilisateur);
            utilisateur.listeSuiveurs.add(this);
            System.out.println("Vous suivez " + utilisateur.getPseudo());
        } else {
            System.out.println("Vous suivez déjà "+ utilisateur.getPseudo());
        }
    }

    public void sInscrireActivite() {
        //get l'activite et l'ajouter à la liste de mes actvités
        //get liste des Interets + choisir un Interet + choisir uen activité dans la liste des Activites
        //get les Intérets et choisir une actvité et l'ajouter à la liste de mes actvités (Map<Interets, ArrayList<Activite>)
    }

    public void voirNotifications() {
    }

    public void gererClassement(){
    }

    /*
     * Fonctions auxiliaires qui gèrent les Suivis
     */
    public void voirSuivis() {
        System.out.println("Liste des utilisateurs que vous suivez :");
        for (Utilisateur utilisateur : listeSuivis) {
            System.out.println(utilisateur.getPseudo());
        }
    }

    public void voirSuiveurs() {
        System.out.println("Liste des utilisateurs qui vous suivent :");
        for (Utilisateur utilisateur : listeSuiveurs) {
            System.out.println(utilisateur.getPseudo());
        }
    }

    private Utilisateur rechercherParPseudo(String pseudo) {
        for (Utilisateur utilisateur : listeUsers) {
            if (utilisateur.getPseudo().equals(pseudo)) {
                return utilisateur;
            }
        }
        return null;
    }
    /* ************************************************************************************************ */

    /*
     * Fonction auxiliaire pour vérifier L'inscription
     */
    private static boolean emailValide(String email) {
        String emailPattern = "[A-Za-z0-9-_\\.]+@[a-z]+\\.(com|fr|ca|io|web)";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private String genererCodeConfirmation() {
        return UUID.randomUUID().toString();
    }

    private void envoyerEmail(String destinataire) {
        System.out.println("Envoi d'email à : " + destinataire);
        System.out.println("Sujet : Confirmation de l'inscription" );
        System.out.println("Contenu : Cliquez sur le lien pour confirmer votre inscription  et saisisez le code :" + codeConfirmation);
    }
    /* ************************************************************************************************ */

    public void initialiserUtilisateurs() {

        Utilisateur utilisateur1 = new Utilisateur("Dannane", "Chaima", "aithu", "aithu@example.com", "motdepasse1", "1357924680", 0);
        listeUsers.add(utilisateur1);

        Utilisateur utilisateur2 = new Utilisateur("nom2", "Maha", "maha", "maha@example.com", "motdepasse2", "2468013579", 0);
        listeUsers.add(utilisateur2);

        Utilisateur utilisateur3 = new Utilisateur("nom3","Sabrina", "miaou", "miaou@example.com", "motdepasse3", "9753124680", 0);
        listeUsers.add(utilisateur3);

        Utilisateur utilisateur4 = new Utilisateur("nom4", "Audrey", "aude", "aude@example.com", "motdepasse4", "0864213579", 0);
        listeUsers.add(utilisateur4);

        Utilisateur utilisateur5 = new Utilisateur("nom5", "prenom5", "nprenom5", "nprenom5@example.com", "motdepasse5", "3332221111", 0);
        listeUsers.add(utilisateur5);

        Utilisateur utilisateur6 = new Utilisateur("nom6", "prenom6", "nprenom6", "nprenom6@example.com", "motdepasse6", "2221113333", 0);
        listeUsers.add(utilisateur6);

        Utilisateur utilisateur7 = new Utilisateur("nom7", "prenom7", "nprenom7", "nprenom7@example.com", "motdepasse7", "6665554444", 0);
        listeUsers.add(utilisateur7);

        Utilisateur utilisateur8 = new Utilisateur("nom8", "prenom8", "nprenom8", "nprenom8@example.com", "motdepasse8", "5554446666", 0);
        listeUsers.add(utilisateur8);

        Utilisateur utilisateur9 = new Utilisateur("nom9", "prenom9", "nprenom9", "nprenom9@example.com", "motdepasse9", "8889997777", 0);
        listeUsers.add(utilisateur9);

        Utilisateur utilisateur10 = new Utilisateur("nom10", "prenom10", "nprenom10", "nprenom10@example.com", "motdepasse10", "9997778888", 0);
        listeUsers.add(utilisateur10);

        suivreUtilisateur(utilisateur1);
    }
}

