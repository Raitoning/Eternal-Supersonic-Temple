package yal.arbre.expression;

import yal.arbre.Type;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class NonLogique extends Unaire {

    public NonLogique(Expression expr) {

        super(expr);
    }

    @Override
    public String operateur() {

        return " non " ;
    }

    @Override
    public void verifier() {

        if( expression.getType() != Type.booleen ) {

            throw new AnalyseSemantiqueException(noLigne, "Sous expression " +
                    "non booléenne.");

        } else {

            expression.verifier();
        }
    }

    public String toMIPS() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(expression.toMIPS());
        stringBuilder.append("\n");
        stringBuilder.append("\taddi $sp, $sp, 4\t# Non logique\n");
        stringBuilder.append("\tlw $v0, ($sp)\n");
        stringBuilder.append("\tnor $v0, $v0, $v0\n");
        stringBuilder.append("\tsw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp, -4\n");

        return stringBuilder.toString();
    }


    @Override
    public Type getType() {

        return Type.booleen;
    }
}
