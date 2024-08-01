import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        ControllerUtilisateur controllerUtilisateur = new ControllerUtilisateur(null);

 //       ArrayList<Utilisateur> utilisateurs = controllerUtilisateur.getListeUtilisateurs();
//        Utilisateur user = utilisateurs.get(0);
 //       controllerUtilisateur.setUtilisateur(user);


        MenuUtilisateur menuUtilisateur = new MenuUtilisateur(controllerUtilisateur);
        menuUtilisateur.afficherMenuUtilisateur();

 //       System.out.println(user);
        //System.out.println(utilisateurs);
 //       controllerUtilisateur.toJson(utilisateurs);
    }
}
