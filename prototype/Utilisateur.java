import java.util.*;

public class Utilisateur implements Acteur{
    private String nom;
    private String prenom;
    private String pseudo;
    private String motDePasse;
    private String email;
    private String telephone;
    private ArrayList<Utilisateur> listeUsers;
    private ArrayList<String> listePseudos;
    private ArrayList<Interet> listeInterets;

    public Utilisateur(String nom, String prenom, String pseudo, String email, String motDePasse, String telephone, ArrayList<Interet> listeInterets) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.listeInterets = listeInterets;
        listePseudos.add(pseudo);
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

    public ArrayList<Utilisateur> getListeUsers() {
        return listeUsers;
    }

    
    public void ajouterNouvUtilisateur(Utilisateur utilisateur) {
        listeUtilisateurs.add(utilisateur);  // après connexion on ajoute
        // voir methode se connecter puis harmoniser 
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

    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", listeUsers=" + listeUsers +
                ", listeInterets=" + listeInterets +
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

            Interet.initialiserListeInterets();

            System.out.println("Choisissez vos intérêts (entrez le numéro):");
            for (int i = 0; i < listeInterets.size(); i++) {
                System.out.println((i + 1) + ". " + listeInterets.get(i).getNom());
            }

            for (int i = 0; i < 10; i++) {
                System.out.print("Choix " + (i + 1) + ": ");
                int choix = scanner.nextInt();
                if (choix >= 1 && choix <= listeInterets.size()) {
                    Interet interet = listeInterets.get(choix - 1);
                    interet.souscrire(this);
                } else {
                    System.out.println("Choix invalide. Réessayez.");
                    i--;
                }
            }
            
            Utilisateur utilisateur = new Utilisateur(nom, prenom, pseudo, email, motDePasse, telephone, listeInterets);
            listeUsers.add(utilisateur);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'inscription : " + e.getMessage());
        }

        System.out.println("Inscription réussie!");
    }

    private static boolean emailValide(String email) {
        String emailPattern = "[A-Za-z0-9-_\\.]+@[a-z]+\\.(com|fr|ca|io|web)";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
                scanner.nextLine(); // Pour consommer le retour à la ligne

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

    
    public void voirMetriques(Robot robot) {
        robot.getEtats();
    }

    







    
    public void gererFlotte() {
    }

    public void gererSuiveurs() {
    }

    public void gererActivites() {
    }

    public void gererInterets() {
    }

    public void suivreUtilisateur() {
    }

    public void sInscrireActivite() {
    }


    public void voirNotifications() {
    }

    public void initialiserUtilisateurs() {
  
        Utilisateur utilisateur1 = new Utilisateur("Dannane", "Chaima", "aithu", "aithu@example.com", "motdepasse1", "1357924680", listeInterets);
        listeUsers.add(utilisateur1);
    
        Utilisateur utilisateur2 = new Utilisateur("nom2", "Maha", "maha", "maha@example.com", "motdepasse2", "2468013579",listeInterets);
        listeUsers.add(utilisateur2);
    
        Utilisateur utilisateur3 = new Utilisateur("nom3","Sabrina", "miaou", "miaou@example.com", "motdepasse3", "9753124680", listeInterets);
        listeUsers.add(utilisateur3);
    
        Utilisateur utilisateur4 = new Utilisateur("nom4", "Audrey", "aude", "aude@example.com", "motdepasse4", "0864213579", listeInterets);
        listeUsers.add(utilisateur4);
    
        Utilisateur utilisateur5 = new Utilisateur("nom5", "prenom5", "nprenom5", "nprenom5@example.com", "motdepasse5", "3332221111", listeInterets);
        listeUsers.add(utilisateur5);

        Utilisateur utilisateur6 = new Utilisateur("nom6", "prenom6", "nprenom6", "nprenom6@example.com", "motdepasse6", "2221113333", listeInterets);
        listeUsers.add(utilisateur6);

        Utilisateur utilisateur7 = new Utilisateur("nom7", "prenom7", "nprenom7", "nprenom7@example.com", "motdepasse7", "6665554444", listeInterets);
        listeUsers.add(utilisateur7);

        Utilisateur utilisateur8 = new Utilisateur("nom8", "prenom8", "nprenom8", "nprenom8@example.com", "motdepasse8", "5554446666", listeInterets);
        listeUsers.add(utilisateur8);

        Utilisateur utilisateur9 = new Utilisateur("nom9", "prenom9", "nprenom9", "nprenom9@example.com", "motdepasse9", "8889997777", listeInterets);
        listeUsers.add(utilisateur9);

        Utilisateur utilisateur10 = new Utilisateur("nom10", "prenom10", "nprenom10", "nprenom10@example.com", "motdepasse10", "9997778888", listeInterets);
        listeUsers.add(utilisateur10);
    
    
    }
}

