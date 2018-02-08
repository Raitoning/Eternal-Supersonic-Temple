package yal.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class EtLogique extends BinaireLogique {

    public EtLogique(Expression gauche, Expression droite) {

        super(gauche, droite);
    }

    @Override
    public String operateur() {

        return " et " ;
    }

    @Override
    public String toMIPS() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(gauche.toMIPS());
        stringBuilder.append(droite.toMIPS());
        stringBuilder.append("\n");
        stringBuilder.append("\taddi $sp, $sp 4\t# Et logique\n");
        stringBuilder.append("\tlw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp 4\n");
        stringBuilder.append("\tlw $t8, ($sp)\n");
        stringBuilder.append("\tand $v0, $v0, $t8\n");
        stringBuilder.append("\tsw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp, -4\n");

        return stringBuilder.toString();
    }
}
