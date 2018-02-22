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

        //TODO
        return null;
    }
}
