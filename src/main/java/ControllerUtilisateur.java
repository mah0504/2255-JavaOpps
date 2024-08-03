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
    private ArrayList<Fournisseur> listeFournisseurs ;

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


    /**
     * Vérifie si la flotte de l'utilisateur contient au moins 1 composante.
     *
     * @return true si au moins une composante est disponible, false sinon.
     */
    public boolean composanteDispo(){
        if (utilisateur.getComposantesFlotte().size() >0){
            return true;
        }
        return false;
    }


    /**
     * Permet à l'utilisateur de choisir une composante parmi celles disponibles dans sa flotte.
     *
     * Cette méthode affiche toutes les composantes disponibles dans la flotte de l'utilisateur et
     * permet à l'utilisateur d'en choisir une en entrant son numéro correspondant.
     *
     * @return Le type de la composante choisie par l'utilisateur, ou null
     * si aucune composante n'a été choisie.
     */

    public ComposanteType choisirComposanteFlotte(){

        if (utilisateur.getComposantesFlotte().keySet().isEmpty()) {
            System.out.println("Aucune composante disponible dans la flotte.");
            return null;
        }

        for ( String s: utilisateur.getComposantesFlotte().keySet() ){
            System.out.println("Veuillez choisir un composante de la flotte :");
            System.out.println(s);
        }
        Scanner scanner= new Scanner(System.in);
        try{

            int choix = scanner.nextInt();
            return utilisateur.getComposantesFlotte().get(choix - 1);
        }catch (Exception e){
            e.printStackTrace(); // modifier apres
        }
        return null;
    }

    // a modifier ofc
    public void enregistrerRobot(Robot robot) {
        if (!composanteDispo()) {
            System.out.println("Composants nécessaires non disponibles.");
            return;
        }


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
                utilisateur.getComposantesFlotte().put(c.getNom(), c.getType());
                // ajouter les composantes du robot supprimé à l'inventaire de la flotte
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


    /**
     * Permet à l'utilisateur de choisir un type de composante parmi les options disponibles.

     *
     * @return Le type de composante choisi par l'utilisateur.
     */
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

    /**
     * Trouve et affiche les composantes d'un certain type parmi les fournisseurs disponibles.
     *
     * @return  La composante choisie par l'utilisateur, ou null si une erreur se produit.
     */
    public FournisseurComposante trouverComposanteSelonType(){
        ComposanteType typeRecherche = choisirTypeComposante() ; // la compo choisie par l'utili
        ArrayList<Fournisseur> Listefournisseurs =controllerFournisseur. getFournisseurs();
        List<FournisseurComposante> composantesTrouvees = new ArrayList<>();

        for (Fournisseur f : Listefournisseurs) {
            for (FournisseurComposante c : f.getComposantes().values()){

                if (c.getComposante().getType() == typeRecherche) {
                    System.out.println("Nom du fournisseur: " + f.getNomCompagnie() + "\n Id de la composante" +
                            c.getComposante().getId()  );
                    composantesTrouvees.add(c);
                }
            }
        }
        System.out.println("Veuillez choisir celle que vous voulez ");

        Scanner scanner = new Scanner(System.in);
        try {
            int choix = scanner.nextInt();

            return composantesTrouvees.get( choix-1);
            // elaborer ??????

        }catch (Exception e){
            System.out.println("Veuillez choisir un composante valide !");
        }
        return null ;
    }


    /**
     * Normalise  une chaîne de caractères en NFD (Normalization Form D),
     * supprimant les accents et en la mettant en minuscules.
     *
     * @param input La chaîne de caractères à normaliser. Peut être {@code null}.
     * @return La chaîne normalisée, en minuscules et sans accents.
     * Si l'entrée est {@code null}, retourne une chaîne vide.
     */
    private static String ajusterString(String input) {
        if (input == null) {
            return "";
        }
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "").toLowerCase();
    }


    // selon nom
    public FournisseurComposante trouverComposanteSelonNom(){
        Scanner scanner = new Scanner(System.in);
        try{

            System.out.println("Veuilez entrer le nom de composante que vous recherchez");
            String nom = scanner.nextLine();
            // on va dans le json de fournisseurs
            // on accede a la liste des composantes de chacun
            // si l'un d'eux a la string recherchee normalisee on imprime ses info
            boolean trouve = false;

//
            for (Fournisseur fournisseur : listeFournisseurs) {
                // parcourir la liste des composantes de chaque fournisseur

                for (FournisseurComposante composante : fournisseur.getComposantes().values()) {
                    if (ajusterString(composante.getComposante().getNom()).equals(ajusterString(nom))) {
//
//                        System.out.println("Nom du fournisseur: " + fournisseur.getNomCompagnie());
//                        System.out.println("ID de la composante: " + composante.getComposante().getId());
                        // Afficher d'autres informations si nécessaire
                        trouve = true;
                        return composante; // return la composante

                    }
                }
            }
            if (!trouve) {
                System.out.println("Aucune composante trouvée avec le nom spécifié.");
            }

        }catch (Exception e ){
            System.out.println("Veuillez entrer un nom de composante valide !");
        }
        return null;
    }





    public FournisseurComposante trouverComposanteSelonFournisseur(){
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Veuillez entrer le nom du fournisseur que vous recherchez");
            String nomFournisseur = scanner.nextLine();
            boolean trouve = false;

            String nomNormalise = ajusterString(nomFournisseur);

            for (Fournisseur fournisseur : listeFournisseurs) {

                // recherche du fournisseur dans notre liste de fournisseurs
                if (ajusterString(fournisseur.getNomCompagnie()).equals(nomNormalise)) {

                    trouve = true;
                    System.out.println("Fournisseur trouvé : " + fournisseur.getNomCompagnie());

                    System.out.println("Composantes disponibles :");
                    List<FournisseurComposante> composantes = new ArrayList<>(fournisseur.getComposantes().values());

                    for (int i = 0; i < composantes.size(); i++) {
                        FournisseurComposante composante = composantes.get(i);
                        System.out.println((i + 1) + ": ID: " + composante.getComposante().getId() + ", Nom: " + composante.getComposante().getNom());
                    }

                    System.out.println("Veuillez choisir une composante (entrer le numéro correspondant) :");
                    int choix = scanner.nextInt();

                    if (choix > 0 && choix <= composantes.size()) {
                        return composantes.get(choix - 1);
                    } else {
                        System.out.println("Numéro invalide. Aucune composante sélectionnée.");
                        return null;
                    }
                }
            }
            if (!trouve) {
                System.out.println("Aucun fournisseur trouvé avec le nom spécifié.");
            }

        } catch (Exception e) {
            System.out.println("Une erreur s'est produite. Veuillez entrer un nom de fournisseur valide.");
        }
        return null;
    }
            // ouvrir liste des fournisseurs
            // chercher le fournisseur , puis imprimer sa liste de composantes


    public void afficherCompoRecherche(FournisseurComposante composante){
        System.out.println("Le nom"+composante.getComposante().getId() + "\n" + "Le Type"
                +composante.getComposante().getType() ) ;
    }


    public void afficherCompoInfo(FournisseurComposante composante){

        System.out.println("L'Id "+composante.getComposante().getId() + "\n" + "Le Type"
                +composante.getComposante().getType() +"\n" + "Description :" +
                composante.getComposante().getDescription() +"\n Prix: " + composante.getComposante().getPrix()
        + "\n Nom: " + composante.getComposante().getNom()); ;
    }





    public void trouverFournisseur(){}

    public void voirNotifs(){}

}

