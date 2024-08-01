import java.awt.*;
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Utilisateur user = new Utilisateur();

        ControllerUtilisateur controllerUtilisateur = new ControllerUtilisateur(user);

        MenuUtilisateur menuUtilisateur = new MenuUtilisateur(controllerUtilisateur);
        menuUtilisateur.afficherMenuUtilisateur();
    }
}


