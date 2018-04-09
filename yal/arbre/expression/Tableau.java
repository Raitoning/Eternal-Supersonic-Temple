package yal.arbre.expression;

import yal.arbre.Type;
import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.EntreeVariable;
import yal.tds.Symbole;
import yal.tds.TableDesSymboles;

public class Tableau extends Expression {

    protected EntreeVariable nom;
    protected Expression element;
    protected Symbole s;

    public Tableau(EntreeVariable e, Expression el, int n) {

        super(n);
        nom = e;
        element = el;
    }

    @Override
    public void verifier() {

        if(element.getType() != Type.entier) {

            throw new AnalyseSemantiqueException(noLigne, "Appel de tableau: entier attendu");
        }

        element.verifier();

        s = TableDesSymboles.getInstance().identifier(nom, noLigne);
    }

    @Override
    public String toMIPS() {

        // TODO: fonction toMIPS de la classe Tableau
        StringBuilder stringBuilder = new StringBuilder();

        element.toMIPS();

        stringBuilder.append("\taddi $sp, $sp, 4\n");
        stringBuilder.append("\tlw $v0 $sp\n");

        stringBuilder.append("\t#Lecture tableau\n");
        stringBuilder.append("\t#Vérification bornes tableau\n");
        stringBuilder.append("\tbltz $v0, msgOutOfBounds\n");
        stringBuilder.append("\tbltz $v0, msgOutOfBounds\n");

        return stringBuilder.toString();
    }

    @Override
    public Type getType() {

        return Type.entier;
    }
}
