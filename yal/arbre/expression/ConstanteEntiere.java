package yal.arbre.expression;

import yal.arbre.Type;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class ConstanteEntiere extends Constante {

    public ConstanteEntiere(String texte, int n) {

        super(texte, n);
    }

    @Override
    public Type getType() {

        return Type.entier;
    }

    public String toMIPS() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\tli $v0, " + cste + "\t# Constante entière\n");

        stringBuilder.append("\tsw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp, -4\n");

        return stringBuilder.toString();
    }

    // HACK: Pour récupérer la taille d'un tableau pour la TDS
    @Override
    public boolean isConstanteEntiere() {
        return true;
    }

    // HACK: Pour convertir la constante String en Integer
    public int toInt() {

        return Integer.parseInt(cste);
    }
}
