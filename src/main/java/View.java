import java.util.Scanner;

public class View {
  private Compte loggedAccount;
  private MenuFournisseur menuFournisseur;
  private MenuUtilisateur menuUtilisateur;


  /**
   * Affiche le menu principal et permet de naviguer vers les menus Fournisseur ou Utilisateur
   * selon le choix entré par le participant
   *
   */
  /*public void afficherMenuPrincipal() {
    Scanner scanner = new Scanner(System.in);
    try{
    System.out.println("Bienvenue dans Robotix ! \n Veuillez choisir sous quel format vous voulez participer" +
            "[1] : Fournisseur \n [2] : Utilisateur");

      int choix = scanner.nextInt();

      switch (choix) {
        case 1:
          menuFournisseur.afficherMenuFournisseur();
          break;
        case 2:
          menuUtilisateur.afficherMenuUtilisateur();
          break;

        default:
          System.out.println("Veuillez entrer un nombre valide.");
          break;
      }


  }catch (Exception e){

    }

  }*/
}


