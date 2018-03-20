package yal.arbre.instruction;

import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.arbre.expression.Expression;
import yal.exceptions.DoubleDeclarationException;
import yal.tds.*;

public class DeclarationFonction extends Instruction{

    private EntreeFonction nom;
    private ArbreAbstrait exp;
    private ArbreAbstrait instructions;
    private int numBloc;

    public DeclarationFonction(int no, EntreeFonction n, ArbreAbstrait instr,
                               ArbreAbstrait retour) {

        super(no);
        exp = retour;
        nom = n;
        instructions = instr;
        compteurBloc++;
        numBloc = compteurBloc;
    }

    @Override
    public void verifier() {

        if(TableDesSymboles.getInstance().existe(nom)) {

            throw new DoubleDeclarationException(nom.getNom());
        } else {

            TableDesSymboles.getInstance().ajouter(new EntreeFonction(nom
                    .getNom()), new Symbole(TypeTDS.Fonction, 0));
        }
        if(instructions != null)
            instructions.verifier();
        exp.verifier();
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
        //ArbreAbstrait.functionBuilder.append("\tmove $s7, -4($sp)\n\n");
        if(instructions != null)
            ArbreAbstrait.functionBuilder.append(instructions.toMIPS()+"\n");
        ArbreAbstrait.functionBuilder.append(exp.toMIPS()+"\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, 4\n");
        ArbreAbstrait.functionBuilder.append("\tlw $v0, ($sp)\n");
        ArbreAbstrait.functionBuilder.append("\tsw $v0, -4($s7)\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp -4\n");
        ArbreAbstrait.functionBuilder.append("\n");

        /*ArbreAbstrait.functionBuilder.append("\tlw $sp, 12($s7)\n");
        ArbreAbstrait.functionBuilder.append("\tlw $s7, 8($s7)\n");
        ArbreAbstrait.functionBuilder.append("\tmove $ra, $sp\n");
        ArbreAbstrait.functionBuilder.append("\tjr $ra\n");*/
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
