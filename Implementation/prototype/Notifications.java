import java.util.*;

public class Notifications {
    private Notifications notif;

    public  Notifications getNotif() { return this.notif; }
    private String message;


    public Notifications(String message) {
        this.message = message;
    }

    public Notifications(){

    }


    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }

    Systeme sys = new Systeme();

// méthode qui sert à notifier l'utilisateur de la création d'une nouvelle activité
// si elle correspond à ses intérêts



    public void notifierUtilisateur(Activite activite) {
        int declencheur=0;  // nom aleatoire tbh
        ArrayList<Utilisateur> listeUtilisateurs= sys.getInstance().getUtilisateurs();

            for ( Utilisateur u: listeUtilisateurs){
                for ( Interet i: activite.getListedInterets()){
                    if (u.getListeInterets().contains(i)) {
                        notifierCreationActivite(u, activite);
                        break;
                    }
                }
            }

            }

    private void notifierCreationActivite(Utilisateur utilisateur, Activite activite) {
        Notifications notification = new Notifications("Une nouvelle activité avec " +
                "des intérêts similaires aux vôtres a été créée! \n" + "L'activité est :" +activite );
        utilisateur.addNotif(notification);

    }

}






