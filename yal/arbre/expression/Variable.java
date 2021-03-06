package yal.arbre.expression;

import yal.arbre.Type;
import yal.tds.Entree;
import yal.tds.Symbole;
import yal.tds.TableDesSymboles;

public class Variable extends Expression{

    protected Entree nom;

    public Variable(Entree n, int no){
        super(no);
        nom = n;
    }


    @Override
    public void verifier() {
        TableDesSymboles tds = TableDesSymboles.getInstance();
        Symbole s = tds.identifier(nom, noLigne);
    }

    @Override
    public String toMIPS() {
        TableDesSymboles tds = TableDesSymboles.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        rec++;
        int numRecup = rec;
        sb.append("\t#recuperation variable\n");
        sb.append("\tmove $t8, $s7\n");
        sb.append("\tloop"+nom.getNom()+ numRecup +":\n");
        sb.append("\tlw $v0, 4($s7)\n");
        sb.append("\tbeq $v0, $zero, recupVar"+nom.getNom()+ numRecup+"\n");
        sb.append("\tlw $s7, 8($s7)\n");
        sb.append("\tj loop"+nom.getNom()+ numRecup +"\n");
        sb.append("\trecupVar"+nom.getNom()+ numRecup +":\n");

        sb.append("\t#stockage\n");
        sb.append("\t\n");
        sb.append("\tlw $v0, "+tds.identifier(nom, noLigne).getAdr()*4+"($s7)\n");
        sb.append("\tsw $v0, ($sp)\n");
        sb.append("\taddi $sp, $sp, -4\n");

        sb.append("\tmove $s7, $t8\n");


        return sb.toString();
    }

    @Override
    public Type getType() {
        return Type.entier;
    }
}

