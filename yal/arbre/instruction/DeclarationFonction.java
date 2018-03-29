package yal.arbre.instruction;

import yal.arbre.ArbreAbstrait;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.DoubleDeclarationException;
import yal.tds.*;

import java.util.ArrayList;

public class DeclarationFonction extends Instruction{

    private EntreeFonction nom;
    private ArbreAbstrait instructions;
    private int numBloc;
    private ArrayList<String> parametres;
    private int numVar;

    public DeclarationFonction(int ligne, EntreeFonction n, ArbreAbstrait instr)
                                {

        super(ligne);

        nom = n;
        instructions = instr;
        compteurBloc++;
        numBloc = compteurBloc;
        parametres = new ArrayList<String>();
        numVar =0;
    }

    public DeclarationFonction(int ligne, EntreeFonction n, ArbreAbstrait instr, ArrayList<String> p)
    {

        super(ligne);

        nom = n;
        instructions = instr;
        compteurBloc++;
        numBloc = compteurBloc;

        parametres = p;
        numVar = 0;
    }

    public void setVariables(int n){
        numVar = n;
    }

    @Override
    public void verifier() {

        if(TableDesSymboles.getInstance().existe(nom)) {
            throw new DoubleDeclarationException(noLigne, nom.getNom() + "()");
        } else {

            TableDesSymboles.getInstance().ajouter(new EntreeFonction(nom
                    .getNom()), new SymboleFonction(0, parametres.size()), noLigne);

            //ajouter les parametres

            TableDesSymboles tds = TableDesSymboles.getInstance();
            EntreeVariable v;
            int tailleBloc = 1;
            for(int k = 0; k < parametres.size();k++) {
                v = new EntreeVariable(parametres.get(k), numBloc);

                if(tds.existe(v)){
                    throw new AnalyseSemantiqueException(noLigne,"Erreur: double " +
                            "déclaration d'un parametre");
                } else {
                    tds.ajouter(v,new SymboleVariable(
                            tailleBloc,true), noLigne);
                    tailleBloc++;
                }
            }
        }

        if(instructions != null) {
            instructions.setBloc(numBloc);
            instructionsFonctions.add(instructions);
        }
    }

    @Override
    public String toMIPS() {
        ArbreAbstrait.functionBuilder.append(nom.getNom() + ":\n\n");
        for(int h = 0;h < parametres.size();h++){

            ArbreAbstrait.functionBuilder.append("\tlw $v0, "+4*(parametres.size()-h+1)+"($sp)\n");
            ArbreAbstrait.functionBuilder.append("\tsw $v0, "+ (-4*(h+3))+"($sp)\n");
        }


        ArbreAbstrait.functionBuilder.append("\tsw $ra, ($sp)\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, -4 \n");
        ArbreAbstrait.functionBuilder.append("\tsw $s7, ($sp)\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, -4\n");
        ArbreAbstrait.functionBuilder.append("\tli $v0,"+numBloc+"\n");
        ArbreAbstrait.functionBuilder.append("\tsw $v0, ($sp)\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, -4\n");
        ArbreAbstrait.functionBuilder.append("\tmove $s7,$sp\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, "+(-4*(parametres.size()+numVar))+"\n");



        if(instructions != null) {

            ArbreAbstrait.functionBuilder.append(instructions.toMIPS()+"\n");
        }
        ArbreAbstrait.functionBuilder.append("\tj noReturn\n");
        ArbreAbstrait.functionBuilder.append("finF"+nom.getNom() + ":\n\n");

        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp, 4\n");
        ArbreAbstrait.functionBuilder.append("\tlw $v0, ($sp)\n");
        ArbreAbstrait.functionBuilder.append("\tsw $v0, 16($s7)\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $sp -4\n");
        ArbreAbstrait.functionBuilder.append("\n");

        ArbreAbstrait.functionBuilder.append("\tlw $ra, 12($s7)\n");
        ArbreAbstrait.functionBuilder.append("\taddi $sp, $s7, +12\n");
        ArbreAbstrait.functionBuilder.append("\tlw $s7, 8($s7)\n");
        //ArbreAbstrait.functionBuilder.append("\tsw $v0, 4($sp)\n");
        ArbreAbstrait.functionBuilder.append("\tjr $ra\n");
        ArbreAbstrait.functionBuilder.append("\t#fin fonction\n");

        ArbreAbstrait.functionBuilder.append("\n");

        return "";
    }
}
