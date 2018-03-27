package yal.arbre.instruction;

import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.Entree;
import yal.tds.SymboleVariable;
import yal.tds.TableDesSymboles;
import yal.tds.TypeTDS;

public class Declaration extends Instruction{

    private Entree nom;

    public Declaration(Entree nom, int no) {

        super(no);
        this.nom = nom;
    }

    public void setBloc(int numbloc){bloc = numbloc;}

    public void verifier() {

        TableDesSymboles tds = TableDesSymboles.getInstance();

        if(tds.existe(nom)){

            throw new AnalyseSemantiqueException(noLigne,"Erreur: double " +
                    "déclaration");
        } else {

            tds.ajouter(nom,new SymboleVariable(
                    tds.getTailleZoneVariable()+1), noLigne);
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
