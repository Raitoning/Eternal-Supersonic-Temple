package yal.arbre.instruction;

import yal.arbre.ArbreAbstrait;

public abstract class Instruction extends ArbreAbstrait{

    protected Instruction(int no) {
        super(no);
    }

    @Override
    public abstract void verifier();

    @Override
    public abstract String toMIPS();

    public void setBloc(int numbloc){bloc = numbloc; }
}
