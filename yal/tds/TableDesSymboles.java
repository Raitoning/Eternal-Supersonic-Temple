package yal.tds;

import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.VariableNonDefinieException;

import java.util.HashMap;

public class TableDesSymboles {

    public static TableDesSymboles Instance;

    private HashMap<Entree, Symboles> tds;

    private TableDesSymboles() {

        tds = new HashMap<>();
    }

    public void ajouter(Entree e, Symboles s) {

        if(tds.containsKey(e)) {

            throw new DoubleDeclarationException("Double déclaration de " + e);
        } else {

            tds.put(e, s);
        }
    }

    public Symboles identifier(Entree e) {

        if(!tds.containsKey(e)) {

            throw new VariableNonDefinieException("Variable non déclarée: " +
                    e);
        } else {

            return tds.get(e);
        }
    }

    // TODO: Détecter le type d'entrée.
    public int getTailleZoneVariable() {

        int res = 0;

        for(Entree e: tds.keySet()) {

            if(e.getTypeTDS() == TypeTDS.Variable) {

                res++;
            }
        }

        return res;
    }

    public static TableDesSymboles getInstance() {

        if (Instance == null ){

            Instance = new TableDesSymboles();
        }

        return Instance;
    }
}
