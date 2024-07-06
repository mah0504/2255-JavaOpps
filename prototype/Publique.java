public class Publique {

    int index=0;


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


    public void RecupListeInterets ( Interet inter){
        for (int i=0; i<  Systeme.getInstance().getInterets().size() ;i++){

            System.out.println(Systeme.getInstance().getInterets().get(i) );
        }
    }

    public void RecupListeFournisseur(Fournisseur fourn){
        for (int i=0; i<  Systeme.getInstance().getFournisseurs().size() ;i++){
            System.out.println(Systeme.getInstance().getFournisseurs().get(i) );
        }
    }



    public void RecupListeUtilisateur( Utilisateur utilisateur){

        for (int i=0; i<  Systeme.getInstance().getUtilisateurs().size() ;i++){
            System.out.println(Systeme.getInstance().getUtilisateurs().get(i) );

        }
    }

    public void voirProfil(Acteur acteur){
        // soit utilisateur soit fournisseur
    }


    public void rechercherComposante(Composantes composantes) {
        // par nom, type getClass() ou nom du fournisseur "?"
    }
}
