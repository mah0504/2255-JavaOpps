import java.util.Scanner;

public class View {

  private Compte compteConnecte;
  private MenuCompte menuCompte;

  public View() {
  }

  /**
   * Cette classe Permet l'affichage des menus et des options à l'utilisateur ou au fournisseur.
   * Elle gère une partie des interactions entre l'humain et la machine
   */

  public void home(){
    System.out.println("Bienvenue à Robotix");
    System.out.println("Choisissez un rôle");
    System.out.println("[0] : Quitter");
    System.out.println("[1] : Utilisateur");
    System.out.println("[2] : Fournisseur");
  }

  public void homeCompte(){
    System.out.println("Menu principal");
    System.out.println("Que souhaitez-vous faire ?");
    System.out.println("[0] : Retour à la home page");
    System.out.println("[1] : Se connecter");
    System.out.println("[2] : S'inscrire");
    System.out.println("[3] : Confirmer l'inscription");
  }

  public void afficherMessage(String message){
    System.out.println(message);
  }

  public void getPseudoView(){
    System.out.println("Entrez un Pseudo :");
  }

  public void getNomView(){
    System.out.println("Entrez un Nom :");
  }

  public void getPrenomView(){
    System.out.println("Entrez un Prenom :");
  }

  public void getEmailView(){
    System.out.println("Entrez un Email :");
  }

  public void getMotDePasseView(){
    System.out.println("Entrez un Mot de passe :");
  }

  public void getTelephoneView(){
    System.out.println("Entrez un Telephone :");

  }

  public void getCompagnieView(){
    System.out.println("Entrez un nom de compagnie :");
  }

  public void getConfirmationLien(){
    System.out.println("Entrez la confirmation du lien : ");
  }

  public void getNomComposanteView(){
    System.out.println("Entrez le nom de la composante : ");
  }

  public void getDescriptionComposanteView(){
    System.out.println("Entrez la description de la composante : ");
  }

  public void getTypeComposanteView(){
    System.out.println("Entrez le type de la composante : ");
  }

  public void getPrixView(){
    System.out.println("Entrez le prix de la composante : ");
  }

  public void getQuantiteView(){

    System.out.println("Entrez la quantité de composantes disponible : ");
  }

  public void actionModifierProfilUtilisateur(){
    System.out.println("Que souhaitez-vous modifier ?:");
    System.out.println(" [0] : Retour au menu principal \n " +
            "[1] : Pseudo  \n " +
            "[2] : Nom \n " +
            "[3] : Prenom  \n " +
            "[4] : Mot de passe \n " +
            "[5] : Telephone ");
  }

  public void actionModifierProfilFournisseur(){
    System.out.println("Que souhaitez-vous modifier ?:");
    System.out.println(" [0] : Retour au menu principal \n " +
            "[1] : Pseudo  \n " +
            "[2] : Nom de la Compagnie \n " +
            "[3] : Mot de passe \n " +
            "[4] : Telephone ");
  }

  public void actionModifierComposante(){

    System.out.println("Que souhaitez-vous modifier ?:");
    System.out.println(" [0] : Retour au menu principal \n " +
            "[1] : Nom de la composante  \n " +
            "[2] : Description de la composante \n " +
            "[3] : Type de la composante\n " +
            "[4] : Prix de la composante \n ");

  }

}


