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

    public void ajouter(Entree e, Symbole s, int noLigne) {

        if(tds.containsKey(e)) {
            throw new DoubleDeclarationException(noLigne, e.getNom());
        } else {

            tds.put(e, s);
        }
    }

    public boolean testParam(Entree e, int num){

        EntreeVariable ev = new EntreeVariable(e.getNom(),num);
        SymboleVariable s;

        if(num != 0){

            if(tds.containsKey(ev)) {

                s = (SymboleVariable) tds.get(ev);

                return s.getParam();
            }
            else return false;
        }
        else return false;
    }

    public Symbole identifier(Entree e, int noLigne) {

        if(!tds.containsKey(e)) {

            throw new VariableNonDefinieException(noLigne, e.getNom());
        }

        return tds.get(e);
    }

    public void testVariable(Entree e, int num, int noLigne){

        EntreeVariable ev = new EntreeVariable(e.getNom(),num);

        if(!tds.containsKey(e)) {

            if(!tds.containsKey(ev)) {

                throw new VariableNonDefinieException(noLigne, e.getNom());
            }
        }
    }

    public boolean existe(Entree e){

        return tds.containsKey(e);
    }

    public int getTailleZoneVariable() {

        int res = 1;

        for(Entree e: tds.keySet()) {

            if(tds.get(e).getTypeTDS() == TypeTDS.Variable) {

                res++;
            } else if (tds.get(e).getTypeTDS() == TypeTDS.Tableau) {

                res += tds.get(e).getTailleTableau() + 1;
            }
        }

        return res;
    }

    public int getTailleBloc(int n){

        int res =0;
        int tmp = 0;
        EntreeVariable ev;
        for(Entree e: tds.keySet()) {

            if(tds.get(e).getTypeTDS() == TypeTDS.Variable) {
                ev = (EntreeVariable)e;
                if(ev.getBloc() == n && !tds.get(e).estParametre)
                    res++;
                if(tds.get(e).estParametre)
                    tmp++;
            }
        }

        return res+tmp;

    }

    public int getNumBloc(EntreeFonction e){

        return ((SymboleFonction)tds.get(e)).getNumBloc();
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
