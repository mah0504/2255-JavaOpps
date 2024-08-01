import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class ControllerUtilisateur extends ControllerCompte{

    private Utilisateur utilisateur;
    private MenuUtilisateur utilisateurView;
    private ControllerRobot controllerRobot;
    private ArrayList<Activite> listeActivites;




    /**
     * Retourne la liste des utilisateurs en désérialisant le contenu du fichier JSON.
     * @return La liste des utilisateurs mise à jour ou null en cas d'erreur.
     */

    private List<Utilisateur> majListeUtilisateurs() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("src/main/resources/utilisateurs.json")) {
            Type userListType = new TypeToken<List<Utilisateur>>() {}.getType();
            //List<Utilisateur> utilisateurs = gson.fromJson(reader, userListType);
            return gson.fromJson(reader, userListType);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // sinon?
    }





    public ControllerUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        getListeActivitesfromJson();
    }

    @Override
    public void creerCompte() {

    }

    @Override
    public void seConnecter() {

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

    /**
     *
     */
    // a modifier ofc
    public void enregistrerRobot(Robot robot) {
      //  utilisateur.getListeRobots().add(robot);

    }

    public  void supprimerRobot() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir quel robot supprimer: ");
        for ( Robot robot : utilisateur.getListeRobots()) {
            System.out.println(robot);

        }
    }

    // source : https://www.baeldung.com/gson-list

    /**
     * permet de désérialiser les données du fichier activites.json
     * en une liste d'objet Activite
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

    /**
     * permet d'afficher la liste de toutes les activités qui sont disponibles et
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
     * Permet d'afficher toutes les activités auxqueles l'utilisateur est inscrit
     */
    private void afficherActivitesUtilisateur(){
        System.out.println("Vos activités : ");
        for(Map.Entry<String, StatutActivite> entry : utilisateur.getActivites().entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }


    private boolean isAvailable(String nomActivite){
        for(Activite activite : listeActivites){
            if(activite.getNom().equals(nomActivite)){
                return true;
            }
        }
        return false;
    }

    private void sInscrireActivite(String nomActivite){
        if (isAvailable(nomActivite)){
            utilisateur.ajouterActivite(nomActivite, StatutActivite.NON_DEBUTEE);
            System.out.println("Vous êtes inscrit  l'activité : " + nomActivite);
        } else {
            System.out.println("Cette activité n'existe pas ou est invalide") ;
        }
    }
    private void desinscrireActivite(String nomActivite){
        if(utilisateur.getActivites().containsKey(nomActivite)){
            utilisateur.supprimerActivite(nomActivite);
            System.out.println("Vous êtes désincrit de l'activité : " + nomActivite);
        }else {
            System.out.println("Vous n'êtes pas inscrit à cette activité ");
        }
    }

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





}
