import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.print.attribute.HashAttributeSet;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
public abstract class ControllerCompte<T extends Compte>{

    public ControllerCompte(){}

    public abstract boolean activateCompte(Compte compte);

    public abstract boolean deactivateCompte(Compte compte);

    public abstract boolean confirmCompte(Compte compte);
}
