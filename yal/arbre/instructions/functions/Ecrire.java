package yal.arbre.instructions.functions;

import yal.arbre.Type;
import yal.arbre.expression.Expression;
import yal.arbre.instructions.Instruction;

public class Ecrire extends Instruction {

    protected Expression expression;

    protected Ecrire(int no) {
        super(no);
    }

    @Override
    public void verifier() {

    }

    public Ecrire(int no, Expression expr){
        this(no);
        expression = expr ;
    }

    @Override
    public String toMIPS() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(expression.toMIPS());
        stringBuilder.append("\n");
        if ( expression.getType() == Type.entier)
        stringBuilder.append("\tjal printEntier\t# Affichage\n");
        if (expression.getType() == Type.booleen)
            stringBuilder.append("\tjal printBool\t# Affichage\n");
        stringBuilder.append("\n\n");
        return stringBuilder.toString();
    }

    @Override
    public String toString() {

        return "ecrire(" + expression + ");" ;
    }

}
