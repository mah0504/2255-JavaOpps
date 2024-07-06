public class BrouillonExec
{
    public static void main(String[] args) {
//        Flotte flotte = new Flotte();
//
//        // Initialisation des composantes avec leurs inventaires
//       // CPU cpu1 = new CPU("CPU", 2);
//        //flotte.getListeComposantes().add(cpu1);
//
//        //Roue roue1 = new Roue("roue",5);
//        //flotte.getListeComposantes().add(roue1);
//
//        // Affichage des composantes disponibles
//        System.out.println("Liste des composantes disponibles : ");
//        for (Composantes c : flotte.getListeComposantes()) {
//            System.out.println(c.getClass().getSimpleName() + " - Inventaire : " + c.getInventaire());
//        }
//        Composantes composante=new Composantes();
//        composante.choisirCompo();
//
//        // Enregistrement d'un nouveau robot
//        Robot robot = new Robot();
//      //  flotte.enregistrerRobot(robot);
//    }


        Activite activites = new Activite();
        Publique pub = new Publique();

        // exemple d'activites a ajouter
        activites.addActivites("Inscription à une activité");
        activites.addActivites("Acheter une composante");
        activites.addActivites("Créer une tâche");
        activites.addActivites("Connexion/Inscription utilisateur");
        activites.addActivites("Voir les détails de l'activité");
        activites.addActivites("Assigner une composante à un robot");

        pub.RecupListeActivites(activites);

        Robot r= new Robot();
        r.setNom("IJWFVN");
        r.setType("Mouvement");
        r.setNumeroDeSerie(1234);

        r.afficherInformations();



    }
}
