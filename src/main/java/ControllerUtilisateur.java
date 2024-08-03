import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.Normalizer;
import java.util.*;

public class ControllerUtilisateur{
    private ControllerFournisseur controllerFournisseur; // temporaire
    private Utilisateur utilisateur;
    private MenuUtilisateur utilisateurView;
    private ControllerCompte controllerCompte;

    //à revoir
    private ControllerRobot controllerRobot;

    private ArrayList<Utilisateur> listeUtilisateurs;
    private ArrayList<Activite> listeActivites;


    public ControllerUtilisateur(Utilisateur utilisateur, MenuUtilisateur utilisateurView) {
        this.utilisateur = utilisateur;
        this.utilisateurView = utilisateurView;
        this.listeUtilisateurs = new ArrayList<>();
        getListeUtilisateursfromJson();
        getListeActivitesfromJson();
    }

    /**
     * Permet de désérialiser les données du fichier utilisateurs.json
     * en une liste d'objet Utilisateur
     *
     * source : https://www.baeldung.com/gson-list
     */
    private void getListeUtilisateursfromJson(){
        try(FileReader reader = new FileReader("src/main/resources/utilisateurs.json")){
            Gson gson = new Gson();
            Type listeUtilisteursType = new TypeToken<ArrayList<Utilisateur>>(){}.getType();
            listeUtilisateurs = gson.fromJson(reader, listeUtilisteursType);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void UtilisateurToJson(ArrayList<Utilisateur> listeUtilisateurs) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter("src/main/resources/utilisateurs.json")){
            gson.toJson(this.listeUtilisateurs, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Utilisateur> getListeUtilisateurs() {

        return listeUtilisateurs;
    }


    /*************************** Méthodes qui permettent de gérer les actvités **********************/

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

    public void ActiviteToJson(ArrayList<Activite> listeActivites){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter("src/main/resources/activites.json")){
            gson.toJson(listeActivites, writer);
        }catch(IOException e){
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
     * Vérifie si une activité est disponible et/ou existe par recherche par nom
     *
     * @param nomActivite le nom de l'activité à vérifier
     *
     * @return {@code true} si l'activité est diponible, {@code false} sinon
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
     * Permet de s'inscrire à une activité à laquelle on n'est pas déjà inscrit.
     * Initilaise le statut de cette activité à NON_DEBUTEE
     * Met à jour le dictionnaire des activitées de l'utilisateur
     *
     * @param nomActivite le nom de l'activité à laquelle on souhaite s'inscrire
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
     * Permet de se désinscrire d'une activité à laquelle on est déjà inscrit.
     * Met à jour le dictionnaire des activitées de l'utilisateur
     *
     * @param nomActivite le nom de l'activité à laquelle on souhaite se désinscrire
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
     * Permet de gérer la logique derrière l'action de gérer les activités
     *
     */
    public void gererActivites(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez-vous vous inscrire ou vous désinscrire d'une activité ?");
        System.out.println("1 : Inscription à une activité");
        System.out.println("2 : Désincription à une activité");
        String choix = scanner.nextLine().trim().toLowerCase();

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

            default:
                System.out.println("Choix invalide.");
        }

    }

    /**
     * Permet à l'utilisateur de choisir un robot de sa flotte par index.
     * Affiche les robots disponibles avec leur index et attend une saisie utilisateur pour choisir un robot.
     *
     * @return Le robot choisi par l'utilisateur ou null si une erreur survient.
     */

    public Robot choisirRobot (){
        try {
            for (int i=0 ; i< utilisateur.getListeRobots().size(); i++) {

                System.out.println( "[" + i + "]" +"Veuillez choisir " +
                        "un robot de votre Flotte par index! \n "
                        + utilisateur.getListeRobots().get(i).getId() + "\n");
            }


            Scanner scan = new Scanner(System.in);
            int choix = scan.nextInt();

            // si choix valide , on return le Robot sitié à l'Index choisis pas l'Utilisateur
            return utilisateur.getListeRobots().get(choix - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Veuillez choisir un nombre valide parmi les options disponibles!");
        } catch (InputMismatchException e) {
            System.out.println("Veuillez entrer un nombre!");
        }catch(Exception e){
            e.printStackTrace(); // modif apre
            System.out.println("Veuillez choisir un nombre  !");
        }
        return null;
    }


    // a modifier ofc
    public void enregistrerRobot(Robot robot) {
      //  choisir fournisseur
        //acheter composantes , CPU + compo
        // les ajouter
        //

    }

    /**
     * Permet à l'utilisateur de supprimer un robot  qu'il a choisis de sa liste de robots.

     */

    public  void supprimerRobot() {
        Scanner scanner = new Scanner(System.in);
        try {
            for (int i=0 ; i< utilisateur.getListeRobots().size(); i++) {

                System.out.println( "[" + i + "]" +"Veuillez choisir quel robot supprimer " +
                        " par index! \n " + utilisateur.getListeRobots().get(i).getId() + "\n");
            }
            int choix = scanner.nextInt();
            ArrayList<Composante> lstCompo = utilisateur.getListeRobots().get(choix).getListeComposantes();

            for (Composante c: lstCompo) {
                utilisateur.getComposantesFlotte().put(c.getNom(), c.getType()); // ajouter les composantes du robot supprimé
                // à l'inventaire de la flotte
            }

            utilisateur.getListeRobots().remove(choix); // enlever le robot

        } catch (Exception e){
            e.printStackTrace(); // modif apres
        }

    }


    /**
     * Permet à l'utilisateur de choisir entre deux types d'affichages pour l'état des robots :
     * un affichage général ou un affichage complet. L'utilisateur choisit le robot pour lequel
     * il souhaite voir l'état, puis le type d'affichage souhaité.
     */
    public void afficherEtatsRobots(){
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Veuillez choisir quel type d'affichage vous voulez: \n " +
                    "[1] : Affichage Général \n [2] : Affichage Complet ");

            int choix = scan.nextInt();

            switch (choix){
                case 1:
                    controllerRobot.afficherVueGenerale(choisirRobot());
                    break;
                case 2:
                    controllerRobot.afficherVueComplete(choisirRobot());
                    break;
                default:
                    System.out.println("Veuillez entrer un nombre valide !");
                    break;
            }


        }catch (Exception e){
            e.printStackTrace(); // modifier apres
        }


        // sinon afficher etat

    }



    public ComposanteType choisirTypeComposante() {
        Scanner scanner = new Scanner(System.in);
        ComposanteType[] types = ComposanteType.values();
        int choix = -1;

        try {
            System.out.println("Choisir type composante:");
            for (int i = 0; i < types.length; i++) {
                System.out.println(i + ": " + types[i]);
            }

            while (choix < 0 || choix >= types.length) {
                System.out.print("Entrez le numéro de la composante choisie: ");
                try {
                    choix = scanner.nextInt();
                    if (choix < 0 || choix >= types.length) {
                        System.out.println("Numéro invalide. Veuillez entrer un nombre entre 0 et " + (types.length - 1));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                }
            }
        } catch (Exception e) {
            System.out.println("Une erreur inattendue s'est produite. Veuillez réessayer.");
        }

        return types[choix];
    }


    public void trouverComposanteSelonType(){
        ComposanteType typeRecherche = choisirTypeComposante() ; // la compo choisie par l'utili
        ArrayList<Fournisseur> Listefournisseurs =controllerFournisseur. getFournisseurs();
        for (Fournisseur f : Listefournisseurs) {
            for (FournisseurComposante c : f.getComposantes().values()){

                if (c.getComposante().getType() == typeRecherche) {
                    System.out.println("Nom du fournisseur: " + f.getNomCompagnie() + "\n Id de la composante" +
                            c.getComposante().getId()  );

                }
            }
        }
    }
   // selon nom

    public void trouverComposanteSelonNom(){}

    public void trouverComposanteSelonFournisseur(){}


    public void trouverFournisseur(){}

    public void voirNotifs(){}

}

