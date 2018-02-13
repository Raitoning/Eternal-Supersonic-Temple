package yal.arbre;

import yal.tds.TableDesSymboles;

import java.util.ArrayList;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected ArrayList<ArbreAbstrait> expr ;

    public BlocDInstructions(int n) {

        super(n) ;
        expr = new ArrayList<ArbreAbstrait>();
    }

    public void ajouter(ArbreAbstrait a) {

        expr.add(a) ;
    }

    public String toMIPS() {

        TableDesSymboles tds = TableDesSymboles.getInstance();

        StringBuilder stringBuilder = new StringBuilder();

        if(noLigne == 1) {

            stringBuilder.append(".data\n");
            stringBuilder.append("\tmsgDivZero:\t.asciiz \"Erreur division " +
                    "par zero\"\n\n");
            stringBuilder.append(".text\n");
            stringBuilder.append("main:\n\n");
            stringBuilder.append("\tmove $s7, $sp\n");
            stringBuilder.append("\taddi $sp, $sp, -" + (4 * tds
                    .getTailleZoneVariable()) + "\n");

            stringBuilder.append("\n");
        }



        if(!expr.isEmpty()) {

            for (ArbreAbstrait abstrait: expr) {

                stringBuilder.append(abstrait.toMIPS());
            }
        }


        if(noLigne == 1) {
            stringBuilder.append("\nend:\n\n");
            stringBuilder.append("\tmove $v1, $v0\t# copie de v0 dans v1 " +
                    "pour permettre les tests\n");
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
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {

        if(expr != null) {

            return expr.toString();
        }

        else return "";
    }

    public void verifier() {

        if(!expr.isEmpty()) {

            for (ArbreAbstrait abstrait: expr) {

                abstrait.verifier();
            }
        }
    }
}
