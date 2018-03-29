package yal.arbre.expression;

import yal.arbre.Type;
import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.EntreeFonction;

public class Retourne extends Expression{

    private EntreeFonction nom;
    private Expression expr;

    public Retourne(int n, Expression e) {

        super(n);
        expr = e ;
    }

    public void setBloc(int num){bloc = num; expr.setBloc(num);}

    @Override
    public void verifier() {

        expr.verifier();

        if(expr.getType() == Type.booleen) {

            throw new AnalyseSemantiqueException(noLigne, "Type de retour entier attendu, booleen trouvé");
        }
    }

    @Override
    public String toMIPS() {

        StringBuilder sb = new StringBuilder();
        sb.append(expr.toMIPS());
        sb.append("\taddi $sp, $sp, 4\n");

        if (nom != null){

            sb.append("\tj finF"+nom.getNom()+"\n");
        }
        return sb.toString();
    }

    @Override
    public Type getType() {

        return Type.entier;
    }

    public void setName(EntreeFonction e){

        nom = e;
    }
}
