import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControllerFournisseur extends ControllerCompte<Fournisseur>{

    private static final String CHEMIN_FIC_JSON = "src/main/resources/fournisseurs.json";

    public ControllerFournisseur(MenuCompte menuCompte, View view) {
        super(menuCompte, view);
        this.comptes = getListeFournisseursFromJson();
    }

    /**
     * Permet de désérialiser les données du fichier fournisseurs.json
     * en une liste d'objet Fournisseur
     *
     * source : https://www.baeldung.com/gson-list
     *
     * @return liste de Fournisseurs
     * @throws Exception si une erreur survient lors de la lecture du fichier
     */
    private ArrayList<Fournisseur> getListeFournisseursFromJson(){
        try(FileReader reader = new FileReader(CHEMIN_FIC_JSON)){
            Gson gson = new Gson();
            Type listeFournisseurstype = new TypeToken<ArrayList<Fournisseur>>(){}.getType();
            return gson.fromJson(reader, listeFournisseurstype);
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Récupère la liste des fournisseurs
     * @return liste des Fournisseurs
     */
    public ArrayList<Fournisseur> getListeFournisseurs(){
        return comptes;
    }

    /**
     * Convertit une liste d'objet {@link Fournisseur} en un fichier JSON
     * en la sérialisant
     *
     * @param listeFournisseurs liste des fournisseurs à sérialiser
     * @throws Exception si une erreur survient lors de l'écriture du fichier
     */
    private void listeFournisseursToJson(ArrayList<Fournisseur> listeFournisseurs){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter(CHEMIN_FIC_JSON)){
            gson.toJson(listeFournisseurs, writer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Permet de s'inscrire en tant que Fournisseur
     * On récupère les variables d'instances pour créer un objet Forurnisseur
     * On envoie le mail de confirmation et on met à jour le Json
     */
    public void sInscrire(){
        Scanner scanner = new Scanner(System.in);
        String pseudo = super.getPseudoUnique();
        view.getCompagnieView();
        String compagnie = scanner.nextLine();
        String email = super.getEmailUnique();
        String motDePasse = super.getMdpValid();
        String telephone = super.getTelephoneValid();

        Fournisseur nouveauSupplier = new Fournisseur(pseudo, email, motDePasse,telephone,compagnie);

        if(isPseudoUnique(pseudo)){
            super.envoyerMailConfirmation(nouveauSupplier);
            super.sInscrire(nouveauSupplier);
            listeFournisseursToJson((ArrayList<Fournisseur>) comptes);
            System.out.println("Inscription réussie. En attente de la confirmation de l'email .");
        } else {
            System.out.println("Échec à la création du compte.");
        }
    }

    /**
     * Permet de confirmer l'inscription par mail de confirmation
     *
     */
    public void confirmerInscription(){

        Scanner scanner = new Scanner(System.in);
        view.getEmailView();
        String email = scanner.nextLine();
        view.getConfirmationLien();
        String lienConfirmation = scanner.nextLine();

        Fournisseur supplier = findUserByEmail(email);

        if (supplier != null) {
            String confirmationDateStr = supplier.getConfirmationLienExpirationDate();
            LocalDateTime confirmationDate = supplier.StrToDate(confirmationDateStr);

            if (LocalDateTime.now().isBefore(confirmationDate)) {
                if(supplier.getConfirmationLien().equals(lienConfirmation)){
                    supplier.isConfirmed(true);
                    supplier.setConfirmationLien(null);
                    supplier.setConfirmationLienExpirationDate(null);
                    listeFournisseursToJson((ArrayList<Fournisseur>) comptes);
                } else {
                    view.afficherMessage("Lien de confirmation incorrect.");
                }
            } else {
                comptes.remove(supplier);
                listeFournisseursToJson((ArrayList<Fournisseur>) comptes);
                view.afficherMessage("Le lien de confirmation a expiré. Le compte est supprimé");
            }
        }
        view.afficherMessage("Ce compte n'existe pas, entrez un email valide !");
    }

    /**
     * Permet de modifier le profil d'un Fournisseur
     * On considère que l'email ne peut pas être modifié
     *
     * @param fournisseur le compte où on effectue les changements
     */
    public void modifierProfilFournissuer(Fournisseur fournisseur) {
        boolean continuer = true;
        Scanner scanner = new Scanner(System.in);
        while (continuer) {
            view.actionModifierProfilFournisseur();
            int choix = Integer.parseInt(scanner.nextLine().trim());

            switch (choix) {
                case 0:
                    continuer = false;
                    break;
                case 1:
                    String nvPseudo = super.getPseudoUnique();
                    fournisseur.setPseudo(nvPseudo);
                    break;
                case 2:
                    view.getCompagnieView();
                    String nvNomCompagnie = scanner.nextLine();
                    fournisseur.setNomCompagnie(nvNomCompagnie);
                    break;
                case 3:
                    String nvMdp = super.getMdpValid();
                    fournisseur.setMdp(nvMdp);
                    break;
                case 4:
                    String nvTelephone = super.getTelephoneValid();
                    fournisseur.setTelephone(nvTelephone);
                    break;
                default :
                    System.out.println("Choix invalide");

            }
        }

        for (int i = 0; i < comptes.size(); i++) {
            if (comptes.get(i).getEmail().equals(fournisseur.getEmail())) {
                comptes.set(i, fournisseur);
                break;
            }
        }
        listeFournisseursToJson((ArrayList<Fournisseur>) comptes);
    }

    /**
     * Permet d'afficher les composantes que possède un Fournisseur.
     * Elle donne aussi la quantité qui est disponible
     *
     * @param fournisseur le compte qui fait l'affichage
     */
    public void afficherComposantes(Fournisseur fournisseur) {
        HashMap<String, FournisseurComposante> composantes = fournisseur.getComposantes();
        if (composantes.isEmpty()) {
            System.out.println("Vous n'avez aucune composante");
        } else {
            System.out.println("La liste de vos composantes : ");
            for (Map.Entry<String, FournisseurComposante> entry : composantes.entrySet()) {

                FournisseurComposante fournisseurComposante = entry.getValue();
                Composante composante = fournisseurComposante.getComposante();
                int quantite = fournisseurComposante.getQuantite();

                System.out.println("Composante " + entry.getKey() + " : " + composante.getDescription());
                System.out.println("Type : " + composante.getType());
                System.out.println("Prix : " + composante.getPrix());
                System.out.println("Quantité : " + quantite);
                if(fournisseurComposante.isAvailable()){
                    System.out.println("Composante " + entry.getKey() + " est disponible");
                } else {
                    System.out.println("Composante " + entry.getKey() + " n'est pas disponible");
                }
                System.out.println("**********************************************************");
            }
        }
    }

    /**
     * Permet d'enregistrer une composante dans la liste des composantes que possède un Fournisseur
     *
     * @param fournisseur le compte su lequel j'enregistre de nouvelles composanates
     */
    public void enregistrerComposante(Fournisseur fournisseur) {
        Scanner scanner = new Scanner(System.in);
        view.afficherMessage("Enregistrement d'une nouvelle composante :");
        view.getNomComposanteView();
        String nomComposante = scanner.nextLine();
        view.getDescriptionComposanteView();
        String descriptionComposante = scanner.nextLine();
        view.getTypeComposanteView();
        ComposanteType type = ComposanteType.valueOf(scanner.nextLine());
        view.getPrixView();
        float prix = Float.parseFloat(scanner.nextLine());
        view.getQuantiteView();
        int quantite = Integer.parseInt(scanner.nextLine());

        Composante composante = new Composante(nomComposante, descriptionComposante, type, prix);
        FournisseurComposante fournisseurComposante = new FournisseurComposante(composante, quantite);

        fournisseur.addComposante(fournisseurComposante);

        for (int i = 0; i < comptes.size(); i++) {
            if (comptes.get(i).getEmail().equals(fournisseur.getEmail())) {
                comptes.set(i, fournisseur);
                break;
            }
        }

        listeFournisseursToJson((ArrayList<Fournisseur>) comptes);
        view.afficherMessage("Composante enregistrée avec succès !");

    }

    /**
     * Permet d'afficher la liste de composantes que possède un Fournisseur
     * @param fournisseur le compte qui possède la liste des composantes
     */
    private void afficherlisteComposantes(Fournisseur fournisseur){
        System.out.println("Vos composantes : ");
        for(Map.Entry<String, FournisseurComposante> entry : fournisseur.getComposantes().entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue().getQuantite());
        }
    }

    /**
     * Permet de retirer la composante de la liste des composantes du fournisseur
     *
     * @param fournisseur le compte ù on supprime la composante
     */
    public void supprimerComposante(Fournisseur fournisseur) {
        Scanner scanner = new Scanner(System.in);
        view.afficherMessage("Quelle composante souhaitez-vous supprimer ?");

        afficherlisteComposantes(fournisseur);

        view.getNomComposanteView();
        String nomComposante = scanner.nextLine();


        HashMap<String, FournisseurComposante> composantes = fournisseur.getComposantes();
        if (composantes.containsKey(nomComposante)) {
            composantes.remove(nomComposante);

            fournisseur.setComposantes(composantes);

            for (int i = 0; i < comptes.size(); i++) {
                if (comptes.get(i).getEmail().equals(fournisseur.getEmail())) {
                    comptes.set(i, fournisseur);
                    break;
                }
            }

            listeFournisseursToJson((ArrayList<Fournisseur>) comptes);
            view.afficherMessage("Composante supprimée avec succès !");
        } else {
            view.afficherMessage("Cette  n'existe pas !");
        }
    }

    /**
     * Permet d'apporter des changements à une composante
     * @param fournisseur le compte où on peut faire ces modifications sur les Utilisateurs
     */
    public void modifierComposante(Fournisseur fournisseur) {
        Scanner scanner = new Scanner(System.in);

        afficherlisteComposantes(fournisseur);
        System.out.println("Quelle composante souhaitez vous modifier ?");
        view.getNomComposanteView();
        String nomComposante = scanner.nextLine();

        HashMap<String, FournisseurComposante> composantes = fournisseur.getComposantes();
        if (composantes.containsKey(nomComposante)) {

            FournisseurComposante composanteAModifier = composantes.get(nomComposante);

            boolean continuer = true;
            while (continuer) {
                view.actionModifierComposante();
                int choix = Integer.parseInt(scanner.nextLine().trim());

                switch (choix) {
                    case 0:
                        continuer = false;
                        break;
                    case 1:
                        FournisseurComposante temp = composanteAModifier;
                        composantes.remove(composanteAModifier.getComposante().getNom());
                        view.getNomComposanteView();
                        String nvNom = scanner.nextLine();
                        composanteAModifier.getComposante().setNom(nvNom);
                        composantes.put(nvNom, new FournisseurComposante(new Composante(nvNom, temp.getComposante().getDescription(), temp.getComposante().getType(),temp.getComposante().getPrix()), temp.getQuantite()));
                        break;
                    case 2:
                        view.getDescriptionComposanteView();
                        String nvDescription = scanner.nextLine();
                        composanteAModifier.getComposante().setDescription(nvDescription);
                        break;
                    case 3:
                        view.getTypeComposanteView();
                        ComposanteType nvType = ComposanteType.valueOf(scanner.nextLine().toUpperCase());
                        composanteAModifier.getComposante().setType(nvType);
                        break;
                    case 4:
                        view.getPrixView();
                        float nvPrix = Float.parseFloat(scanner.nextLine());
                        composanteAModifier.getComposante().setPrix(nvPrix);
                        break;
                    default:
                        System.out.println("Choix invalide.");
                }

                fournisseur.setComposantes(composantes);

                for (int i = 0; i < comptes.size(); i++) {
                    if (comptes.get(i).getEmail().equals(fournisseur.getEmail())) {
                        comptes.set(i, fournisseur);
                        break;
                    }
                }

                listeFournisseursToJson((ArrayList<Fournisseur>) comptes);
                view.afficherMessage("Composante modifiée avec succès !");
            }
        } else {
            view.afficherMessage("Cette composante n'existe pas.");
        }
    }


}