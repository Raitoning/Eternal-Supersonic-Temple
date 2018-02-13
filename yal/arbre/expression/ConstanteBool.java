package yal.arbre.expression;

import yal.arbre.Type;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class ConstanteBool extends Constante {

    public ConstanteBool(String texte, int n) {

        super(texte, n) ;
    }

    @Override
    public Type getType() {

        return Type.booleen;
    }

    @Override
    public String toMIPS() {

        StringBuilder stringBuilder = new StringBuilder();

        if(cste.equals("vrai")) {

            stringBuilder.append("\tli $v0, -1\t# Booléen vrai\n");
        } else {

            stringBuilder.append("\tli $v0, 0\t# Booléen faux\n");
        }

        stringBuilder.append("\tsw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp, -4\n");

        return stringBuilder.toString();
    }
}
