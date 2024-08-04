import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ControllerFournisseur{

    private ArrayList<Fournisseur> listeFournisseurs;
    private Fournisseur fournisseur;


    public ControllerFournisseur() {
        listeFournisseurs = new ArrayList<>();
        getListeFournisseursFromJson();
    }

    private void getListeFournisseursFromJson(){
        try(FileReader reader = new FileReader("src/main/resources/fournisseurs.json")){
            Gson gson = new Gson();
            Type listeFournisseurstype = new TypeToken<ArrayList<Fournisseur>>(){}.getType();
            listeFournisseurs = gson.fromJson(reader, listeFournisseurstype);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }


     public void modifierProfil(){}

    public ArrayList<Fournisseur> getListeFournisseurs(){
        try (FileReader reader = new FileReader("src/main/resources/fournisseurs.json")) {
            Gson gson = new Gson();
            Type listeFournisseursType = new TypeToken<ArrayList<Fournisseur>>() {}.getType();
            return gson.fromJson(reader, listeFournisseursType);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void listeFournisseursToJson(ArrayList<Fournisseur> listeFournisseurs){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter("src/main/resources/fournisseurs.json")){
            gson.toJson(listeFournisseurs, writer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean confirmerFournisseur(String pseudo){
        boolean confirme = true;
        for (Fournisseur fournisseur : listeFournisseurs) {
            if (fournisseur.getPseudo().equals(pseudo)) {
                confirme = false;
                break;
            }
        }
        return confirme;
    }

    public void creerFournisseur(String pseudo, String nomCompagnie, String email, String mdp, String telephone) {
        Fournisseur nouveauFournisseur = new Fournisseur(pseudo, nomCompagnie, email, mdp, telephone);

        if (confirmerFournisseur(pseudo)) {

            //Initialiser le lien de confirmation
            String confirmationLien = nouveauFournisseur.getConfirmationLien();

            //Initialiser la Date d'expiration de ce lien
            String DateExpiration = fournisseur.getConfirmationLienExpirationDate();

            //Assigner les nouvelles valeurs
            nouveauFournisseur.setConfirmationLien(confirmationLien);
            nouveauFournisseur.setConfirmationLienExpirationDate(DateExpiration);

            listeFournisseurs.add(nouveauFournisseur);
            listeFournisseursToJson(listeFournisseurs);

            //Envoyer le mail de confirmation
            envoyerEmailConfirmation(nouveauFournisseur.getEmail(), confirmationLien);

            System.out.println("Inscription réussie ! En attente de la confirmation de mail.");

        } else {
            System.out.println("Échec à la création du compte.");
        }
    }

    public boolean isPseudoUnique(String pseudo){
        for(Fournisseur fournisseur : listeFournisseurs){
            if(fournisseur.getPseudo().equals(pseudo)){
                return false;
            }
        }
        return true;
    }

    public boolean isEmailUnique(String email){
        for(Fournisseur fournisseur : listeFournisseurs){
            if(fournisseur.getEmail().equals(email)){
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

    public boolean confirmerCompte(String email,String confirmationLien) {
        Fournisseur fournisseur = findSupplierByEmail(email);
        if(fournisseur != null){
            //récupère la date d'expiration du lien de confirmation
            String confirmationDateStr = fournisseur.getConfirmationLienExpirationDate();

            //Convertit le String en une Date
            LocalDateTime confirmationDate = fournisseur.StrToDate(confirmationDateStr);

            if (LocalDateTime.now().isBefore(confirmationDate)) {
                fournisseur.isConfirmed(true);
                fournisseur.setConfirmationLien(null);
                fournisseur.setConfirmationLienExpirationDate(null);
                listeFournisseursToJson(listeFournisseurs);
                return true;
            } else {
                listeFournisseurs.remove(fournisseur);
                listeFournisseursToJson(listeFournisseurs);
                System.out.println("Le lien de confirmation a expiré. Inscription annulée.");
                return false;
            }
        }else {
            System.out.println("Lien de confirmation invalide.");
            return false;
        }
    }

    public Fournisseur verifierConnexion(String email, String mdp){
        Fournisseur supplier = findSupplierByEmail(email);
        if(supplier != null && supplier.getMdp().equals(mdp) && supplier.getConfirmed()){
            return supplier;
        }
        return null;
    }

    private Fournisseur findSupplierByEmail(String email) {
        for (Fournisseur fournisseur : listeFournisseurs) {
            if (fournisseur.getEmail().equals(email)) {
                return fournisseur;
            }
        }
        return null;
    }

    public void enregistrerComposante(){
    }

    public void supprimerComposante(){}

    public void modifierComposante( ){}
}
