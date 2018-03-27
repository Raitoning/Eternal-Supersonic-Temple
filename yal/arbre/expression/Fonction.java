package yal.arbre.expression;

import yal.arbre.Type;
import yal.tds.EntreeFonction;
import yal.tds.SymboleFonction;
import yal.tds.SymboleVariable;
import yal.tds.TableDesSymboles;

import java.util.ArrayList;

public class Fonction extends Expression{

    protected EntreeFonction nom;
    protected ArrayList<Expression> p;

    public Fonction(EntreeFonction e, int n){

        super(n);
        nom = e;
        p = new ArrayList<Expression>();
    }

    public Fonction(EntreeFonction e, int n, ArrayList<Expression> param){

        super(n);
        nom = e;
        p = param;
    }

    public void setBloc(int numbloc){bloc = numbloc; for(int i = 0;i<p.size();i++)p.get(i).setBloc(numbloc);}
    @Override
    public void verifier() {

        SymboleFonction s = (SymboleFonction) TableDesSymboles.getInstance().identifier(nom, noLigne);
        for(int i =0;i < p.size();i++){
            p.get(i).verifier();
            //TODO exception semantique : mauvais type de parametre : entier attendu
            /*if(p.get(i).getType() == Type.booleen)
                throw new */
        }

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("\t#appel fonction\n");

        for(int k = 0;k < p.size();k++){
            sb.append("#stockage parametre\n");
            sb.append(p.get(k).toMIPS());
        }
        //sb.append("\taddi $sp, $sp -4\n");
        sb.append("\tjal "+ nom.getNom() +"\n");
        sb.append("\n");

        return sb.toString();
    }

    @Override
    public Type getType() {
        return Type.entier;
    }
}
