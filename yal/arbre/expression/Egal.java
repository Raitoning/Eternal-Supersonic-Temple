package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Egal extends Comparaison {

    public Egal(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public void verifier() {

        if(gauche.getType() != droite.getType()) {

            throw new AnalyseSemantiqueException(noLigne, "Types des sous " +
                    "expressions non comparables");

        } else {

            gauche.verifier();
            droite.verifier();
        }
    }

    @Override
    public String toMIPS() {

        int comp = nbComp++;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(gauche.toMIPS());
        stringBuilder.append(droite.toMIPS());
        stringBuilder.append("\n");
        stringBuilder.append("\taddi $sp, $sp 4\t# Egal\n");
        stringBuilder.append("\tlw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp 4\n");
        stringBuilder.append("\tlw $t8, ($sp)\n");
        stringBuilder.append("\tbeq $v0, $t8, " + "comp" + comp + "\n");
        stringBuilder.append("\tli $v0, 0\n");
        stringBuilder.append("\tj finComp" + comp + "\n");
        stringBuilder.append("\tcomp" + comp + ":\n");
        stringBuilder.append("\tli $v0, -1\n");
        stringBuilder.append("\tfinComp" + comp +":\n");
        stringBuilder.append("\tsw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp, -4\n");

        return stringBuilder.toString();
    }

    @Override
    public String operateur() {

        return " == ";
    }
}
