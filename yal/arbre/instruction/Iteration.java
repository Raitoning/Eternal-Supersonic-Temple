package yal.arbre.instruction;

import yal.arbre.ArbreAbstrait;
import yal.arbre.Type;
import yal.arbre.expression.Expression;
import yal.exceptions.AnalyseSemantiqueException;

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

        if(condition.getType() != Type.booleen) {

            throw new AnalyseSemantiqueException(noLigne, "Condition booléenne " +
                    "attendue");
        }

        condition.verifier();
        instructions.verifier();
    }

    @Override
    public String toMIPS() {

       int boucle =  nbBoucle++;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("boucle" + boucle + ":\n");
        stringBuilder.append(condition.toMIPS());
        stringBuilder.append("\taddi $sp, $sp, 4\n");
        stringBuilder.append("\tlw $v0, ($sp)\n");
        stringBuilder.append("\tbeq $v0, $zero, finBoucle" + boucle + "\n");
        stringBuilder.append(instructions.toMIPS());
        stringBuilder.append("\tj boucle" + boucle + "\n");
        stringBuilder.append("finBoucle" + boucle + ":\n");

        return stringBuilder.toString();
    }

    public ArbreAbstrait getInstructions() {
        return instructions;
    }
}
