import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ActiviteTest {

    private boolean isAvailable(ArrayList<Activite> activites, String nomActivite){
        for(Activite activite : activites){
            if(activite.getNom().equals(nomActivite)){
                return true;
            }
        }
        return false;
    }

    @Test
    public void testisAvailable_allTrue(){
        ArrayList<Activite> activites = new ArrayList<>();
        activites.add(new Activite("Activite1", "Description1"));
        activites.add(new Activite("Activite2", "Description2"));
        activites.add(new Activite("Activite3", "Description3"));

        assertTrue(isAvailable(activites, "Activite1"));
    }

    @Test
    public void testisAvailable_allFalse(){
        ArrayList<Activite> activites = new ArrayList<>();
        activites.add(new Activite("Activite1", "Description1"));
        activites.add(new Activite("Activite2", "Description2"));
        activites.add(new Activite("Activite3", "Description3"));

        assertFalse(isAvailable(activites, "Activite5"));
    }

    @Test
    public void testisAvailable_vide(){
        ArrayList<Activite> activites = new ArrayList<>();

        assertFalse(isAvailable(activites, "Activite1"));
    }

}
