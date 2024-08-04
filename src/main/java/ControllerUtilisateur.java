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

public class ControllerUtilisateur{

    private Utilisateur utilisateur;
    private ArrayList<Utilisateur> listeUtilisateurs;
    private ControllerRobot controllerRobot;
    private ControllerFournisseur controllerFournisseur;
    private ArrayList<Fournisseur> listeFournisseurs;


    public ControllerUtilisateur(){

        this.listeUtilisateurs = new ArrayList<>();
        getListeUtilisateursFromJson();
     //   this.listeFournisseurs= new ArrayList<>();
        this.controllerFournisseur = new ControllerFournisseur();
        this.listeFournisseurs = controllerFournisseur.getListeFournisseurs();


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



    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
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

    public boolean confirmerCompte(String email,String confirmationLien) {
        Utilisateur utilisateur = findUserByEmail(email);
        if(utilisateur != null){
            String confirmationDateStr = utilisateur.getConfirmationLienExpirationDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime confirmationDate = LocalDateTime.parse(confirmationDateStr, formatter);

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
    }


    private Utilisateur findUserByEmail(String email) {
        for (Utilisateur utilisateur : listeUtilisateurs) {
            if (utilisateur.getEmail().equals(email)) {
                return utilisateur;
            }
        }
        return null;
    }

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

    // a modifier ofc / continuer
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
        ArrayList<Fournisseur> Listefournisseurs =controllerFournisseur. getListeFournisseurs();
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


    /**
     * Affiche les informations basiques d'une composante recherchée.
     *
     * @param composante La composante dont les informations doivent être affichées.
     */
    public void afficherCompoRecherche(FournisseurComposante composante){
        System.out.println("Le nom"+composante.getComposante().getId() + "\n" + "Le Type"
                +composante.getComposante().getType() ) ;
    }


    /**
     * Affiche les informations détaillées d'une composante.
     *
     * @param composante La composante dont les informations doivent être affichées.
     */
    public void afficherCompoInfo(FournisseurComposante composante){

        System.out.println("L'Id "+composante.getComposante().getId() + "\n" + "Le Type"
                +composante.getComposante().getType() +"\n" + "Description :" +
                composante.getComposante().getDescription() +"\n Prix: " + composante.getComposante().getPrix()
                + "\n Nom: " + composante.getComposante().getNom()); ;
    }



    public void acheterComposante(){
        ComposanteType compoChoisie =  choisirTypeComposante();
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
            System.out.println("Nombre de fournisseurs disponibles : " + listeFournisseurs.size());
            for (Fournisseur c : listeFournisseurs) {
                System.out.println("Fournisseur : " + c);
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
    public void trouverFournisseur(){}

    public void voirNotifs(){}


}
