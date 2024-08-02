import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ControllerFournisseur extends ControllerCompte{
    
    private Fournisseur fournisseur;
    private MenuFournisseur fournisseurView;
    private ArrayList<FournisseurComposante> listeCompo= fournisseur.getComposantes();

    @Override
    public void creerCompte() {

    }

    @Override
    public void seConnecter() {

    }


    private List<Fournisseur> fournisseurs;

    /**
     * Charge la liste des fournisseurs à partir d'un fichier JSON.
     *
     */
    private List<Fournisseur> chargerFournisseursDepuisJson() {
        try (FileReader reader = new FileReader("src/main/resources/fournisseurs.json")) {
            Gson gson = new Gson();
            Type typeListeFournisseurs = new TypeToken<ArrayList<Fournisseur>>(){}.getType();
            fournisseurs = gson.fromJson(reader, typeListeFournisseurs);

            return fournisseurs;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }



    public List<Fournisseur> getFournisseurs(){
        return chargerFournisseursDepuisJson();
    }


    public void enregistrerComposante(){
        //xhoisir un fournisseur
        //
    }

    public void supprimerComposante(){
        for (FournisseurComposante c: listeCompo ){

        }
    }

    public void modifierComposante(){

    }




}
