import java.util.*;
public class Activite {

    private List<String> listedActivites=new ArrayList<>();

    public List<String> getListedActivites(){
        return listedActivites;
    }

    public int getSize(){
        return listedActivites.size( );
    }
    public void setListedActivites(List<String> listedActivites){
        this.listedActivites=listedActivites;

    }


    public void addActivites(String string ){
        this.listedActivites.add(string);

    }
}
