package yal.arbre.instruction;

import yal.arbre.ArbreAbstrait;
import yal.arbre.Type;
import yal.arbre.expression.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Conditionnel extends Instruction{

    ArbreAbstrait si;
    ArbreAbstrait non;
    Expression cond;

    public Conditionnel(int no, Expression pcondition, ArbreAbstrait psi, ArbreAbstrait pnon){

        super(no);
        si = psi;
        non = pnon;
        cond = pcondition;
    }

    public void setBloc(int numbloc){bloc = numbloc; si.setBloc(numbloc); non.setBloc(numbloc);cond.setBloc(numbloc);}


    @Override
    public void verifier() {

        if(cond.getType() != Type.booleen){

            throw new AnalyseSemantiqueException(noLigne,"Le type de l'expression d'une condition doit etre : Boolean");
        }
        cond.verifier();
        si.verifier();

        if(non != null) {

            non.verifier();
        }


    }

    @Override
    public String toMIPS() {

        StringBuilder sb = new StringBuilder();

        int numSi = nbSi;
        nbSi++;

        sb.append("\t#debut condition\n");
        sb.append(cond.toMIPS());
        sb.append("\taddi $sp, $sp 4\n");
        sb.append("\tlw $v0, ($sp)\n");
        sb.append("\t#test condition\n");
        sb.append("\tbeq $v0,$zero,condition" + numSi + "Else\n");
        sb.append(si.toMIPS());
        sb.append("\t j condition" + numSi + "\n");
        sb.append("\t#else\n");
        sb.append("condition" + numSi + "Else:\n");

        if(non != null) {

            sb.append(non.toMIPS());
        }
        sb.append("condition" + numSi + ":\n");
        sb.append("\t#fin condition\n");
        return sb.toString();
    }

    public ArbreAbstrait getSi() {
        return si;
    }

    public ArbreAbstrait getNon() {
        return non;
    }
}
