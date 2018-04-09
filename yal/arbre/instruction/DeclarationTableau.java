package yal.arbre.instruction;

import yal.arbre.Type;
import yal.arbre.expression.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.*;

public class DeclarationTableau extends Instruction {

    private Expression tailleTableau;
    private Entree nom;

    protected DeclarationTableau(Entree n, int no, Expression taille) {

        super(no);
        nom = n;
        tailleTableau = taille;
    }

    @Override
    public void verifier() {

        if(tailleTableau.getType() != Type.entier) {

            throw new AnalyseSemantiqueException(noLigne, "Déclaration de tableau: entier attendu");
        } else {

            tailleTableau.verifier();
        }

        TableDesSymboles tds = TableDesSymboles.getInstance();

        tds.ajouter(nom,new Symbole(TypeTDS.Variable, tds.getTailleZoneVariable() + 1), noLigne);
    }

    @Override
    public String toMIPS() {

        //TODO: Fonction toMIPS de la classe DeclarationTableau
        return null;
    }
}
