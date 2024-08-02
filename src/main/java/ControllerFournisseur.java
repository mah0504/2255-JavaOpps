import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ControllerFournisseur extends ControllerCompte{
    
    private Fournisseur fournisseur;
    private MenuFournisseur fournisseurView;
    private ArrayList<FournisseurComposante> listeCompo= fournisseur.getComposantes();
    private ArrayList<Fournisseur> listeFournisseurs;


    public ControllerFournisseur(Fournisseur fournisseur){
        this.fournisseur = fournisseur;
        chargerFournisseursDepuisJson();
    }

    @Override
    public void creerCompte() {

    }

    @Override
    public void seConnecter() {

    }


    /**
     * Charge la liste des fournisseurs à partir d'un fichier JSON.
     *
     */
    private List<Fournisseur> chargerFournisseursDepuisJson() {
        try (FileReader reader = new FileReader("src/main/resources/fournisseurs.json")) {
            Gson gson = new Gson();
            Type typeListeFournisseurs = new TypeToken<ArrayList<Fournisseur>>(){}.getType();
            listeFournisseurs = gson.fromJson(reader, typeListeFournisseurs);

            return listeFournisseurs;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }

    public ArrayList<Fournisseur> getFournisseurs(){
        return listeFournisseurs;
    }

    public void FournisseurToJson(ArrayList<Fournisseur> listeFournisseurs) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter("src/main/resources/fournisseurs.json")){
            gson.toJson(this.listeFournisseurs, writer);
        }catch(IOException e){
            e.printStackTrace();
        }
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
