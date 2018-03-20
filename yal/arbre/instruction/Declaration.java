package yal.arbre.instruction;

import yal.arbre.ArbreAbstrait;
import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.Entree;
import yal.tds.Symbole;
import yal.tds.TableDesSymboles;
import yal.tds.TypeTDS;

public class Declaration extends Instruction{

    private Entree nom;

    public Declaration(Entree nom, int no) {

        super(no);
        this.nom = nom;
    }

    public void verifier() {

        TableDesSymboles tds = TableDesSymboles.getInstance();

        if(tds.existe(nom)){

            throw new AnalyseSemantiqueException(noLigne,"Erreur: double " +
                    "déclaration");
        } else {

            tds.ajouter(nom,new Symbole(TypeTDS.Variable,
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
