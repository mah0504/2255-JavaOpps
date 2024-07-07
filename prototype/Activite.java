import java.util.*;
public class Activite {

    private List<Activite> listedActivites=new ArrayList<>();
    private String type;
    private String nom;
    private int dateDebut;
    private int datefin;
    private List<Tache> listeTaches= new ArrayList<>();
    private int points;
    private int classement;

    private List<Interet> listedInterets = new ArrayList<>();


   // la plateforme doit notifier les utilisateurs quand une nouvelle activité correspondant aux
    // intérêts d'un utilisateur est créée.


    public void ajouterActivite(Activite activite ){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez choisir un nom pour votre activité: ");
        String choix= scanner.nextLine();
        activite.setNom(choix);


        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Veuillez choisir le type de l'activité: ");
        // check si on a des types predefinis ou si on veut choisir parmis ceux dispo

        String choix1= scanner1.nextLine();
        activite.setType(choix1);

        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Veuillez choisir le nombre d'interets que vous voulez associer à l'activité:");

        int choix2 = scanner2.nextInt();
        for ( int i= 0 ; i<choix2; i++){
            // choisir composante

        }

    }



    public void setNom(String string){
        this.nom = string;
    }

    public void setType(String string){
        this.type= string;
    }
    public List<Activite> getListedActivites(){
        return listedActivites;
    }


    public int getSize(){
        return listedActivites.size( );
    }


    public void setListedActivites(List<Activite> listedActivites){
        this.listedActivites=listedActivites;

    }

    public void addActivites(Activite activite ){
        this.listedActivites.add(activite);

    }
}
