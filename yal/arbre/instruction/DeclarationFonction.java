package yal.arbre.instruction;

import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.tds.Entree;

public class DeclarationFonction extends Instruction{

    private Entree nom;
    private int numBloc;
    private ArbreAbstrait instructions;

    public DeclarationFonction(int no, Entree n, int bloc,
                                  ArbreAbstrait instr) {

        super(no);

        nom = n;
        numBloc = bloc;
        instructions = instr;
    }

    @Override
    public void verifier() {


    }

    @Override
    public String toMIPS() {
        return null;
    }
}
