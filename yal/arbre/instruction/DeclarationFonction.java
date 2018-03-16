package yal.arbre.instruction;

import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.arbre.expression.Expression;
import yal.tds.Entree;

public class DeclarationFonction extends Instruction{

    private Entree nom;
    private ArbreAbstrait exp;
    private ArbreAbstrait instructions;
    private int numBloc;

    public DeclarationFonction(int no, Entree n, ArbreAbstrait instr, ArbreAbstrait retour) {

        super(no);
        exp = retour;
        nom = n;
        instructions = instr;
        compteurBloc++;
        numBloc = compteurBloc;
    }

    @Override
    public void verifier() {

        instructions.verifier();
        exp.verifier();



        //TODO stocker dans TDS
        //TODO verifier nom fonction

    }

    // TODO: Ajouter création pile fonction.
    @Override
    public String toMIPS() {

        ArbreAbstrait.functionBuilder.append(nom.getNom() + ":\n\n");
        ArbreAbstrait.functionBuilder.append("\tsw $ra, ($sp)\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, -4 \n");
        ArbreAbstrait.functionBuilder.append("\tsw $s7, $sp\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, -4\n");
        ArbreAbstrait.functionBuilder.append("\tli $v0,"+numBloc+"\n");
        ArbreAbstrait.functionBuilder.append("\tsw $v0, $sp\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, -4\n");

        //TODO : compléter entrée

        ArbreAbstrait.functionBuilder.append(instructions.toMIPS()+"\n");
        ArbreAbstrait.functionBuilder.append("\n");

        //chainage
        //ArbreAbstrait.functionBuilder.append();//adr retour
        ArbreAbstrait.functionBuilder.append(exp.toMIPS()+"\n");
        //jump retour
        ArbreAbstrait.functionBuilder.append("\n");

        return "";
    }
}
