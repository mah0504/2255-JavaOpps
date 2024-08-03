import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.util.UUID;
import java.time.LocalDateTime;

public class ControllerUtilisateur{

    private ArrayList<Utilisateur> listeUtilisateurs;

    public ControllerUtilisateur(){
        this.listeUtilisateurs = new ArrayList<>();
        getListeUtilisateursFromJson();
    }

    private void getListeUtilisateursFromJson(){
        try(FileReader reader = new FileReader("src/main/resources/utilisateurs.json")){
            Gson gson = new Gson();
            Type listeUtilisateurstype = new TypeToken<ArrayList<Utilisateur>>(){}.getType();
            listeUtilisateurs = gson.fromJson(reader, listeUtilisateurstype);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Utilisateur> getListeUtilisateurs(){
        return listeUtilisateurs;
    }

    private void listeUtilisateursToJson(ArrayList<Utilisateur> listeUtilisateurs){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter("src/main/resources/utilisateurs.json")){
            gson.toJson(listeUtilisateurs, writer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean confirmerUtilisateur(String pseudo){
        boolean confirme = true;
        for (Utilisateur utilisateur : listeUtilisateurs) {
            if (utilisateur.getPseudo().equals(pseudo)) {
                confirme = false;
                break;
            }
        }
        return confirme;
    }

    public void creerUtilisateur(String pseudo, String nom, String prenom, String email, String mdp, String telephone) {
        Utilisateur nouvelUtilisateur = new Utilisateur(pseudo, nom, prenom, email, mdp, telephone);

        if (confirmerUtilisateur(pseudo)){

            //Initialiser le lien de confirmation
            String confirmationLien = UUID.randomUUID().toString();

            //Initialiser la Date d'expiration de ce lien
            LocalDateTime expiration = LocalDateTime.now().plusHours(24);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String DateExpiration = expiration.format(formatter);

            //Assigner les nouvelles valeurs
            nouvelUtilisateur.setConfirmationLien(confirmationLien);
            nouvelUtilisateur.setConfirmationLienExpirationDate(DateExpiration);

            listeUtilisateurs.add(nouvelUtilisateur);
            listeUtilisateursToJson(listeUtilisateurs);

            //Envoyer le mail de confirmation
            envoyerEmailConfirmation(nouvelUtilisateur.getEmail(), confirmationLien);

            System.out.println("Inscription réussie. En attente de la confirmation de l'email .");
        } else {
            System.out.println("Échec à la création du compte.");
        }
    }

    public boolean isPseudoUnique(String pseudo){
        for(Utilisateur utilisateur : listeUtilisateurs){
            if(utilisateur.getPseudo().equals(pseudo)){
                return false;
            }
        }
        return true;
    }

    public boolean isEmailUnique(String email){
        for(Utilisateur utilisateur : listeUtilisateurs){
            if(utilisateur.getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }

    public boolean isMdpValide(String mdp){
        return mdp.length() > 7;
    }

    public boolean isTelephoneValide(String telephone){
        return telephone.length() == 10;
    }

    private void envoyerEmailConfirmation(String email, String confirmationLien) {

        System.out.println("Email de confirmation envoyé à : " + email);
        System.out.println("Lien de confirmation : " + confirmationLien);
    }

    public boolean confirmerCompte(String confirmationLien) {
        for (Utilisateur utilisateur : listeUtilisateurs) {
            confirmationLien = confirmationLien.trim();
            String userConfirmation = utilisateur.getConfirmationLien();
            System.out.println("Vérification du lien de confirmation de l'utilisateur: " + userConfirmation);
            if (userConfirmation.trim().equalsIgnoreCase(confirmationLien)) {
                String confirmationDateStr = utilisateur.getConfirmationLienExpirationDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime confirmationDate = LocalDateTime.parse(confirmationDateStr, formatter);
                if (LocalDateTime.now().isBefore(confirmationDate)) {
                    utilisateur.isConfirmed(true);
                    utilisateur.setConfirmationLien(null);
                    utilisateur.setConfirmationLienExpirationDate(null);
                    listeUtilisateursToJson(listeUtilisateurs);
                    System.out.println("Compte confirmé avec succès !");
                    return true;
                } else {
                    listeUtilisateurs.remove(utilisateur);
                    listeUtilisateursToJson(listeUtilisateurs);
                    System.out.println("Le lien de confirmation a expiré. Inscription annulée.");
                    return false;
                }
            }
        }
        System.out.println("Lien de confirmation invalide.");
        return false;
    }

    public boolean verifierConnexion(String email, String mdp){
        Utilisateur user = findUserByEmail(email);
        if(user != null && user.getMdp().equals(mdp) && user.getConfirmed()){
            return true;
        }
        return false;
    }

    private Utilisateur findUserByEmail(String email) {
        for (Utilisateur utilisateur : listeUtilisateurs) {
            if (utilisateur.getEmail().equals(email)) {
                return utilisateur;
            }
        }
        return null;
    }

}
