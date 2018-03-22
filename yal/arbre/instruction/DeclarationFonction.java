package yal.arbre.instruction;

import yal.arbre.ArbreAbstrait;
import yal.exceptions.DoubleDeclarationException;
import yal.tds.*;

public class DeclarationFonction extends Instruction{

    private EntreeFonction nom;
    private ArbreAbstrait instructions;
    private int numBloc;

    public DeclarationFonction(int ligne, EntreeFonction n, ArbreAbstrait instr)
                                {

        super(ligne);

        nom = n;
        instructions = instr;
        compteurBloc++;
        numBloc = compteurBloc;
    }

    @Override
    public void verifier() {

        if(TableDesSymboles.getInstance().existe(nom)) {

            throw new DoubleDeclarationException(noLigne, nom.getNom() + "()");
        } else {

            TableDesSymboles.getInstance().ajouter(new EntreeFonction(nom
                    .getNom()), new SymboleFonction(0), noLigne);
        }

        if(instructions != null) {

            instructionsFonctions.add(instructions);
        }
    }

    @Override
    public String toMIPS() {

        ArbreAbstrait.functionBuilder.append(nom.getNom() + ":\n\n");
        ArbreAbstrait.functionBuilder.append("\tsw $ra, ($sp)\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, -4 \n");
        ArbreAbstrait.functionBuilder.append("\tsw $s7, ($sp)\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, -4\n");
        ArbreAbstrait.functionBuilder.append("\tli $v0,"+numBloc+"\n");
        ArbreAbstrait.functionBuilder.append("\tsw $v0, ($sp)\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, -4\n");
        ArbreAbstrait.functionBuilder.append("\tmove $s7,$sp\n");

        if(instructions != null) {

            ArbreAbstrait.functionBuilder.append(instructions.toMIPS()+"\n");
        }
        ArbreAbstrait.functionBuilder.append("\tj noReturn\n");
        ArbreAbstrait.functionBuilder.append("finF"+nom.getNom() + ":\n\n");

        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, 4\n");
        ArbreAbstrait.functionBuilder.append("\tlw $v0, ($sp)\n");
        ArbreAbstrait.functionBuilder.append("\tsw $v0, -4($s7)\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp -4\n");
        ArbreAbstrait.functionBuilder.append("\n");

        ArbreAbstrait.functionBuilder.append("\tlw $ra, 12($s7)\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $s7, +12\n");
        ArbreAbstrait.functionBuilder.append("\tlw $s7, 8($s7)\n");
        ArbreAbstrait.functionBuilder.append("\tsw $v0, 4($sp)\n");
        ArbreAbstrait.functionBuilder.append("\tjr $ra\n");
        ArbreAbstrait.functionBuilder.append("\t#fin fonction\n");

        ArbreAbstrait.functionBuilder.append("\n");

        return "";
    }
}
