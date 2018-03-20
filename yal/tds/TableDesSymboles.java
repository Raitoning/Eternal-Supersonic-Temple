package yal.tds;

import yal.exceptions.DoubleDeclarationException;
import yal.exceptions.VariableNonDefinieException;

import java.util.HashMap;

public class TableDesSymboles {

    public static TableDesSymboles Instance;

    private HashMap<Entree, Symbole> tds;

    private TableDesSymboles() {

        tds = new HashMap<>();
    }

    // TODO: Fixer les exceptions

    public void ajouter(Entree e, Symbole s) {

        if(tds.containsKey(e)) {

//            throw new DoubleDeclarationException("ERREUR SEMANTIQUE:\n" +
//                    "\tDouble déclaration de " + e);
        } else {

            tds.put(e, s);
        }
    }

    public Symbole identifier(Entree e) {

        if(!tds.containsKey(e)) {

//            throw new VariableNonDefinieException("ERREUR SEMANTIQUE:\n" +
//                    "\tVariable non déclarée: " +
//                    e.getNom());
        }

        return tds.get(e);
    }

    public boolean existe(Entree e){

        return tds.containsKey(e);
    }

    public int getTailleZoneVariable() {

        int res = 0;

        for(Entree e: tds.keySet()) {

            if(tds.get(e).getTypeTDS() == TypeTDS.Variable) {

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

    // DEBUG
    public void reset() {

        tds.clear();
    }
}
