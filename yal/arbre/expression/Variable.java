package yal.arbre.expression;

import yal.arbre.Type;
import yal.tds.*;

public class Variable extends Expression{

    protected Entree nom;

    public Variable(Entree n, int no){
        super(no);
        nom = n;
    }


    @Override
    public void verifier() {
        TableDesSymboles tds = TableDesSymboles.getInstance();

        tds.testVariable(nom,bloc,noLigne);
        //SymboleVariable s = ((SymboleVariable)tds.identifier(nom, noLigne));
    }

    @Override
    public String toMIPS() {
        TableDesSymboles tds = TableDesSymboles.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        rec++;
        int numRecup = rec;


        sb.append("\t#recuperation variable\n");

        EntreeVariable sv = new EntreeVariable(nom.getNom(),bloc);
        boolean exi = tds.existe(sv) && bloc != 0;

        if(!exi){
            sb.append("\tmove $t8, $s7\n");
            sb.append("\tloop"+nom.getNom()+ numRecup +":\n");
            sb.append("\tlw $v0, ($s7)\n");
            sb.append("\tbeq $v0, $zero, recupVar"+nom.getNom()+ numRecup+"\n");
            sb.append("\tlw $s7, 8($s7)\n");
            sb.append("\tj loop"+nom.getNom()+ numRecup +"\n");
            sb.append("\trecupVar"+nom.getNom()+ numRecup +":\n");
        }




        sb.append("\t#stockage\n");
        sb.append("\t\n");
        if(!exi) {

            sb.append("\tlw $v0, " + (((SymboleVariable) tds.identifier(nom, noLigne)).getAdr()) * 4 + "($s7)\n");
        }else {
            sb.append("\tlw $v0, " + (((SymboleVariable) tds.identifier(sv, noLigne)).getAdr()-1) * 4 + "($s7)\n");
        }
        sb.append("\tsw $v0, ($sp)\n");
        sb.append("\taddi $sp, $sp, -4\n");

        if(!exi)
            sb.append("\tmove $s7, $t8\n");


        return sb.toString();
    }

    @Override
    public Type getType() {
        return Type.entier;
    }
}

