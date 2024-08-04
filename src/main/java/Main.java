import java.awt.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ControllerUtilisateur controllerUtilisateur = new ControllerUtilisateur();
        ControllerFournisseur controllerFournisseur = new ControllerFournisseur();
    //    MenuUtilisateur menuUtilisateur = new MenuUtilisateur();

        MenuCompte menuCompte = new MenuCompte(controllerUtilisateur, controllerFournisseur );
        menuCompte.afficherMenu();
    }

}


