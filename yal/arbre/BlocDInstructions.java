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
        stringBuilder.append("\tmsgTrue:\t.asciiz \"vrai\"\n\n");
        stringBuilder.append("\tmsgFalse:\t.asciiz \"faux\"\n\n");
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
        stringBuilder.append("\tli $v0, 4\n");
        stringBuilder.append("\tla $a0, msgDivZero\n");
        stringBuilder.append("\tsyscall\n");
        stringBuilder.append("\tj end\n");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("printEntier:\n");
        stringBuilder.append("\tmove $a0, $v0\t# copie de v0 dans a1 pour " +
                "permettre l'affichage\n");
        stringBuilder.append("\tmove $t8, $v0\n");
        stringBuilder.append("\tli $v0, 1\n");
        stringBuilder.append("\tsyscall\n");
        stringBuilder.append("\taddi $a0, $0, 0xA\t#pour sauter une ligne\n");
        stringBuilder.append("\tli $v0, 11\n");
        stringBuilder.append("\tsyscall\n");
        stringBuilder.append("\tjr $ra\n");
        //stringBuilder.append("\tla $a0, msgDivZero\n");
        stringBuilder.append("\n");
        stringBuilder.append("\n");
        stringBuilder.append("printBool:\n");
        stringBuilder.append("\t beq $v0, $0, printFalse\n");
        stringBuilder.append("\tla $a0, msgTrue\t#Ici on affiche vrai\n");
        stringBuilder.append("\tj printBool2\n");
        stringBuilder.append("printFalse:\n");
        stringBuilder.append("\tla $a0, msgFalse\t#Ici on affiche faux\n");
        stringBuilder.append("printBool2:\n");
        stringBuilder.append("\tli $v0, 4\n");
        stringBuilder.append("\tsyscall\n");
        stringBuilder.append("\taddi $a0, $0, 0xA\t#pour sauter une ligne\n");
        stringBuilder.append("\tli $v0, 11\n");
        stringBuilder.append("\tsyscall\n");
        stringBuilder.append("\tjr $ra\n");

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
