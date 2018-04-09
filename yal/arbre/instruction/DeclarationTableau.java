package yal.arbre.instruction;

import javafx.scene.control.Tab;
import yal.arbre.Type;
import yal.arbre.expression.ConstanteEntiere;
import yal.arbre.expression.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.*;

public class DeclarationTableau extends Instruction {

    private Expression tailleTableau;
    private EntreeVariable nom;
    private boolean dynamique;

    public DeclarationTableau(EntreeVariable n, int no, Expression taille, boolean dyn) {

        super(no);
        nom = n;
        tailleTableau = taille;
        dynamique = dyn;
    }

    @Override
    public void verifier() {

        if(tailleTableau.getType() != Type.entier) {

            throw new AnalyseSemantiqueException(noLigne, "Déclaration de tableau: entier attendu");
        } else {

            tailleTableau.verifier();
        }

        TableDesSymboles tds = TableDesSymboles.getInstance();

        // HACK: Pour récupérer la taille du tableau à partir de la String cste
        if(tailleTableau.isConstanteEntiere()) {

            ConstanteEntiere tmp = (ConstanteEntiere)tailleTableau;

            tds.ajouter(nom,new SymboleVariable(TypeTDS.Tableau, tds.getTailleZoneVariable()), tmp.toInt());
        } else {
            if (dynamique){
                throw new AnalyseSemantiqueException(noLigne, "Déclaration de tableau: constante attendue");
            }

            tds.ajouter(nom,new SymboleVariable(TypeTDS.TableauDynamique, tds.getTailleZoneVariable()), 0);
        }
    }

    @Override
    public String toMIPS() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t# Déclaration tableau\n");

        if(dynamique) {

            stringBuilder.append("\t# Tableau dynamique\n");

        } else {

            stringBuilder.append("\t# Tableau statique\n");

            stringBuilder.append("\taddi $v0, $zero, "+ tailleTableau +"\n");
            stringBuilder.append("\t sw $v0,"+ (-4 * TableDesSymboles.getInstance().identifier(nom, noLigne).getAdr() - 1) + "($s7)\n");
        }

        return stringBuilder.toString();
    }
}
