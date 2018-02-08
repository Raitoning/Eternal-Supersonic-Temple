package yal.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Superieur extends Comparaison {

    public Superieur(Expression gauche, Expression droite) {

        super(gauche, droite);
    }

    @Override
    public String operateur() {

        return " > ";
    }

    @Override
    public String toMIPS() {

        nbComp++;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(droite.toMIPS());
        stringBuilder.append(gauche.toMIPS());

        stringBuilder.append("\n");
        stringBuilder.append("\taddi $sp, $sp 4\t# Superieur\n");
        stringBuilder.append("\tlw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp 4\n");
        stringBuilder.append("\tlw $t8, ($sp)\n");

        stringBuilder.append("\tsub $v0, $v0, $t8\n");

        stringBuilder.append("\tbgtz $v0," + "comp" + nbComp + "\n");
        stringBuilder.append("\tli $v0, 0\n");
        stringBuilder.append("\tj finComp" + nbComp + "\n");
        stringBuilder.append("\tcomp" + nbComp + ":\n");
        stringBuilder.append("\tli $v0, -1\n");
        stringBuilder.append("\tfinComp" + nbComp +":\n");
        stringBuilder.append("\tsw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp, -4\n");

        return stringBuilder.toString();
    }
}
