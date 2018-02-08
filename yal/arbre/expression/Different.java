package yal.arbre.expression;

import yal.arbre.Type;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Different extends Comparaison {

    public Different(Expression gauche, Expression droite) {
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

        nbComp++;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(gauche.toMIPS());
        stringBuilder.append(droite.toMIPS());
        stringBuilder.append("\n");
        stringBuilder.append("\taddi $sp, $sp 4\t# Different\n");
        stringBuilder.append("\tlw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp 4\n");
        stringBuilder.append("\tlw $t8, ($sp)\n");
        stringBuilder.append("\tbeq $v0, $t8, " + "comp" + nbComp + "\n");
        stringBuilder.append("\tli $v0, -1\n");
        stringBuilder.append("\tj finComp" + nbComp + "\n");
        stringBuilder.append("\tcomp" + nbComp + ":\n");
        stringBuilder.append("\tli $v0, 0\n");
        stringBuilder.append("\tfinComp" + nbComp +":\n");
        stringBuilder.append("\tsw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp, -4\n");

        return stringBuilder.toString();
    }

    @Override
    public String operateur() {
        return " != ";
    }
}
