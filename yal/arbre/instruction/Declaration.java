package yal.arbre.instruction;

import yal.exceptions.AnalyseSemantiqueException;
import yal.tds.*;

import java.util.ArrayList;

public class Declaration extends Instruction{

    private Entree nom;

    public Declaration(Entree nom, int no) {

        super(no);
        this.nom = nom;
    }

    public void setBloc(int numbloc){bloc = numbloc;}

    public void verifier() {

        Entree ev;
        if(bloc != 0)
            ev = new EntreeVariable(nom.getNom(),bloc);
        else ev = nom;

        TableDesSymboles tds = TableDesSymboles.getInstance();

        if(tds.existe(ev)){

            throw new AnalyseSemantiqueException(noLigne,"Erreur: double " +
                    "déclaration");
        } else {
            if(bloc == 0)
                tds.ajouter(ev,new SymboleVariable(
                    tds.getTailleZoneVariable()+1), noLigne);
            else{

                tds.ajouter(ev,new SymboleVariable(tds.getTailleBloc(bloc)
                        ), noLigne);
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
