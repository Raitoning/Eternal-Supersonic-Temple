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

    @Override
    public void verifier() {
        if(cond.getType() != Type.booleen){
            throw new AnalyseSemantiqueException(noLigne,"Le type de l'expression d'une condition doit etre : Boolean");
        }
        cond.verifier();
        si.verifier();
        non.verifier();

    }

    @Override
    public String toMIPS() {

        StringBuilder sb = new StringBuilder();
        nbSi++;
        sb.append("\t#debut condition");
        sb.append(cond.toMIPS());
        sb.append("\taddi $sp, $sp 4\n");
        sb.append("\tlw $v0, ($sp)\n");
        sb.append("\t#test condition");
        sb.append("\tbeq $v0,$zero,condition"+nbSi+"Else\n");
        sb.append(si.toMIPS());
        sb.append("\t j condition"+nbSi+"\n");
        sb.append("\t#else");
        sb.append("condition"+nbSi+"Else:\n");
        if(non != null) {
            sb.append(non.toMIPS());
        }
        sb.append("condition"+nbSi+"\n");
        sb.append("\t#fin condition");
        return sb.toString();
    }
}
