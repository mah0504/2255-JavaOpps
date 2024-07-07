public class Metrique {

    private boolean disponibilite;
    private float tauxSuccessTaches;
    private int performance;


    // getters et setters
    public void setDisponibilite(boolean disponibilite){this.disponibilite=disponibilite;}
    public void setPerformance(int performance){ this.performance=performance;}
    public void setTauxSuccessTaches( float tauxSuccessTaches){ this.tauxSuccessTaches=tauxSuccessTaches;}

    public boolean getDisponibilite(){ return disponibilite;}

    public int getPerformance() {return performance;}
    public float getTauxSuccessTaches(){ return tauxSuccessTaches;}



}

