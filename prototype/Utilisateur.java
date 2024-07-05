import java.util.ArrayList;
import java.util.regex.*;
public class Utilisateur implements Acteur{
    private int numero; 
    private String nomdutilisateur, motdepasse, nom; 
    private String email = "[A-Za-z][A-Za-z0-9-_\\.]+@[a-z]+\\.(com|fr|ca|io|web)";  //regex

    private ArrayList<Utilisateur> listeUtilisateurs;
    private ArrayList<Interets> listeInterets;

    public ArrayList<Utilisateur> getListeUtilisateurs(){
        return listeUtilisateurs;
    }

     public void ajouterNouvUtilisateur(Utilisateur utilisateur){
        listeUtilisateurs.add(utilisateur);
     }


    @Override
    public void modifierSonProfil() {
    }

    @Override
    public void sInscrire() {
    }

    @Override
    public void validerMDP() {
    }

    @Override
    public void seConnecter() {
    }

    @Override
    public void comfirmerInscription() {

    }
    // après inscription validée

}