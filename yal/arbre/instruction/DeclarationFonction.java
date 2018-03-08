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

        instructions.verifier();
    }

    // TODO: Ajouter création pile fonction.
    @Override
    public String toMIPS() {

        ArbreAbstrait.functionBuilder.append(nom.getNom() + ":\n\n");
        ArbreAbstrait.functionBuilder.append(instructions.toMIPS());
        ArbreAbstrait.functionBuilder.append("\n");

        return "";
    }
}
