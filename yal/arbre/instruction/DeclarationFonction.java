package yal.arbre.instruction;

import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.arbre.expression.Expression;
import yal.exceptions.DoubleDeclarationException;
import yal.tds.*;

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

        if(TableDesSymboles.getInstance().existe(nom)) {

            throw new DoubleDeclarationException(nom.getNom());
        } else {

            TableDesSymboles.getInstance().ajouter(new EntreeFonction(nom
                    .getNom()), new Symbole(TypeTDS.Fonction, 0));
        }
        instructions.verifier();
        exp.verifier();

        //TODO stocker dans TDS
        //TODO verifier nom fonction

    }

    // TODO: Ajouter création pile fonction.
    @Override
    public String toMIPS() {

        ArbreAbstrait.functionBuilder.append(nom.getNom() + ":\n\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, -4 \n");
        ArbreAbstrait.functionBuilder.append("\t");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, -4 \n");
        ArbreAbstrait.functionBuilder.append("\t");
        ArbreAbstrait.functionBuilder.append(instructions.toMIPS()+"\n");
        ArbreAbstrait.functionBuilder.append("\n");

/*
        stringBuilder.append("\taddi $sp, $sp 4\t# Plus\n");
        stringBuilder.append("\tlw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp 4\n");
        stringBuilder.append("\tlw $t8, ($sp)\n");
        stringBuilder.append("\tadd $v0, $v0, $t8\n");
        stringBuilder.append("\tsw $v0, ($sp)\n");
        stringBuilder.append("\taddi $sp, $sp, -4\n");*/




        //chainage
        ArbreAbstrait.functionBuilder.append("");//adr retour
        ArbreAbstrait.functionBuilder.append(exp.toMIPS()+"\n");
        //jump retour
        ArbreAbstrait.functionBuilder.append("\n");

        return "";
    }
}
