public class ServiceNotifs {

    private static ServiceNotifs instance;

    // Constructeur privé pour empêcher l'instanciation
    private ServiceNotifs() {
        // Initialisation si nécessaire
    }


    /**
     * Retourne l'unique instance de la classe {@code ServiceNotifs}.
     * Cette méthode est synchronisée pour garantir que l'instance est créée de manière thread-safe.
     * cad accès uniforme même si les threads sont asynchrones lors de leur exécution
     *
     * @return L'unique instance de la classe {@code ServiceNotifs}.
     */
    public static synchronized ServiceNotifs getInstance() {
        if (instance == null) {
            instance = new ServiceNotifs();
        }
        return instance;
    }


    // à implémenter
}

