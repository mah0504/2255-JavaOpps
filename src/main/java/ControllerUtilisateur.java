import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.Normalizer;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.util.UUID;
import java.time.LocalDateTime;

public class ControllerUtilisateur extends ControllerCompte<Utilisateur>{

    private View view;
    private static final String CHEMIN_FIC_JSON = "src/main/resources/utilisateurs.json";
    protected ArrayList<Activite> activites;

    private Utilisateur utilisateur;
    private ArrayList<Utilisateur> listeUtilisateurs;
    private ControllerRobot controllerRobot;
    private ControllerFournisseur controllerFournisseur;
    private ArrayList<Fournisseur> listeFournisseurs;
    private Fournisseur fournisseurChoisi;


    /*public ControllerUtilisateur(){

        this.listeUtilisateurs = new ArrayList<>();
        getListeUtilisateursFromJson();
        this.controllerFournisseur = new ControllerFournisseur();
        this.listeFournisseurs = controllerFournisseur.getListeFournisseurs();
        this.controllerRobot= new ControllerRobot();
    }*/

    public ControllerUtilisateur(MenuCompte menuCompte, View view) {
        super(menuCompte, view);
        this.comptes = getListeUtilisateursFromJson();
        this.activites = getListeActivitesfromJson();
        this.listeFournisseurs = getListeFournisseurs();
        this.controllerRobot = new ControllerRobot();
    }

