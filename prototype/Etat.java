public class Etat {

    private double vitesse;
    private int nivDeBatterie;
    private int consoCPU;
    private int consoMem;
    private Object[] Etats = new Object[4]; // Tableau pour stocker les états


    public Etat() {

        // on initialise deja
        for (int i = 0; i < 4; i++) {
            Etats[i] = vitesse;       // Stocke la vitesse dans Etats[0]
            Etats[i] = nivDeBatterie; // Stocke le niveau de batterie dans Etats[1]
            Etats[i] = consoCPU;      // Stocke la consommation CPU dans Etats[2]
            Etats[i] = consoMem;      // Stocke la consommation mémoire dans Etats[3]
        }
    }

    public double getVitesse(){
        return this.vitesse;
    }

    public int getNivDeBatterie(){
        return this.nivDeBatterie;
    }

    public int getConsoCPU(){
        return this.consoCPU;
    }

    public int getConsoMem(){
        return this.consoMem;
    }

}
