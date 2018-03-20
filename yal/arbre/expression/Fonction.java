package yal.arbre.expression;

import yal.arbre.Type;
import yal.tds.Entree;
import yal.tds.Symbole;
import yal.tds.TableDesSymboles;

public class Fonction extends Expression{

    protected EntreeFonction nom;

    public Fonction(Entree e, int n){
        super(n);
        nom = e;
    }


    @Override
    public void verifier() {

        Symbole s = TableDesSymboles.getInstance().identifier(nom);

    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("\t#appel fonction\n");
        sb.append("\taddi $sp, $sp -4\n");
        sb.append("\tjal "+ nom.getNom() +"\n");
        sb.append("\n");

        return sb.toString();
    }

    @Override
    public Type getType() {
        return Type.entier;
    }
}
