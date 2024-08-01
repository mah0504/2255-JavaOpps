import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class ControllerUtilisateur extends ControllerCompte{

    private Utilisateur utilisateur;
    private MenuUtilisateur utilisateurView;

    private ArrayList<Utilisateur> listeUtilisateurs;
    private ArrayList<Activite> listeActivites;

    /**
     * Une nouvelle instance de la classe ControllerUtilisateur
     *
     * @param utilisateur Objet Utilisateur sur lequel on applique les actions prévues à l'utilisateur
     */
    public ControllerUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        getListeUtilisateursfromJson();
        getListeActivitesfromJson();
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public void creerCompte() {

    }

    @Override
    public void seConnecter() {

    }


    public void enregistrerRobot() {
       // utilisateur.getRobots().add(robot);

    }

    private void getListeUtilisateursfromJson(){
        try(FileReader reader = new FileReader("src/main/resources/utilisateurs.json")){
            Gson gson = new Gson();
            Type listeUtilisateurstype = new TypeToken<ArrayList<Utilisateur>>(){}.getType();
            listeUtilisateurs = gson.fromJson(reader, listeUtilisateurstype);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Utilisateur> getListeUtilisateurs() {
        return listeUtilisateurs;
    }

    /**
     * Permet de désérialiser les données du fichier activites.json
     * en une liste d'objet Activite
     *
     * source : https://www.baeldung.com/gson-list
     */
    private void getListeActivitesfromJson(){
        try(FileReader reader = new FileReader("src/main/resources/activites.json")){
            Gson gson = new Gson();
            Type listeActivitesType = new TypeToken<ArrayList<Activite>>(){}.getType();
            listeActivites = gson.fromJson(reader, listeActivitesType);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void toJson(ArrayList<Utilisateur> listeUtilisateurs){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter("src/main/resources/utilisateurs.json")){
            gson.toJson(listeUtilisateurs, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet d'afficher la liste de toutes les activités qui sont disponibles et
     * auxquelles l'utilisateur n'est pas inscrit
     */
    private void afficherlisteActivites(){
        System.out.println("Choisissez une activité : ");
        //vérifier si l'utilisateur n'est pas déjà inscrit à l'activité
        for(Activite activite : listeActivites){
            if(!utilisateur.getActivites().containsKey(activite.getNom())){
                System.out.println(activite.toString());
            }
        }
    }

    /**
     * Permet d'afficher toutes les activités auxquelles l'utilisateur est inscrit
     */
    private void afficherActivitesUtilisateur(){
        System.out.println("Vos activités : ");
        for(Map.Entry<String, StatutActivite> entry : utilisateur.getActivites().entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    /**
     * Vérifie si l'activité existe comme choix à faire ou si
     * le nom récupéré est correcte
     *
     * @param nomActivite le nom de l'activité
     * @return {@code true} si elle existe, {@code false} sinon
     */
    private boolean isAvailable(String nomActivite){
        for(Activite activite : listeActivites){
            if(activite.getNom().equals(nomActivite)){
                return true;
            }
        }
        return false;
    }

    /**
     * Permet d'ajouter une activité dans le dictionnaire des acticités dd'un utilisateur
     * Elle initialise le statut de chaque activité à NON_DEBUTEE
     *
     * @param nomActivite le nom de l'activité à laquelle l'utilisateur s'inscrit
     */
    private void sInscrireActivite(String nomActivite){
        if (isAvailable(nomActivite)){
            utilisateur.ajouterActivite(nomActivite, StatutActivite.NON_DEBUTEE);
            System.out.println("Vous êtes inscrit  l'activité : " + nomActivite);
        } else {
            System.out.println("Cette activité n'existe pas ou est invalide") ;
        }
    }

    /**
     * Permet de supprimer une activité au dictionnaire des activités à un utilisateur
     *
     * @param nomActivite le nom de l'activité à laquelle l'utilisateur se désinscrit
     */
    private void desinscrireActivite(String nomActivite){
        if(utilisateur.getActivites().containsKey(nomActivite)){
            utilisateur.supprimerActivite(nomActivite);
            System.out.println("Vous êtes désincrit de l'activité : " + nomActivite);
        }else {
            System.out.println("Vous n'êtes pas inscrit à cette activité ");
        }
    }

    /**
     * Permet de gérer l'action à faire sur les activités.
     *
     * Choix 1 : L'utilisateur peut s'inscrire à une activité à partir de toutes les activités disponibles
     * Choix 2 : L'utilisateur peut se désincrire d'une activité à laquelle il est inscrit
     *
     */
    public void gererActivites(){
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        while(continuer){
            System.out.println("Voulez-vous vous inscrire ou vous désinscrire d'une activité ?");
            System.out.println("1 : Inscription à une activité");
            System.out.println("2 : Désincription à une activité");
            System.out.println("3 : Retour au Menu principal");
            String choix = scanner.nextLine().trim();

            switch (choix) {
                case "1":
                    afficherlisteActivites();
                    System.out.println("Choisissez l'activité auquelle vous voulez vous inscrire :");
                    String nomActivite1 = scanner.nextLine().trim();
                    sInscrireActivite(nomActivite1);
                    break;

                case "2":
                    afficherActivitesUtilisateur();
                    System.out.println("Choisissez l'activité auquelle vous voulez vous désinscrire :");
                    String nomActivite2 = scanner.nextLine().trim();
                    desinscrireActivite(nomActivite2);
                    break;

                case "3":
                    continuer = false;
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        }

    }

}
