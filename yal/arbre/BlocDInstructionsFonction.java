package yal.arbre;

import yal.arbre.expression.Expression;
import yal.arbre.expression.Retourne;
import yal.arbre.instruction.Conditionnel;
import yal.arbre.instruction.Instruction;
import yal.arbre.instruction.Iteration;
import yal.tds.EntreeFonction;

public class BlocDInstructionsFonction extends BlocDInstructions {
    public BlocDInstructionsFonction(int n) {
        super(n);
    }

    public void setName(EntreeFonction name) {
        for (ArbreAbstrait e : expr) {
            if (e instanceof BlocDInstructionsFonction) {
                ((BlocDInstructionsFonction) e).setName(name);
            } else if (e instanceof Retourne) {
                ((Retourne) e).setName(name);
            } else if (e instanceof Conditionnel) {
                if (((Conditionnel) e).getSi() instanceof BlocDInstructionsFonction) {
                    ((BlocDInstructionsFonction) ((Conditionnel) e).getSi()).setName(name);
                }
                if (((Conditionnel) e).getNon() instanceof BlocDInstructionsFonction) {
                    ((BlocDInstructionsFonction) ((Conditionnel) e).getNon()).setName(name);
                }
            } else if (e instanceof Iteration) {
                if (((Iteration) e).getInstructions() instanceof BlocDInstructionsFonction) {
                    ((BlocDInstructionsFonction) ((Iteration) e).getInstructions()).setName(name);
                }
            }
        }
    }

}