import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
public abstract class ControllerCompte {

    private Utilisateur utilisateur;
    private MenuUtilisateur utilisateurView;


    /**
     * Génère la liste des comptes enregistrés au système Robotix.
     * @return
     * @param <T> Utilisateur ou Fournisseur
     */
    private <T extends Compte> List<T> genererListe(String path) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(path)) {
            Type type = new TypeToken<List<T>>() {}.getType();
            return gson.fromJson(reader, type);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // sinon?
    }

    /**
     * Vérifie l'existence d'un pseudo dans les comptes inscrits à Robotix.
     * @return
     */
    public Compte verifierPseudo(String pseudo) {

        pseudo = pseudo.toLowerCase();

        // Rechercher chez les utilisateurs
        List<Utilisateur> utilisateurs = genererListe("src/main/resources/utilisateurs.json");

        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getPseudo().equals(pseudo)) {
                return utilisateur;
            }
        }

        // Rechercher chez les fournisseurs
        List<Fournisseur> fournisseurs = genererListe("src/main/resources/fournisseurs.json");
        for (Fournisseur fournisseur : fournisseurs) {
            if (fournisseur.getPseudo().equals(pseudo)) {
                return fournisseur;
            }
        }

        return null;
    }



    public abstract void creerCompte() ;

    public abstract void seConnecter() ;

}
