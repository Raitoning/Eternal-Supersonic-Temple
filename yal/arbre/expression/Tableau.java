package yal.arbre.expression;

import yal.arbre.Type;
import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.EntreeVariable;
import yal.tds.Symbole;
import yal.tds.TableDesSymboles;

public class Tableau extends Expression {

    protected EntreeVariable nom;
    protected Expression element;
    protected Expression valeur;

    protected Tableau(EntreeVariable e, Expression el, Expression val, int n) {

        super(n);
        nom = e;
        element = el;
        valeur = val;
    }

    @Override
    public void verifier() {

        if(element.getType() != Type.entier || valeur.getType() != Type.entier) {

            throw new AnalyseSemantiqueException(noLigne, "Appel de tableau: entier attendu");
        }

        element.verifier();
        valeur.verifier();

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
