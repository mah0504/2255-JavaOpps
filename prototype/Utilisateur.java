import java.util.regex.*;
public class Utilisateur { 
    private int numero; 
    private String nomdutilisateur, motdepasse, nom; 
    private String email = "[A-Za-z][A-Za-z0-9-_\\.]+@[a-z]+\\.(com|fr|ca|io|web)";  //regex


    // comment associer à l'adresse mail de l'Utilisateur  
    // verifier adresse mail -> invalide afficher erreur sur le Scanner 


    public void seConnecter(String nomdutilisateur , String motdepasse){
        System.out.println("Entrez le nom d'utilisateur et mdp :"); 
        // apres modifier pr match ce qui est ecrit par l'utilisateur ? definir un csv avec les 
        // noms d'utilisateur  mdps adresses mail ...etc ?
    }

    public void ajouterComposante(Flotte robot){
        // on prend la composante ? On l'ajoute dans la Hashmap partie valeur 
        // du robot .. donc arrayList comme value ? 
        
    }

    public void acheterComposante(Composantes compo) {
        // on demande a l'utilisateur quelle composante il veut 
        // il chosiit selon l'indice de la composante dans le tableau 
        // on lui associe avec 
    }

    public void accederAuxMetriques ( Robot robot) {
        
    }


    // utilisateur a un inventaire de composantes


}