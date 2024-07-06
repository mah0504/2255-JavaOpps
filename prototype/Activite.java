import java.util.*;
public class Activite {

    private List<String> listedActivites=new ArrayList<>();
    private String type;
    private String nom;
    private int dateDebut;
    private int datefin;
    private List<Tache> listeTaches= new ArrayList<>();
    private int points;
    private int classement;



   // la plateforme doit notifier les utilisateurs quand une nouvelle activité correspondant aux
    // intérêts d'un utilisateur est créée.



    public List<String> getListedActivites(){
        return listedActivites;
    }


    public int getSize(){
        return listedActivites.size( );
    }


    public void setListedActivites(List<String> listedActivites){
        this.listedActivites=listedActivites;

    }

    public void addActivites(String string ){
        this.listedActivites.add(string);

    }
}
