import java.util.*;
public class Activite {

    private static List<Activite> listedActivites=new ArrayList<>();
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

            ArrayList<Interet> listeInteretsGeneraux = Interet.getListeInteretsGeneraux();

            System.out.println("Choisissez un intérêt (entrez le numéro, 0 pour aucun choix):");
            System.out.println("0. Ajouter un nouvel intérêt");
            for (int j = 0; j < listeInteretsGeneraux.size(); j++) {
                System.out.println((j + 1) + ". " + listeInteretsGeneraux.get(j).getNom());
            }
            int choix3 = scanner.nextInt();
            scanner.nextLine();

            if (choix3 == 0) {
                System.out.print("Entrez le nom du nouvel intérêt: ");
                String nomNvInteret = scanner.nextLine();
                Interet nouveau = new Interet(nomNvInteret);
                listeInteretsGeneraux.add(nouveau);
                activite.ajouterInteret(nouveau);
            } else if (choix3 >= 1 && choix3 <= listeInteretsGeneraux.size()) {
                Interet interet = listeInteretsGeneraux.get(choix3 - 1);
                activite.ajouterInteret(interet);
            } else {
                System.out.println("Choix invalide. Réessayez.");
                i--;
            }


        }

    }

    public void ajouterInteret(Interet interet) {
        this.listedInterets.add(interet);
    }

    public void setNom(String string){
        this.nom = string;
    }

    public void setType(String string){
        this.type= string;
    }

    public static List<Activite> getListedActivites(){
        return listedActivites;
    }


    public int getSize(){
        return listedActivites.size( );
    }


    public void setListedActivites(List<Activite> listedActivites){
        this.listedActivites=listedActivites;

    }

    public List<Interet> getListedInterets(){
        return listedInterets;
    }

    public void addActivites(Activite activite ){
        this.listedActivites.add(activite);

    }
    /*
     * public void addActivites(){
     *    Activite activite = new Activte();
     *    ajouterActivite(activite)
     *    this.listedActivites.add(activite);
     * }
     *
     */
}


