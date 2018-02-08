package yal.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Mult extends BinaireArithmetique {

    public Mult(Expression gauche, Expression droite) {

        super(gauche, droite);
    }

    @Override
    public String operateur() {

        return " * ";
    }

    @Override
    public String toMIPS() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(gauche.toMIPS());
        stringBuilder.append(droite.toMIPS());
        stringBuilder.append("\n");
        stringBuilder.append("\taddi $sp, $sp 4\t# Mult\n");
        stringBuilder.append("\tlw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp 4\n");
        stringBuilder.append("\tlw $t8, ($sp)\n");
        stringBuilder.append("\tmult $v0, $t8\n");
        stringBuilder.append("\tmflo $v0\n");
        stringBuilder.append("\tsw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp, -4\n");

        return stringBuilder.toString();
    }
}
