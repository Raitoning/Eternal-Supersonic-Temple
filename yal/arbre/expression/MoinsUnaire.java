package yal.arbre.expression;

import yal.arbre.Type;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class MoinsUnaire extends Unaire {

    public MoinsUnaire(Expression expr) {

        super(expr);
    }

    @Override
    public String operateur() {

        return "- " ;
    }

    @Override
    public void verifier() {

        if( expression.getType() != Type.entier ) {

            throw new AnalyseSemantiqueException(noLigne, "Sous expression " +
                    "non arithmetique.");

        } else {

            expression.verifier();
        }
    }

    public String toMIPS() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(expression.toMIPS());
        stringBuilder.append("\n");
        stringBuilder.append("\tli $v0, 0\t # Moins unaire\n");
        stringBuilder.append("\taddi $sp, $sp, 4\n");
        stringBuilder.append("\tlw $t8, ($sp)\n");
        stringBuilder.append("\tsub $v0, $v0, $t8\n");
        stringBuilder.append("\tsw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp, -4\n");

        return stringBuilder.toString();
    }

    @Override
    public Type getType() {

        return Type.entier;
    }
}
