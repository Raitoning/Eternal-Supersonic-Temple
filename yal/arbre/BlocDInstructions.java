package yal.arbre;

import yal.arbre.instruction.fonction.ListeChaines;
import yal.tds.TableDesSymboles;
import java.util.ArrayList;
/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected ArrayList<ArbreAbstrait> expr ;
    private boolean first = false;
    public BlocDInstructions(int n) {
        super(n) ;
        expr = new ArrayList<ArbreAbstrait>();
    }
    public BlocDInstructions(int n, boolean b) {
        super(n) ;
        first = b;
        expr = new ArrayList<ArbreAbstrait>();
    }


    public int getVariables(){
        int res =0;
        if(expr.size() > 1){
            for(int p  =0; p < expr.size();p++)
                res = res + expr.get(p).getVariables();
            return res;
        }
        else
            return 1;
    }

    public void ajouter(ArbreAbstrait a) {

        expr.add(a) ;
    }

    public String toMIPS() {

        TableDesSymboles tds = TableDesSymboles.getInstance();

        StringBuilder stringBuilder = new StringBuilder();

        if(first) {

            stringBuilder.append(".data\n");
            stringBuilder.append("\tmsgDivZero:\t.asciiz \"ERREUR EXECUTION : division " +
                    "par zero\"\n\n");
            stringBuilder.append("\tmsgNoReturn:\t.asciiz \"ERREUR EXECUTION : Aucune " +
                    "valeur de retour\"\n\n");
            stringBuilder.append("\tmsgOutOfBounds:\t.asciiz \"ERREUR EXECUTION : Index Array " +
                    "Out of Bounds : Acces a une case en dehors du tableau\n\n");
            stringBuilder.append("\tmsgTrue:\t.asciiz \"vrai\"\n\n");
            stringBuilder.append("\tmsgFalse:\t.asciiz \"faux\"\n\n");
            addStringData(stringBuilder);
            stringBuilder.append(".text\n");
            stringBuilder.append("main:\n\n");
            stringBuilder.append("\tmove $s7, $sp\n");
            stringBuilder.append("\taddi $sp, $sp, -" + (4 * tds
                    .getTailleZoneVariable()) + "\n");
            stringBuilder.append("\t#numero bloc 0\n");
            stringBuilder.append("\tsw $zero, ($sp)\n");
            stringBuilder.append("\taddi $sp, $sp, -4\n");
            stringBuilder.append("\n");
        }

        if(!expr.isEmpty()) {

            for (ArbreAbstrait abstrait: expr) {

                stringBuilder.append(abstrait.toMIPS());
            }
        }


        if(first) {
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

            stringBuilder.append("noReturn:\n");
            stringBuilder.append("\n");
            stringBuilder.append("\tli $v0, 4\n");
            stringBuilder.append("\tla $a0, msgNoReturn\n");
            stringBuilder.append("\tsyscall\n");
            stringBuilder.append("\tj end\n");
            stringBuilder.append("\n");
            stringBuilder.append("\n");

            stringBuilder.append("outOfBounds:\n");
            stringBuilder.append("\n");
            stringBuilder.append("\tli $v0, 4\n");
            stringBuilder.append("\tla $a0, msgOutOfBounds\n");
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
            stringBuilder.append("\tmove $v0, $t8\n");
            stringBuilder.append("\tjr $ra\n");
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
            stringBuilder.append("\tjr $ra\n");
            stringBuilder.append("\n");

            stringBuilder.append("printString:\t#Assumant qu'on ait défini la string a afficher avant ce jump\n");
            stringBuilder.append("\n");
            stringBuilder.append("\tmove $t8, $v0\n");
            stringBuilder.append("\tli $v0, 4\n");
            stringBuilder.append("\tsyscall\n");
            stringBuilder.append("\tmove $v0, $t8\n");
            stringBuilder.append("\tjr $ra\n");
            stringBuilder.append("\n");
            stringBuilder.append("\n");
            stringBuilder.append(ArbreAbstrait.functionBuilder.toString());
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

            for(ArbreAbstrait arbreAbstrait: instructionsFonctions) {

                if(first) {

                    arbreAbstrait.verifier();
                }
            }
        }
    }

    private void addStringData(StringBuilder sb){
        ListeChaines lc = ListeChaines.getInstance();
        for (int i=0; i<lc.size() ; i++){
            sb.append("\tmsgString"+i+":\t.asciiz "+lc.at(i)+"\n");
        }
    }

    public void setBloc(int numbloc){
        for(int i =0;i < expr.size();i++)
            expr.get(i).setBloc(numbloc);
    }

}
