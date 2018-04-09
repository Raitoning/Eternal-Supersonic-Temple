package yal.arbre.instruction;

import yal.arbre.Type;
import yal.arbre.expression.Expression;
import yal.arbre.expression.Variable;
import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.Entree;
import yal.tds.EntreeVariable;
import yal.tds.SymboleVariable;
import yal.tds.TableDesSymboles;

public class Affectation extends Instruction {

    private Entree nom;
    private Expression valeur;

    public Affectation(Entree nom, Expression valeur, int no) {

        super(no);
        this.nom = nom;
        this.valeur = valeur;
    }

    public void setBloc(int numbloc){bloc = numbloc; valeur.setBloc(numbloc);}

    @Override
    public void verifier() {

        if(valeur.getType() == Type.booleen){
            throw new AnalyseSemantiqueException(noLigne,
                    "Affectation impossible, mauvais type d'expression.");
        }

        TableDesSymboles tds = TableDesSymboles.getInstance();

        tds.testVariable(nom,bloc,noLigne);


        valeur.verifier();

        if (tds.testParam(nom,bloc))
            throw new AnalyseSemantiqueException(noLigne,
                    "Affectation impossible, impossible d'affecter une valeur à un parametre.");

    }

    @Override
    public String toMIPS() {

        TableDesSymboles tds = TableDesSymboles.getInstance();

        StringBuilder sb = new StringBuilder();

        sb.append(valeur.toMIPS());




        sb.append("\n");
        sb.append("#Affectation\n");
        sb.append("\taddi $sp, $sp 4\n");

        rec++;
        int numRecup = rec;

        sb.append("\t#recuperation variable\n");

        EntreeVariable sv = new EntreeVariable(nom.getNom(),bloc);
        boolean exi = tds.existe(sv);

        if(!exi){
            sb.append("\tmove $t8, $s7\n");
            sb.append("\tloop"+nom.getNom()+ numRecup +":\n");
            sb.append("\tlw $v0, 4($s7)\n");
            sb.append("\tbeq $v0, $zero, recupVar"+nom.getNom()+ numRecup+"\n");
            sb.append("\tlw $s7, 8($s7)\n");
            sb.append("\tj loop"+nom.getNom()+ numRecup +"\n");
            sb.append("\trecupVar"+nom.getNom()+ numRecup +":\n");
        }


        sb.append("\tlw $v0, ($sp)\n");
        if(!exi)
            sb.append("\tsw $v0, " + ((SymboleVariable)tds.identifier(nom, noLigne)).getAdr() * 4 + "" +
                "($s7)\n");
        else sb.append("\tsw $v0, " + ((SymboleVariable)tds.identifier(sv, noLigne)).getAdr() * 4 + "" +
                "($s7)\n");
        sb.append("\taddi $sp, $sp, -4\n");

        if(!exi)
            sb.append("\tmove $s7, $t8\n");

        return sb.toString();
    }

    @Override
    public String toString() {
        return nom + " = " + valeur;
    }
}
