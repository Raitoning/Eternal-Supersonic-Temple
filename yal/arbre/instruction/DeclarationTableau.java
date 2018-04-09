package yal.arbre.instruction;

import yal.arbre.Type;
import yal.arbre.expression.ConstanteEntiere;
import yal.arbre.expression.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.*;

public class DeclarationTableau extends Instruction {

    private Expression tailleTableau;
    private Entree nom;
    private boolean dynamique;

    public DeclarationTableau(Entree n, int no, Expression taille, boolean dyn) {

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
        ConstanteEntiere tmp = (ConstanteEntiere)tailleTableau;

        if(dynamique) {

            tds.ajouter(nom,new Symbole(TypeTDS.TableauDynamique, tds.getTailleZoneVariable() + 1), tmp.toInt());
        } else {

            tds.ajouter(nom,new Symbole(TypeTDS.Tableau, tds.getTailleZoneVariable() + 1), tmp.toInt());

        }

    }

    @Override
    public String toMIPS() {

        //TODO: Fonction toMIPS de la classe DeclarationTableau
        return null;
    }
}
