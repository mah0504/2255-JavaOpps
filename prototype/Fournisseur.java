import java.util.*;

public class Fournisseur {
    private String nom;
    private String adresse;
    private String email;
    private String motDePasse;
    private String telephone;
    private String capaciteFabrication;
    private ArrayList<Composante> composantes;
    private ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();

    // Constructor
    public Fournisseur(String nom, String adresse, String email, String motDePasse, String telephone, String capaciteFabrication) {
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.capaciteFabrication = capaciteFabrication;
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

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCapaciteFabrication() {
        return capaciteFabrication;
    }

    public void setCapaciteFabrication(String capaciteFabrication) {
        this.capaciteFabrication = capaciteFabrication;
    }

    // Méthodes
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

            Fournisseur fournisseur = new Fournisseur(nom, adresse, email, motDePasse, telephone, capaciteFabrication);
            listeFournisseurs.add(fournisseur);

            System.out.println("Inscription réussie !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'inscription : " + e.getMessage());
        }
    }

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

    public void seConnecter() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Entrez votre nom : ");
            String nom = scanner.nextLine();
            System.out.print("Entrez votre mot de passe : ");
            String motDePasse = scanner.nextLine();

            boolean trouve = false;
            for (Fournisseur fournisseur : listeFournisseurs) {
                if (fournisseur.getNom().equals(nom) && fournisseur.getMotDePasse().equals(motDePasse)) {
                    System.out.println("Connexion réussie !");
                    trouve = true;
                    break;
                }
            }

            if (!trouve) {
                System.out.println("Nom ou mot de passe invalide ou inexistant.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion : " + e.getMessage());
        }
    }

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
                scanner.nextLine(); // Pour consommer le retour à la ligne

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

    public void gererComposantes() {
    }

    public void enregistrerComposante() {
    }

    public static void initialiserListeFournisseurs() {
        ArrayList<Fournisseur> listeFournisseurs = new ArrayList<>();

        listeFournisseurs.add(new Fournisseur("Fournisseur1", "Adresse1", "email1@example.com", "mdp1", "1234567890", "Capacité1"));
        listeFournisseurs.add(new Fournisseur("Fournisseur2", "Adresse2", "email2@example.com", "mdp2", "0987654321", "Capacité2"));
        listeFournisseurs.add(new Fournisseur("Fournisseur3", "Adresse3", "email3@example.com", "mdp3", "1112223333", "Capacité3"));
        listeFournisseurs.add(new Fournisseur("Fournisseur4", "Adresse4", "email4@example.com", "mdp4", "4445556666", "Capacité4"));
        listeFournisseurs.add(new Fournisseur("Fournisseur5", "Adresse5", "email5@example.com", "mdp5", "7778889999", "Capacité5"));

    }
}