    private ArrayList<Utilisateur> getListeUtilisateursFromJson(){
        try(FileReader reader = new FileReader(CHEMIN_FIC_JSON)){
            Gson gson = new Gson();
            Type listeUtilisateurstype = new TypeToken<ArrayList<Utilisateur>>(){}.getType();
            return gson.fromJson(reader, listeUtilisateurstype);
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private ArrayList<Fournisseur> getListeFournisseurs(){
        try(FileReader reader = new FileReader("src/main/resources/fournisseurs.json")){
            Gson gson = new Gson();
            Type listeFournisseurstype = new TypeToken<ArrayList<Fournisseur>>(){}.getType();
            return gson.fromJson(reader, listeFournisseurstype);
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void listeUtilisateursToJson(ArrayList<Utilisateur> listeUtilisateurs){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter(CHEMIN_FIC_JSON)){
            gson.toJson(listeUtilisateurs, writer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*private void getListeUtilisateursFromJson(){
        try(FileReader reader = new FileReader("src/main/resources/utilisateurs.json")){
            Gson gson = new Gson();
            Type listeUtilisateurstype = new TypeToken<ArrayList<Utilisateur>>(){}.getType();
            listeUtilisateurs = gson.fromJson(reader, listeUtilisateurstype);
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/

    public ArrayList<Utilisateur> getListeUtilisateurs(){
        return listeUtilisateurs;
    }

    /*private void listeUtilisateursToJson(ArrayList<Utilisateur> listeUtilisateurs){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter("src/main/resources/utilisateurs.json")){
            gson.toJson(listeUtilisateurs, writer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/


    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void sInscrire(){
        Scanner scanner = new Scanner(System.in);
        String pseudo = super.getPseudoUnique();
        view.getNomView();
        String nom = scanner.nextLine();
        view.getPrenomView();
        String prenom = scanner.nextLine();
        String email = super.getEmailUnique();
        String motDePasse = super.getMdpValid();
        String telephone = super.getTelephoneValid();
        Utilisateur nouveauUser = new Utilisateur(pseudo,email,motDePasse,telephone,nom,prenom);

        if(isPseudoUnique(pseudo)){
            super.envoyerMailConfirmation(nouveauUser);
            super.sInscrire(nouveauUser);
            listeUtilisateursToJson((ArrayList<Utilisateur>) comptes);
            System.out.println("Inscription réussie. En attente de la confirmation de l'email .");
        } else {
            System.out.println("Échec à la création du compte.");
        }
    }

    public void confirmerInscription(){
        Scanner scanner = new Scanner(System.in);
        view.getEmailView();
        String email = scanner.nextLine();
        view.getConfirmationLien();
        String lienConfirmation = scanner.nextLine().trim();

        Utilisateur user = findUserByEmail(email);

        if (user != null) {
            String confirmationDateStr = user.getConfirmationLienExpirationDate();
            LocalDateTime confirmationDate = user.StrToDate(confirmationDateStr);

            if (LocalDateTime.now().isBefore(confirmationDate)) {
                if(user.getConfirmationLien().equals(lienConfirmation)){
                    user.isConfirmed(true);
                    user.setConfirmationLien(null);
                    user.setConfirmationLienExpirationDate(null);
                    listeUtilisateursToJson((ArrayList<Utilisateur>) comptes);
                } else {
                    view.afficherMessage("Lien de confirmation incorrect.");
                }
            } else {
                comptes.remove(user);
                listeUtilisateursToJson((ArrayList<Utilisateur>) comptes);
                view.afficherMessage("Le lien de confirmation a expiré. Le compte est supprimé");
            }
        }
        view.afficherMessage("Ce compte n'existe pas, entrez un email valide !");
    }

    /**public boolean confirmerUtilisateur(String pseudo){
        boolean confirme = true;
        for (Utilisateur utilisateur : listeUtilisateurs) {
            if (utilisateur.getPseudo().equals(pseudo)) {
                confirme = false;
                break;
            }
        }
        return confirme;
    }*/

    /*public void creerUtilisateur(String pseudo, String nom, String prenom, String email, String mdp, String telephone) {
        Utilisateur nouvelUtilisateur = new Utilisateur(pseudo, nom, prenom, email, mdp, telephone);

        if (confirmerUtilisateur(pseudo)){

            //Initialiser le lien de confirmation
            String confirmationLien = nouvelUtilisateur.getConfirmationLien();

            //Initialiser la Date d'expiration de ce lien
            String DateExpiration = nouvelUtilisateur.getConfirmationLienExpirationDate();

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

    public boolean confirmerCompte(String email,String confirmationLien) {
        Utilisateur utilisateur = findUserByEmail(email);
        if(utilisateur != null){

            //récupère la date d'expiration du lien de confirmation
            String confirmationDateStr = utilisateur.getConfirmationLienExpirationDate();

            //Convertit le String en une Date
            LocalDateTime confirmationDate = utilisateur.StrToDate(confirmationDateStr);

            if (LocalDateTime.now().isBefore(confirmationDate)) {
                utilisateur.isConfirmed(true);
                utilisateur.setConfirmationLien(null);
                utilisateur.setConfirmationLienExpirationDate(null);
                listeUtilisateursToJson(listeUtilisateurs);
                return true;
            } else {
                listeUtilisateurs.remove(utilisateur);
                listeUtilisateursToJson(listeUtilisateurs);
                System.out.println("Le lien de confirmation a expiré. Inscription annulée.");
                return false;
            }
        }else {
            System.out.println("Lien de confirmation invalide.");
            return false;
        }
    }

    public Utilisateur verifierConnexion(String email, String mdp){
        Utilisateur user = findUserByEmail(email);
        if(user != null && user.getMdp().equals(mdp) && user.getConfirmed()){
            return user;
        }
        return null;
    }*/

    /*private Utilisateur findUserByEmail(String email) {
        for (Utilisateur utilisateur : listeUtilisateurs) {
            if (utilisateur.getEmail().equals(email)) {
                return utilisateur;
            }
        }
        return null;
    }*/

    public Robot choisirRobot (Utilisateur utilisateur){
        int choix = -1;
        try {
            System.out.println( "Veuillez choisir un robot de votre Flotte par index!");

            for (int i=0 ; i< utilisateur.getListeRobots().size(); i++) {
                System.out.println( "[" + i +"]"+
                        utilisateur.getListeRobots().get(i).getId() + "\n");
            }


            Scanner scan = new Scanner(System.in);
            choix = scan.nextInt();

            // si choix valide , on return le Robot sitié à l'Index choisis pas l'Utilisateur

            //return utilisateur.getListeRobots().get(choix - 1) ;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Veuillez choisir un nombre valide parmi les options disponibles!");
        } catch (InputMismatchException e) {
            System.out.println("Veuillez entrer un nombre!");
        }catch(Exception e){
            e.printStackTrace(); // modif apre
            System.out.println("Veuillez choisir un nombre  !");
        }
        return utilisateur.getListeRobots().get(choix) ;
    }


    /**
     * Vérifie si la flotte de l'utilisateur contient au moins 1 composante.
     *
     * @return true si au moins une composante est disponible, false sinon.
     */
    public boolean composanteDispo(Utilisateur utilisateur){
        if (!utilisateur.getComposantesFlotte().isEmpty()){
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

    public ComposanteType choisirComposanteFlotte(Utilisateur utilisateur){

        if (utilisateur.getComposantesFlotte().isEmpty()) {
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



    /**
     * Permet à l'utilisateur de supprimer un robot  qu'il a choisis de sa liste de robots.

     */

    public  void supprimerRobot(Utilisateur utilisateur) {
        Scanner scanner = new Scanner(System.in);
        try {
            for (int i=0 ; i< utilisateur.getListeRobots().size(); i++) {

                System.out.println( "[" + i + "]" +"Veuillez choisir quel robot supprimer " +
                        " par index! \n " + utilisateur.getListeRobots().get(i).getId() + "\n");
            }
            int choix = scanner.nextInt();
            ArrayList<Composante> lstCompo = utilisateur.getListeRobots().get(choix).getListeComposantes();

            for (Composante c: lstCompo) {
                utilisateur.getComposantesFlotte().put(c.getNom(),c.getType());
                // ajouter les composantes du robot supprimé à l'inventaire de la flotte
            }
            utilisateur.getListeRobots().remove(choix); // enlever le robot
            utilisateur.setListeRobots(utilisateur.getListeRobots());
            System.out.println(utilisateur.getListeRobots());

            listeUtilisateursToJson((ArrayList<Utilisateur>) comptes);


        } catch (Exception e){
            e.printStackTrace(); // modif apres
        }

    }


    /**
     * Permet à l'utilisateur de choisir entre deux types d'affichages pour l'état des robots :
     * un affichage général ou un affichage complet. L'utilisateur choisit le robot pour lequel
     * il souhaite voir l'état, puis le type d'affichage souhaité.
     */
    public void afficherEtatsRobots(Utilisateur utilisateur){
        Scanner scan = new Scanner(System.in);

        try {
            System.out.println("Veuillez choisir quel type d'affichage vous voulez: \n " +
                    "[1] : Affichage Général \n [2] : Affichage Complet ");

            int choix = scan.nextInt();

            switch (choix){
                case 1:

                    Robot robotChoisi=choisirRobot(utilisateur);
                    controllerRobot.setRobot(robotChoisi);
                    controllerRobot.afficherVueGenerale(robotChoisi);

                    break;
                case 2:

                    Robot robotChoisi1=choisirRobot(utilisateur);
                    controllerRobot.setRobot(robotChoisi1);
                    controllerRobot.afficherVueComplete(robotChoisi1);

                    break;
                default:
                    System.out.println("Veuillez entrer un nombre valide !");
                    break;
            }


        }catch (Exception e){
            e.printStackTrace(); // modifier apres
        }

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

        //ArrayList<Fournisseur> Listefournisseurs =controllerFournisseur.getListeFournisseurs();

        List<FournisseurComposante> composantesTrouvees = new ArrayList<>();

        for (Fournisseur f : listeFournisseurs) {
            for (FournisseurComposante c : f.getComposantes().values()){

                if (c.getComposante().getType() == typeRecherche) {
                 composantesTrouvees.add(c);
                }
            }
        }

        System.out.println("Veuillez choisir celle que vous voulez par index, en commençant par 1 ");

        for ( int i=0 ; i<composantesTrouvees.size() ; i++){
            System.out.println(composantesTrouvees.get(i).getComposante().getNom());
        }

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
    public FournisseurComposante trouverComposanteSelonNom() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Veuillez entrer le nom de composante que vous recherchez:");
            String nom = scanner.nextLine();

            // Normaliser le nom entré
            nom = ajusterString(nom);

            // Parcourir la liste des fournisseurs et leurs composantes
            for (Fournisseur fournisseur : listeFournisseurs) {
                for (FournisseurComposante composante : fournisseur.getComposantes().values()) {
                    if (ajusterString(composante.getComposante().getNom()).equals(nom)) {
                        // Afficher les détails de la composante trouvée
                        System.out.println("Nom: " + composante.getComposante().getNom());
                        System.out.println("Description: " + composante.getComposante().getDescription());
                        System.out.println("Type: " + composante.getComposante().getType());
                        System.out.println("Prix: " + composante.getComposante().getPrix());
                        System.out.println("Quantité: " + composante.getQuantite());

                        return composante; // Retourner la composante trouvée
                    }
                }
            }

            System.out.println("Aucune composante trouvée avec le nom spécifié.");
        } catch (Exception e) {
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
                        System.out.println((i + 1) + "Nom: " + composante.getComposante().getNom());
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


    /**
     * Affiche les informations basiques d'une composante recherchée.
     *
     * @param composante La composante dont les informations doivent être affichées.
     */
//    public void afficherCompoRecherche(FournisseurComposante composante){
//        System.out.println("Le nom"+composante.getComposante().getId() + "\n" + "Le Type"
//                +composante.getComposante().getType() ) ;
//    }

    /**
     * Affiche les informations détaillées d'une composante.
     *
     * @param composante La composante dont les informations doivent être affichées.
     */
    public void afficherCompoInfo(FournisseurComposante composante){

        System.out.println("Nom : "+composante.getComposante().getNom() + "\n" + "Le Type"
                +composante.getComposante().getType() +"\n" + "Description :" +
                composante.getComposante().getDescription() +"\n Prix: " + composante.getComposante().getPrix());
    }

    public void acheterComposante(Utilisateur utilisateur){

        // ou bien utiliser trouver composante ?

        System.out.println("Quel type de composante voulez-vous acheter? ");
        FournisseurComposante compo = trouverComposanteSelonType();

        if (compo == null) {
            System.out.println("Aucune composante sélectionnée. Retour au menu.");
            return;
        }

        compo.getComposante();

        // ajout de la composante dans la flotte

        utilisateur.getComposantesFlotte().put(compo.getComposante().getNom(),
                compo.getComposante().getType());

        // comment acceder a l'inventaire du fournisseur pr voir?

        listeUtilisateursToJson((ArrayList<Utilisateur>) comptes);
        System.out.println("Composante achetée avec succès !");
        // trouver fournisseurs selon composante
        //choisir fournisseur
        // chosir la composante
        // decrementer le stock du fournisseur et lui envoyer une notification
        // ajoujter compo a la liste de composantes de l'utilisateur

    }

    public Fournisseur choisirFournisseur(){

        if (listeFournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur disponible.");
        }
        else {
        //    System.out.println("Nombre de fournisseurs disponibles : " + listeFournisseurs.size());
            for (Fournisseur c : listeFournisseurs) {
                System.out.println("Fournisseur : " + c.getNomCompagnie());
            }
            Scanner scanner=new Scanner(System.in);

            try {
                System.out.println("Veuillez en choisir 1 selon son index: ");
                int index = scanner.nextInt();
                if (index >= 0 && index < listeFournisseurs.size()) {
                    System.out.println("Vous avez choisis le fournisseur"+listeFournisseurs.get(index) );
                    return listeFournisseurs.get(index);
                } else{
                    System.out.println("Veuillez choisir une index valide !");
                }
            }

            catch (Exception e){
                System.out.println("Veuillez choisir un index approprié!");
            }

        }
        return null;
    }

    public Fournisseur trouverFournisseurSelonNom() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Veuillez entrer le nom du fournisseur que vous recherchez :");
            String nomRecherche = scanner.nextLine();
            String nomRechercheNormalise = ajusterString(nomRecherche);

            // Récupérer la liste des fournisseurs
            //ArrayList<Fournisseur> listeFournisseurs = controllerFournisseur.getListeFournisseurs();

            // Liste des fournisseurs trouvés
            List<Fournisseur> fournisseursTrouves = new ArrayList<>();

            // Parcourir la liste des fournisseurs
            for (Fournisseur f : listeFournisseurs) {
                String nomFournisseurNormalise = ajusterString(f.getNomCompagnie());
                if (nomFournisseurNormalise.contains(nomRechercheNormalise)) {
                    fournisseursTrouves.add(f);
                }
            }

            // Afficher les fournisseurs trouvés
            if (fournisseursTrouves.isEmpty()) {
                System.out.println("Aucun fournisseur ne correspond au nom recherché.");
            } else {
                System.out.println("Fournisseurs trouvés :");
                for (int i = 0; i < fournisseursTrouves.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + fournisseursTrouves.get(i).getNomCompagnie());
                }

                // Laisser l'utilisateur choisir un fournisseur
                System.out.println("Veuillez choisir un fournisseur parmi ceux-ci :");
                int choix = scanner.nextInt();

                // Vérifier si le choix est valide
                if (choix < 1 || choix > fournisseursTrouves.size()) {
                    System.out.println("Choix invalide. Veuillez essayer de nouveau.");
                } else {
                    Fournisseur fournisseurChoisi = fournisseursTrouves.get(choix - 1);
                    System.out.println("Fournisseur choisi : " + fournisseurChoisi.getNomCompagnie());
                    return fournisseurChoisi;
                }
            }

        } catch (Exception e) {
            System.out.println("Veuillez choisir un nom valide !");
            scanner.next(); // Consomme l'entrée invalide
        }
        return null;
    }

    public Fournisseur trouverFournisseurSelonType() {
        Scanner scanner = new Scanner(System.in);

        // Choisir le type de composant
        ComposanteType typeRecherche = choisirTypeComposante(); // méthode pour choisir le type

        // Récupérer la liste des fournisseurs
        //ArrayList<Fournisseur> listeFournisseurs = controllerFournisseur.getListeFournisseurs();

        // Liste des fournisseurs qui ont le type de composant recherché
        List<Fournisseur> fournisseursTrouves = new ArrayList<>();

        // Parcourir les fournisseurs et leurs composantes
        for (Fournisseur f : listeFournisseurs) {
            boolean trouve = false;
            for (FournisseurComposante c : f.getComposantes().values()) {
                if (c.getComposante().getType() == typeRecherche) {
                    trouve = true;
                    break;
                }
            }
            if (trouve) {
                fournisseursTrouves.add(f);
            }
        }

        // Afficher les fournisseurs trouvés
        System.out.println("Veuillez choisir un fournisseur parmi ceux-ci :");
        for (int i = 0; i < fournisseursTrouves.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + fournisseursTrouves.get(i).getNomCompagnie());
        }

        // Laisser l'utilisateur choisir un fournisseur
        try {
            int choix = scanner.nextInt();

            // Vérifier si le choix est valide
            if (choix < 1 || choix > fournisseursTrouves.size()) {
                System.out.println("Choix invalide. Veuillez essayer de nouveau.");
            } else {
                Fournisseur fournisseurChoisi = fournisseursTrouves.get(choix - 1);

                //System.out.println("Fournisseur choisi : " + fournisseurChoisi.getNomCompagnie());
                return fournisseurChoisi;

            }

        } catch (Exception e) {
            System.out.println("Veuillez entrer un nombre valide !");
            scanner.next();
        }
        return null;
    }



// Methode auxiliaire
    public List<Fournisseur> choisirFournisType2(ComposanteType typeRecherche) {
        // Récupérer la liste des fournisseurs
        //ArrayList<Fournisseur> listeFournisseurs = controllerFournisseur.getListeFournisseurs();

     //   System.out.println(listeFournisseurs);
        // Liste des fournisseurs trouvés
        List<Fournisseur> fournisseursTrouves = new ArrayList<>();

        for (Fournisseur f : listeFournisseurs) {
            System.out.println("Examen du fournisseur : " + f.getNomCompagnie());
            for (FournisseurComposante fc : f.getComposantes().values()) {
                if (fc.getComposante() != null && fc.getComposante().getType() == typeRecherche) {
                    System.out.println("Ajout du fournisseur : " + f.getNomCompagnie());
                    fournisseursTrouves.add(f);
                    break; // Sortir de la boucle interne dès qu'un composant correspondant est trouvé
                }
            }
        }

        // Imprimer les fournisseurs trouvés
        System.out.println("Fournisseurs trouvés :");
        for (Fournisseur fournisseur : fournisseursTrouves) {
            System.out.println(fournisseur.getNomCompagnie());
        }

        return fournisseursTrouves;


    }

    public ComposanteType choisirComposanteFlotte(ComposanteType typeRecherche, Utilisateur utilisateur) {
        if (utilisateur.getComposantesFlotte().isEmpty()) {
            System.out.println("Aucune composante disponible dans la flotte.");
            return null;
        }

        // Filtrer les composantes en fonction du type recherché
        List<Map.Entry<String, ComposanteType>> composantesDisponibles = new ArrayList<>();
        for (Map.Entry<String, ComposanteType> entry : utilisateur.getComposantesFlotte().entrySet()) {
            if (entry.getValue() == typeRecherche) {
                composantesDisponibles.add(entry);
            }
        }

        if (composantesDisponibles.isEmpty()) {
            System.out.println("Aucune composante du type " + typeRecherche + " disponible dans la flotte.");
            return null;
        }

        // Afficher les options disponibles
        System.out.println("Veuillez choisir une composante du type " + typeRecherche + ":");
        for (int i = 0; i < composantesDisponibles.size(); i++) {
            System.out.println("[" + i + "] " + composantesDisponibles.get(i).getKey());
        }

        Scanner scanner = new Scanner(System.in);
        try {
            int choix = scanner.nextInt();
            if (choix >= 0 && choix < composantesDisponibles.size()) {
                // Retourner le type de la composante choisie
                return composantesDisponibles.get(choix).getValue();
            } else {
                System.out.println("Choix invalide.");
            }
        } catch (Exception e) {
            System.out.println("Entrée invalide.");
        }

        return null;
    }


    public void enregistrerRobot(Utilisateur utilisateur) {
        Robot robot = new Robot();
        ArrayList<Composante> nouvellesComposantes = new ArrayList<>();
        robot.setListeComposantes(nouvellesComposantes);

        List<Fournisseur> lstFournissAveccpu = choisirFournisType2(ComposanteType.CPU);
        System.out.println(lstFournissAveccpu);

        if (!lstFournissAveccpu.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Veuillez choisir de quel fournisseur vous voulez acheter un robot:");

            try {
                // Impression des fournisseurs avec au moins un CPU à vendre
                for (int i = 0; i < lstFournissAveccpu.size(); i++) {
                    System.out.println("[" + i + "] " + lstFournissAveccpu.get(i).getNomCompagnie());
                }

                int choix = scanner.nextInt();

                if (choix >= 0 && choix < lstFournissAveccpu.size()) {
                    fournisseurChoisi = lstFournissAveccpu.get(choix); // Le fournisseur choisi
                }

                if (fournisseurChoisi != null) {
                    ArrayList<Composante> composantesChoisies = new ArrayList<>();

                    for (FournisseurComposante fc : fournisseurChoisi.getComposantes().values()) {
                        if (fc.getComposante().getType() == ComposanteType.CPU) {
                            composantesChoisies.add(fc.getComposante());
                            break;
                        }
                    }



                        Composante composante=new Composante();

                     //composante.setNom();
                       // composante.setType();

                        composantesChoisies.add(composante); // ajout de la 2eme compo ?

                        // pr trv compo , scnnaer dans le meu puis on l'envoie comme param?
                        // la flotte
                        for (int i = 0; i < composantesChoisies.size(); i++) {
                            robot.getListeComposantes().add(composantesChoisies.get(i));


                            robot.setListeComposantes(robot.getListeComposantes());
                        }
                        // modif le nom ,typw ....
                        // ajouter le numero de serie aussi ...
                        utilisateur.getListeRobots().add(robot);




                    } else {
                        System.out.println("Choix invalide.");
                    }


            } catch (Exception e) {
                e.printStackTrace(); // À modifier après
                // }
            }

        }
        }





    public void voirNotifs(Utilisateur utilisateur){
        if (utilisateur.getNotifis() == null) {
            utilisateur.setNotifs(new ArrayList<>());
        }
        for ( Notification n : utilisateur.getNotifis()) {
            System.out.println(n.toString());
        }
    }

    public void modifierProfilUtilisateur(Utilisateur utilisateur) {
        boolean continuer = true;
        Scanner scanner = new Scanner(System.in);
        while (continuer) {
            view.actionModifierProfilUtilisateur();
            int choix = Integer.parseInt(scanner.nextLine().trim());

            switch (choix) {
                case 0:
                    continuer = false;
                    break;
                case 1:
                    String nvPseudo = super.getPseudoUnique();
                    utilisateur.setPseudo(nvPseudo);
                    break;
                case 2:
                    view.getNomView();
                    String nvNom = scanner.nextLine();
                    utilisateur.setNom(nvNom);
                    break;
                case 3:
                    view.getPrenomView();
                    String nvPrenom = scanner.nextLine();
                    utilisateur.setPrenom(nvPrenom);
                    break;
                case 4:
                    String nvMdp = super.getMdpValid();
                    utilisateur.setMdp(nvMdp);
                    break;
                case 5:
                    String nvTelephone = super.getTelephoneValid();
                    utilisateur.setTelephone(nvTelephone);
                    break;

            }
        }

        for (int i = 0; i < comptes.size(); i++) {
            if (comptes.get(i).getEmail().equals(utilisateur.getEmail())) {
                comptes.set(i, utilisateur);
                break;
            }
        }
        listeUtilisateursToJson((ArrayList<Utilisateur>) comptes);
    }

    /*************************** Méthodes qui permettent de gérer les actvités **********************/

    /**
     * Permet de désérialiser les données du fichier activites.json
     * en une liste d'objet Activite
     *
     * source : https://www.baeldung.com/gson-list
     */
    private ArrayList<Activite> getListeActivitesfromJson(){
        try(FileReader reader = new FileReader("src/main/resources/activites.json")){
            Gson gson = new Gson();
            Type listeActivitesType = new TypeToken<ArrayList<Activite>>(){}.getType();
            return gson.fromJson(reader, listeActivitesType);
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void listeActiviteToJson(ArrayList<Activite> listeActivites){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter("src/main/resources/activites.json")){
            gson.toJson(listeActivites, writer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Permet d'afficher la liste de toutes les activités qui sont disponibles et
     * auxquelles l'utilisateur n'est pas inscrit
     */
    private void afficherlisteActivites(Utilisateur utilisateur){
        System.out.println("Choisissez une activité : ");
        //vérifier si l'utilisateur n'est pas déjà inscrit à l'activité
        for(Activite activite : activites){
            if(!utilisateur.getActivites().containsKey(activite.getNom())){
                System.out.println(activite.toString());
            }
        }
    }

    /**
     * Permet d'afficher toutes les activités auxquelles l'utilisateur est inscrit
     */
    private void afficherActivitesUtilisateur(Utilisateur utilisateur){
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
        for(Activite activite : activites){
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
    private void sInscrireActivite(String nomActivite, Utilisateur utilisateur){
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
    private void desinscrireActivite(String nomActivite, Utilisateur utilisateur){
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
    public void gererActivites(Utilisateur user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez-vous vous inscrire ou vous désinscrire d'une activité ?");
        System.out.println("[1] : Inscription à une activité");
        System.out.println("[2] : Désincription à une activité");
        String choix = scanner.nextLine().trim().toLowerCase();

        switch (choix) {
            case "1":
                afficherlisteActivites(user);
                System.out.println("Choisissez l'activité auquelle vous voulez vous inscrire :");
                String nomActivite1 = scanner.nextLine().trim();
                sInscrireActivite(nomActivite1, user);

                break;

            case "2":
                afficherActivitesUtilisateur(user);
                System.out.println("Choisissez l'activité auquelle vous voulez vous désinscrire :");
                String nomActivite2 = scanner.nextLine().trim();
                desinscrireActivite(nomActivite2, user);
                break;

            default:
                System.out.println("Choix invalide.");
        }

    }


}
