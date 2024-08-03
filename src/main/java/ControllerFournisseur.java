import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ControllerFournisseur{
    
    private Fournisseur fournisseur;
    private MenuFournisseur fournisseurView;
    private ArrayList<Fournisseur> listeFournisseurs;


    public ControllerFournisseur(Fournisseur fournisseur, MenuFournisseur fournisseurView){

        this.fournisseur = fournisseur;
        this.fournisseurView = fournisseurView;
        listeFournisseurs = new ArrayList<>();
        chargerFournisseursDepuisJson();
    }

    /**
     * Charge la liste des fournisseurs à partir d'un fichier JSON.
     *
     */
    private ArrayList<Fournisseur> chargerFournisseursDepuisJson() {
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
        return chargerFournisseursDepuisJson();
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
//        for (FournisseurComposante c: listeCompo ){
//
//        }
    }

    public void modifierComposante(){

    }




}
