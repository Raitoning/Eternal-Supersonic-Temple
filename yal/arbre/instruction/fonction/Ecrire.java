package yal.arbre.instruction.fonction;

import yal.arbre.Type;
import yal.arbre.expression.Expression;
import yal.arbre.instruction.Instruction;

public class Ecrire extends Instruction {

    private boolean containsString;

    private static int labelnum= 0;

    protected Expression expression;
    private String chain;

    protected Ecrire(int no) {
        super(no);
    }

    @Override
    public void verifier() {
        if (!containsString)
            expression.verifier();
    }

    public Ecrire(int no, Expression expr){
        this(no);
        expression = expr ;
        this.containsString = false;
    }

    public Ecrire(int no, String s){
        this(no);
        chain = s ;
        this.containsString = true;
    }

    @Override
    public String toMIPS() {
        return toMips(containsString);
    }

    @Override
    public String toString() {
        if (containsString)
            return "ecrire "+ chain + ";" ;
        return "ecrire " + expression + ";" ;
    }

    private String toMips(boolean b){
        StringBuilder stringBuilder = new StringBuilder();

        if (b){
            stringBuilder.append("\tla $a0, msgString"+ListeChaines.getInstance().position(this.chain)+"\n");
            stringBuilder.append("\tjal printString\t# Affichage\n");

        } else {
            stringBuilder.append(expression.toMIPS());
            stringBuilder.append("\taddi $sp, $sp, 4\n");
            stringBuilder.append("\tlw $v0, ($sp)\n");
            stringBuilder.append("\n");
            if (expression.getType() == Type.entier)
                stringBuilder.append("\tjal printEntier\t# Affichage\n");
            if (expression.getType() == Type.booleen)
                stringBuilder.append("\tjal printBool\t# Affichage\n");
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }

}
