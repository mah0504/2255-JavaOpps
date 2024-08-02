import java.awt.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        ControllerUtilisateur controllerUtilisateur = new ControllerUtilisateur(null, null);
        ArrayList<Utilisateur> utilisateurs = controllerUtilisateur.getListeUtilisateurs();
        controllerUtilisateur.UtilisateurToJson(utilisateurs);

        //Utilisateur user = utilisateurs.get(0);
        //controllerUtilisateur.setUtilisateur(user);

        //MenuUtilisateur menuUtilisateur = new MenuUtilisateur(controllerUtilisateur);
        //menuUtilisateur.afficherMenuUtilisateur();

        //System.out.println(user);
        //System.out.println(utilisateurs);
        //controllerUtilisateur.UtilisateurToJson(utilisateurs);
    }

}



