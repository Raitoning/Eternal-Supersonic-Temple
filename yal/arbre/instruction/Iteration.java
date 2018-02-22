package yal.arbre.instruction;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expression.Expression;

public class Iteration extends Instruction {

    private Expression condition;
    private ArbreAbstrait instructions;

    public Iteration(int no, Expression expression, ArbreAbstrait arbreAbstrait) {

        super(no);

        condition = expression;
        instructions = arbreAbstrait;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {

        return null;
    }
}
