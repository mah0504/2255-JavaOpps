public class Etat {

    private double vitesse;
    private int nivDeBatterie;
    private int consoCPU;
    private int consoMem;
    private double position;

    public Etat() {


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

    public double getPosition() {return this.position;}


    // Setters
    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public void setNivDeBatterie(int nivDeBatterie) {
        this.nivDeBatterie = nivDeBatterie;
    }

    public void setConsoCPU(int consoCPU) {
        this.consoCPU = consoCPU;
    }

    public void setConsoMem(int consoMem) {
        this.consoMem = consoMem;
    }

    public void setPosition(double position) {
        this.position = position;
    }

}

