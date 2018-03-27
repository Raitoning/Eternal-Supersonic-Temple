package yal.arbre.instruction.fonction;

import yal.arbre.instruction.Instruction;
import yal.tds.Entree;
import yal.tds.SymboleVariable;
import yal.tds.TableDesSymboles;

public class Lire extends Instruction{

    private Entree nom;

    public Lire(Entree namae,int no) {
        super(no);
        this.nom = namae;
    }

    @Override
    public void verifier() {
        TableDesSymboles tds = TableDesSymboles.getInstance();
        SymboleVariable s = ((SymboleVariable)tds.identifier(nom, noLigne));
    }

    @Override
    public String toMIPS() {
        TableDesSymboles tds = TableDesSymboles.getInstance();

        StringBuilder sb = new StringBuilder();

        sb.append("#Lecture\n");
        sb.append("\tli $v0, 5\n");//insert read instructions
        sb.append("\tsyscall\n");
        sb.append("\n");
        sb.append("#Affectation qui suit la lecture\n");
        sb.append("\tsw $v0, " + ((SymboleVariable)tds.identifier(nom, noLigne)).getAdr() * 4 + "" +
                "($s7)\n");
        sb.append("\taddi $sp, $sp, -4\n");

        return sb.toString();
    }
    //TODO lire dans fonction
}
