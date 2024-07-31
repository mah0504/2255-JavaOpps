public class ServiceNotifs {

    private static ServiceNotifs instance;

    // Constructeur privé pour empêcher l'instanciation
    private ServiceNotifs() {
        // Initialisation si nécessaire
    }


    public static synchronized ServiceNotifs getInstance() {
        if (instance == null) {
            instance = new ServiceNotifs();
        }
        return instance;
    }
}
