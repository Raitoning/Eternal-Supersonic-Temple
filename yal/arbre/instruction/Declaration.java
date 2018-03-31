package yal.arbre.instruction;

import yal.arbre.Type;
import yal.arbre.expression.Expression;
import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.*;

import java.util.ArrayList;

public class Declaration extends Instruction{

    private Entree nom;
    private boolean tableau;
    private Expression expr;

    public Declaration(Entree nom, int no) {
        super(no);
        this.tableau = false;
        this.nom = nom;
    }

    public Declaration(Entree nom, int no, Expression exp){
        super(no);
        this. nom = nom;
        this.tableau = true;
        expr = exp;
    }

    public boolean estTableau(){
        return tableau;
    }

    public void setBloc(int numbloc){bloc = numbloc;}

    public void verifier() {

        if (!tableau) {
            Entree ev;
            if (bloc != 0)
                ev = new EntreeVariable(nom.getNom(), bloc);
            else ev = nom;

            TableDesSymboles tds = TableDesSymboles.getInstance();

            if (tds.existe(ev)) {

                throw new AnalyseSemantiqueException(noLigne, "Erreur: double " +
                        "déclaration");
            } else {
                if (bloc == 0)
                    tds.ajouter(ev, new SymboleVariable(
                            tds.getTailleZoneVariable() + 1), noLigne);
                else {

                    tds.ajouter(ev, new SymboleVariable(tds.getTailleBloc(bloc)
                    ), noLigne);
                }

            }
        } else {
            //FIXME:Determiner si on est dans une variable locale ou globale
            //TODO:Verifier tableau

            expr.verifier();
            if (expr.getType() != Type.entier) {

                throw new AnalyseSemantiqueException(noLigne, "L'expression " +
                        "doit être arithmetique.");
            }

        }
    }

    @Override
    public String toMIPS() {

        return "";
    }

    @Override
    public String toString() {

        return "entier " + nom;
    }
}
