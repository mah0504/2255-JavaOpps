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


    public void voirProfil(Acteur acteur){
        // soit utilisateur soit fournisseur
    }


    public void rechercherComposante(Composantes composantes) {
        // par nom, type getClass() ou nom du fournisseur "?"
    }
}
