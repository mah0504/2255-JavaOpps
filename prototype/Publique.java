import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Publique {

    int index = 0;
    Scanner scanner = new Scanner(System.in);
    Boolean continuer = true;
    int choix = -1;


    public int rechercherUtilisateur(Utilisateur utilisateur) {
        return index;
        // voir la liste d'utilisateurs , chercher le nom puis imprimer son yapping normalement
        // voir comment on structure les donnees de l'utilisateur et fournisseur
        // on i
    }
    public void  RecupListeActivites( Activite activites ) {
        for (int i = 0; i < activites.getSize(); i++){

            System.out.println("[" + i +"]"+ ": " + activites.getListedActivites().get(i) );
        }
    }

    public void RecupListeUtilisateurs(Utilisateur utilisateur){
        for (int i=0; i<  Systeme.getInstance().getUtilisateurs().size() ;i++) {
            System.out.println("Utilisateur [" +i + "]" +Systeme.getInstance().getUtilisateurs().get(i));

            }
        }

    public void RecupListeInterets ( Interet inter){
        for (int i=0; i<  Systeme.getInstance().getInterets().size() ;i++){

            System.out.println( "Intêrét: [ " +i + "]" +Systeme.getInstance().getInterets().get(i) );
        }
    }


    public void RecupListeFournisseur(Fournisseur fourn){
        for (int i=0; i<  Systeme.getInstance().getFournisseurs().size() ;i++){
            System.out.println("Fournisseur: " + Systeme.getInstance().getFournisseurs().get(i) );
        }
    }



    public void RecupListeUtilisateur( Utilisateur utilisateur){

        for (int i=0; i<  Systeme.getInstance().getUtilisateurs().size() ;i++){
            System.out.println("Utilisateur: " + i+ Systeme.getInstance().getUtilisateurs().get(i) +"\n" );

        }
    }

    // lier a rechercher utilisateur apres :)
    public void voirProfilU(Utilisateur utilisateur){
       System.out.println ("Pseudo: " +utilisateur.getPseudo() +"\n"+
        "Nom : " + utilisateur.getNom() + "\n" +  "Prenom " + utilisateur.getPrenom()+ "\n") ;
       System.out.println("Liste d'intérêts :");

       for (int i=0; i<utilisateur.getListeInterets().size(); i++){
           System.out.println("Intêrét [" + i + "] :" + utilisateur.getListeInterets().get(i));
        }
    }

    public void voirProfilF(Fournisseur fournisseur){
        System.out.println ("Nom: " +fournisseur.getNom() +"\n"+
                "Adresse : " + fournisseur.getAdresse() + "\n" +  "Email " + fournisseur.getEmail()+ "\n"+
        "Téléphone" + fournisseur.getTelephone() +"\n") ;
        System.out.println("Liste des composantes disponibles : ");
        for ( int i=0; i<fournisseur.getListeComposantes().size(); i++){
            System.out.println("Composante " + fournisseur.getListeComposantes().get(i) + "Inventaire :" +
                    fournisseur.getListeComposantes().get(i).getInventaire()  );
        }

    }


    // retourne les index des composantes dans la liste système des composantes si elle existe
    public ArrayList<int> rechercherComposante(Composantes composantes) {

        ArrayList<int> index = new ArrayList<int>();
        continuer = true;
        choix = -1;


        while (continuer) {
            System.out.println("Veuillez choisir une option de recherche \n [0]: Retour au menu principal " +" " +
                    "[1]: Par nom \n [2]: Par type \n [3]: Par nom du fournisseur \n ");

            try {
                choix = scanner.nextInt();
                switch (choix) {
                    // mettre le lien avec la classe utilisateur
                    case 0:
                        for (int i = 0; i < Systeme.getInstance().getComposantes().size(); i++)

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur: Veuillez entrer un nombre entier valide.");
                //scanner.next();
            }

        }

        // par nom, type getClass() ou nom du fournisseur "?"
    }
}
