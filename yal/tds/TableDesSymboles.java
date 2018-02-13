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

    public void ajouter(Entree e, Symbole s) {

        if(tds.containsKey(e)) {

            throw new DoubleDeclarationException("Double déclaration de " + e);
        } else {

            tds.put(e, s);
        }
    }

    public Symbole identifier(Entree e) {

        if(!tds.containsKey(e)) {

            throw new VariableNonDefinieException("Variable non déclarée: " +
                    e);
        } else {

            return tds.get(e);
        }
    }

    public Symbole existe(Entree e){

        if(!tds.containsKey(e)) {

            return null;
        }
        else return tds.get(e);
    }

    // TODO: Détecter le type d'entrée.
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
}
