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

    @Override
    public void verifier() {
        //TODO: this
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append(expr.toMIPS());
        if (nom != null){
            sb.append("\tj finF"+nom.getNom()+"\n");
        } else {
            throw new AnalyseSemantiqueException(noLigne, "Something went wrong");
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
