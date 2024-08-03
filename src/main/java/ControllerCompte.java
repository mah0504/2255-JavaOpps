import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.print.attribute.HashAttributeSet;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
public class ControllerCompte {

    private Compte compte;
    private MenuCompte CompteView;

    //à revoir
    private Utilisateur utilisateur;
    private MenuUtilisateur UtilisateurView;
    private ControllerUtilisateur controllerUtilisateur;
    private Fournisseur fournisseur;
    private ControllerFournisseur controllerFournisseur;

    private ArrayList<Utilisateur> listeUtilisateurs;
    private ArrayList<Fournisseur> listeFournisseurs;
    private ArrayList<String> listePseudos;
    private ArrayList<String> listeEmails;


    public ControllerCompte(Compte compte, MenuCompte compteView) {
        this.compte = compte;
        this.CompteView = compteView;
        this.listeUtilisateurs = new ArrayList<>();
        this.listeFournisseurs = new ArrayList<>();
        this.listePseudos = new ArrayList<>();
        this.listeEmails = new ArrayList<>();
        initialiserListes();
        initialiserPseudosMails();
    }


    /**
     * Génère la liste des comptes enregistrés au système Robotix.
     * @return
     * @param <T> Utilisateur ou Fournisseur
     */
    private <T extends Compte> ArrayList<T> genererListe(String path) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(path)) {
            Type type = new TypeToken<ArrayList<T>>() {}.getType();
            return gson.fromJson(reader, type);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // sinon?
    }


    /**
     * Permet d'initialiser les listes des utilisateurs et des fournisseurs à
     * partir de leurs fichiers json
     */
    private void initialiserListes(){
        this.listeUtilisateurs = genererListe("src/main/resources/utilisateurs.json");
        this.listeFournisseurs = genererListe("src/main/resources/fournisseurs.json");
    }

    /**
     * Permet d'initialiser la liste des Pseudos et des mails afin de s'assurer de l'unicité
     */
    private void initialiserPseudosMails(){
        for(Utilisateur utilisateur : listeUtilisateurs){
            listePseudos.add(utilisateur.getPseudo());
            listeEmails.add(utilisateur.getEmail());
        }
        for(Fournisseur fournisseur : listeFournisseurs){
            listePseudos.add(fournisseur.getPseudo());
            listeEmails.add(fournisseur.getEmail());
        }
    }


    public void creerCompte(){
        int typeCompte =  CompteView.getCompteType();

        switch (typeCompte) {
            case 1:
                String pseudoUser = getPseudoUnique();
                String nomUser = UtilisateurView.getNom();
                String prenomUser = UtilisateurView.getPrenom();
                String emailUser = getEmailUnique();
                String motDePasseUser = getMdpValid();
                String telephoneUser = getTelephoneValid();

                if (creerUtilisateur(pseudoUser, nomUser,prenomUser,emailUser,motDePasseUser,telephoneUser)) {
                    CompteView.AfficherMessage("Inscription réussie !");
                } else {
                    CompteView.AfficherMessage("Échec à l'inscription !");
                }
                break;

            case 2:
                String pseudoSupplier = getPseudoUnique();
                String nomCompagnieSupplier = CompteView.getCompagnie();
                String emailSupplier = getEmailUnique();
                String motDePasseSupplier = getMdpValid();
                String telephoneSupplier = getTelephoneValid();

                if (creerFournisseur(pseudoSupplier, nomCompagnieSupplier, emailSupplier,motDePasseSupplier,telephoneSupplier)) {
                    CompteView.AfficherMessage("Inscription réussie !");
                } else {
                    CompteView.AfficherMessage("Échec à l'inscription !");
                }
                break;

            default:
                System.out.println("Choix Invalide");


        }

    }

    /********************************* Fonctions auxiliaires pour creerCompte() *****************************************************/

    public boolean creerUtilisateur(String pseudo, String nom, String prenom, String email, String motDePasse, String telephone) {
        if(isPseudoUnique(pseudo) && isEmailUnique(email) && isMdpValid(motDePasse) && isTelephoneValid(telephone)){
            Utilisateur user = new Utilisateur(pseudo, email, motDePasse, telephone, prenom, nom);
            listeUtilisateurs.add(user);
            listePseudos.add(pseudo);
            listeEmails.add(email);
            //envoyer mail de confirmation
            controllerUtilisateur.UtilisateurToJson(listeUtilisateurs);
            return true;
        }
        return false;
    }

    public boolean creerFournisseur(String pseudo, String nomCompagnie, String email, String motDePasse, String telephone) {
        if(isPseudoUnique(pseudo) && isEmailUnique(email) && isMdpValid(motDePasse) && isTelephoneValid(telephone)){
            Fournisseur supplier = new Fournisseur(pseudo, nomCompagnie, email, motDePasse, telephone);
            listeFournisseurs.add(supplier);
            listePseudos.add(pseudo);
            listeEmails.add(email);
            //envoyer mail confirmation
            controllerFournisseur.FournisseurToJson(listeFournisseurs);
            return true;
        }
        return false;
    }

    public boolean isPseudoUnique(String pseudo){
        return !listePseudos.contains(pseudo);
    }

    public String getPseudoUnique(){
        String pseudo = CompteView.getPseudo();
        while(!isPseudoUnique(pseudo)){
            CompteView.AfficherMessage("Ce pseudo existe déjà, réssayez !");
            pseudo = CompteView.getPseudo();
        }
        return pseudo;
    }

    public boolean isEmailUnique(String email){
        return !listeEmails.contains(email);
    }

    public String getEmailUnique(){
        String email = CompteView.getEmail();
        while(!isEmailUnique(email)){
            CompteView.AfficherMessage("Cet email existe déjà, réssayez ! ");
            email = CompteView.getEmail();
        }
        return email;
    }

    public boolean isMdpValid(String mdp){
        return mdp.length() > 7;
    }

    public String getMdpValid(){
        String mdp = CompteView.getMotDePasse();
        while(!isMdpValid(mdp)){
            CompteView.AfficherMessage("Le mot de passe doit avoir au moins 8 caractères. ");
            mdp = CompteView.getMotDePasse();
        }

        return mdp;
    }

    public boolean isTelephoneValid(String telephone){
        return telephone.length() == 10;
    }

    public String getTelephoneValid(){
        String telephone = CompteView.getTelephone();
        while(!isTelephoneValid(telephone)){
            CompteView.AfficherMessage("LE numéro de téléphone n'est pas valide. ");
            telephone = CompteView.getTelephone();
        }
        return telephone;
    }
    /************************************************************************************************************/


    public void seConnecter(){

    } ;


    /**
     * Vérifie l'existence d'un pseudo dans les comptes inscrits à Robotix.
     * @return
     */
    public Compte verifierPseudo(String pseudo) {

        pseudo = pseudo.toLowerCase();

        for (Utilisateur utilisateur : listeUtilisateurs) {
            if (utilisateur.getPseudo().equals(pseudo)) {
                return utilisateur;
            }
        }

        for (Fournisseur fournisseur : listeFournisseurs) {
            if (fournisseur.getPseudo().equals(pseudo)) {
                return fournisseur;
            }
        }

        return null;
    }
}
