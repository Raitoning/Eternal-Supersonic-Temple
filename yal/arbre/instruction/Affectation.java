package yal.arbre.instruction;

import yal.arbre.Type;
import yal.arbre.expression.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.Entree;
import yal.tds.Symbole;
import yal.tds.TableDesSymboles;

public class Affectation extends Instruction {

    private Entree nom;
    private Expression valeur;
    private Expression element;

    public Affectation(Entree nom, Expression valeur, int no) {

        super(no);
        this.nom = nom;
        this.valeur = valeur;
    }

    public Affectation(Entree nom, Expression el, Expression valeur, int no) {

        super(no);
        this.nom = nom;
        this.valeur = valeur;
        element = el;
    }

    @Override
    public void verifier() {

        if(valeur.getType() == Type.booleen){
            throw new AnalyseSemantiqueException(noLigne,
                    "Affectation impossible, mauvais type d'expression.");
        }

        if(element != null) {

            if(element.getType() != Type.entier) {

                throw new AnalyseSemantiqueException(noLigne, "Affectation de tableau impossible: entier attendu");
            }

            element.verifier();
        }

        TableDesSymboles tds = TableDesSymboles.getInstance();
        Symbole s = tds.identifier(nom, noLigne);
        valeur.verifier();

    }

    @Override
    public String toMIPS() {

        // TODO: Modifier toMIPS classe Affectation pour tableaux
        TableDesSymboles tds = TableDesSymboles.getInstance();

        StringBuilder sb = new StringBuilder();

        sb.append(valeur.toMIPS());

        sb.append("\n");
        sb.append("#Affectation\n");
        sb.append("\taddi $sp, $sp 4\n");

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

        sb.append("\tlw $v0, ($sp)\n");
        sb.append("\tsw $v0, " + tds.identifier(nom, noLigne).getAdr() * 4 + "" +
                "($s7)\n");
        sb.append("\taddi $sp, $sp, -4\n");

        sb.append("\tmove $s7, $t8\n");

        return sb.toString();
    }

    @Override
    public String toString() {
        return nom + " = " + valeur;
    }
}
