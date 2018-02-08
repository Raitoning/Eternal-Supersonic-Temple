package yal.arbre;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected ArbreAbstrait expr ;

    public BlocDInstructions(int n) {

        super(n) ;
    }

    public void ajouter(ArbreAbstrait a) {

        expr = a ;
    }

    public String toMIPS() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(".data\n");
        stringBuilder.append("\tmsgDivZero:\t.asciiz \"Erreur division par " +
                "zero\"\n\n");
        stringBuilder.append(".text\n");
        stringBuilder.append("main:\n\n");

        stringBuilder.append(expr.toMIPS());

        stringBuilder.append("\nend:\n\n");
        stringBuilder.append("\tmove $v1, $v0\t# copie de v0 dans v1 pour " +
                "permettre les tests\n");
        stringBuilder.append("\tli $v0, 10\t# retour au système\n");
        stringBuilder.append("\tsyscall\n");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("divZero:\n");
        stringBuilder.append("\n");
        stringBuilder.append("\tli $v0, 55\n");
        stringBuilder.append("\tla $a0, msgDivZero\n");
        stringBuilder.append("\tli $a1, 0\n");
        stringBuilder.append("\tsyscall\n");
        stringBuilder.append("\tj end\n");

        return stringBuilder.toString();
    }

    @Override
    public String toString() {

        return expr.toString() ;
    }

    public void verifier() {

        expr.verifier();
    }
}
