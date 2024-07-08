import java.time.LocalDateTime;
import java.util.*;

public class Fournisseur extends Acteur {

    private String adresse;
    private String capaciteFabrication;
    private ArrayList<Composantes> listeComposantesF;
    private static ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();
    private Map<String, Composantes> Stock;
    private Composantes nouvelleComposante;
    private Composantes composanteAchetee;


    // Constructor
    public Fournisseur(String nom, String adresse, String email, String motDePasse, String telephone, String capaciteFabrication) {
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.capaciteFabrication = capaciteFabrication;
    }
    public Fournisseur() {
    }


    // Getters and Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTelephone() {
        return telephone;
    }

    @Override
    protected void entrerCompagnie() {

    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCapaciteFabrication() {
        return capaciteFabrication;
    }

    public void setCapaciteFabrication(String capaciteFabrication) {
        this.capaciteFabrication = capaciteFabrication;
    }

    public ArrayList<Composantes> getListeComposantes(){
        return listeComposantesF;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", capaciteFabrication=" + capaciteFabrication +
                '}';
    }

    // Méthodes
    @Override
    public void sInscrire() {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Entrez votre nom : ");
            String nom = scanner.nextLine();
            if (nomExiste(nom)) {
                throw new IllegalArgumentException("Ce nom de fournisseur existe déjà.");
            }

            System.out.print("Entrez votre adresse : ");
            String adresse = scanner.nextLine();

            System.out.print("Entrez votre email : ");
            String email = scanner.nextLine();
            if (!emailValide(email)) {
                throw new IllegalArgumentException("L'email entré n'est pas valide.");
            }

            System.out.print("Entrez votre mot de passe: ");
            String motDePasse = scanner.nextLine();

            System.out.print("Entrez votre numéro de téléphone : ");
            String telephone = scanner.nextLine();
            if (telephone.length() > 10 || !telephone.matches("[0-9]+")) {
                throw new IllegalArgumentException("Le numéro de téléphone doit contenir uniquement des chiffres.");
            }

            System.out.print("Entrez votre capacité de fabrication : ");
            String capaciteFabrication = scanner.nextLine();

            envoyerEmail(email);
            confirmerInscription();

            Fournisseur fournisseur = new Fournisseur(nom, adresse, email, motDePasse, telephone, capaciteFabrication);
            if(confirmationEmail){
                listeFournisseurs.add(fournisseur);
            }


            System.out.println("Inscription réussie !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'inscription : " + e.getMessage());
        }
    }

    public int seConnecter() {

        continuer = true;
        Utilisateur utilisateur;
        int index = -1;

        while (continuer) {
            try {

                System.out.print("Entrez votre nom : ");
                nom = scanner.nextLine();
                System.out.print("Entrez votre mot de passe : ");
                motDePasse = scanner.nextLine();

                index = chercherNom(nom);
                if (index < 0) {
                    throw new IllegalArgumentException("Pseudo inexistant.");
                }
                utilisateur = Systeme.getInstance().getUtilisateurs().get(index);
                if (! motDePasse.equals(utilisateur.getMdp())) {
                    throw new IllegalArgumentException("Mot de passe invalide.");
                }
                System.out.println("Connexion réussie.");
                return index;

                // todo si connexion réussie, dans le main remplacer l'user par celui Systeme.getInstance().getUtilisateurs().get(index)

            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
                index = -1;
                continuer = stopContinuer();
            }
        }
        return index;
    }

    @Override
    public void modifierProfil() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Que voulez-vous modifier ?");
            System.out.println("1. Nom");
            System.out.println("2. Adresse");
            System.out.println("3. Email");
            System.out.println("4. Mot de passe");
            System.out.println("5. Téléphone");
            System.out.println("6. Capacité de fabrication");
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
                        if (nomExiste(nouveauNom)) {
                            throw new IllegalArgumentException("Ce nom de fournisseur existe déjà.");
                        }
                        this.nom = nouveauNom;
                        break;
                    case 2:
                        System.out.print("Nouvelle adresse : ");
                        String nouvelleAdresse = scanner.nextLine();
                        this.adresse = nouvelleAdresse;
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
                    case 6:
                        System.out.print("Nouvelle capacité de fabrication : ");
                        String nouvelleCapacite = scanner.nextLine();
                        this.capaciteFabrication = nouvelleCapacite;
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

    public void gererComposantes() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Que voulez-vous modifier ?");
            System.out.println("1. Ajouter des composantes à l'inventaire");
            System.out.println("2. Mettre à jour l'inventaire");
            System.out.println("3. Afficher l'inventaire");
            System.out.println("0. Quitter");

            int choix = -1;
            while (choix != 0) {
                System.out.print("Votre choix : ");
                choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        System.out.print("Nouvelle composante à ajouter: ");
                        enregistrerComposante();
                        Stock.put(nouvelleComposante.getNom(), nouvelleComposante);
                        break;
                    case 2:
                        System.out.print("Mettre à jour l'inventaire: : ");
                        composanteAchetee.choisirCompo();
                        Stock.remove(composanteAchetee.getNom());
                        break;
                    case 3:
                        System.out.print("Inventaire : ");
                        for (Map.Entry<String, Composantes> composante : Stock.entrySet()) {
                            System.out.print(composante.getKey() + ":");
                            System.out.println(composante.getValue() + "" );
                        }
                        break;
                    case 0:
                        System.out.println("Traitement terminé.");
                        break;
                    default:
                        System.out.println("Choix invalide. Réessayez.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du traitement de l'inventaire : " + e.getMessage());
        }
    }

    public void enregistrerComposante() {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Entrez le nom de la composante: ");
            String nom = scanner.nextLine();
            nouvelleComposante.setNom(nom);
            if (Stock.containsKey(nom)) {
                throw new IllegalArgumentException("Cette composante existe déjà.");
            }

            System.out.print("Entrez le type de la composante : ");
            String type = scanner.nextLine();
            nouvelleComposante.setType(type);

            System.out.print("Entrez la quantité en unités de cette composante : ");
            String inventaire = scanner.nextLine();
            nouvelleComposante.setQuantite(Integer.parseInt(inventaire));

            System.out.print("Entrez le prix de l'unité: ");
            String prix = scanner.nextLine();
            nouvelleComposante.setPrix(Float.parseFloat(prix));

            System.out.print("Entrez la description de la composante : ");
            String description = scanner.nextLine();
            nouvelleComposante.setType(description);

        } catch (Exception e) {
            System.out.println("Erreur lors de l'inscription : " + e.getMessage());
        }
    }

    /*
     * Fonction auxiliaire pour vérifier L'inscription
     */
    private boolean emailValide(String email) {
        String emailPattern = "[A-Za-z0-9-_\\.]+@[a-z]+\\.(com|fr|ca|io|web)";
        return email.matches(emailPattern);
    }

    private boolean nomExiste(String nom) {
        for (Fournisseur fournisseur : listeFournisseurs) {
            if (fournisseur.getNom().equals(nom)) {
                return true;
            }
        }
        return false;
    }

    private static int chercherNom(String nom) {
        ArrayList<Fournisseur> fournisseurs = Systeme.getInstance().getFournisseurs();
        for (int i = 0; i < fournisseurs.size(); i++) {
            if (fournisseurs.get(i).getNom().equals(nom)) {
                return i;   // le pseudo existe déjà
            }
        }
        return -1;          // le pseudo n'existe pas
    }




    public static void initialiserListeFournisseurs() {

        listeFournisseurs.add(new Fournisseur("Fournisseur1", "Adresse1", "email1@example.com", "mdp1", "1234567890", "Capacité1"));
        listeFournisseurs.add(new Fournisseur("Fournisseur2", "Adresse2", "email2@example.com", "mdp2", "0987654321", "Capacité2"));
        listeFournisseurs.add(new Fournisseur("Fournisseur3", "Adresse3", "email3@example.com", "mdp3", "1112223333", "Capacité3"));
        listeFournisseurs.add(new Fournisseur("Fournisseur4", "Adresse4", "email4@example.com", "mdp4", "4445556666", "Capacité4"));
        listeFournisseurs.add(new Fournisseur("Fournisseur5", "Adresse5", "email5@example.com", "mdp5", "7778889999", "Capacité5"));

    }

}
