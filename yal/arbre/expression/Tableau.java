package yal.arbre.expression;

import yal.arbre.Type;
import yal.tds.EntreeVariable;
import yal.tds.Symbole;
import yal.tds.TableDesSymboles;

public class Tableau extends Expression {

    protected EntreeVariable nom;

    protected Tableau(EntreeVariable e, int n) {

        super(n);
        nom = e;
    }

    @Override
    public void verifier() {

        Symbole s = TableDesSymboles.getInstance().identifier(nom, noLigne);
    }

    @Override
    public String toMIPS() {
        // TODO: fonction toMIPS de la classe Tableau

        return null;
    }

    @Override
    public Type getType() {

        return Type.entier;
    }
}
