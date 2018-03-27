package yal.arbre;

import yal.arbre.instruction.Instruction;

import java.util.ArrayList;

public abstract class ArbreAbstrait {

    /* numéro de ligne du début de l'instruction */
    protected int noLigne ;
    /* Nombre de comparaison pour les nom d'étiquettes */
    static protected int nbComp = 0;
    static protected int nbSi = 0;
    protected static int rec = 0;
    static protected int nbBoucle = 0;
    static protected StringBuilder functionBuilder = new StringBuilder();
    static protected int compteurBloc = 0;
    static protected ArrayList<ArbreAbstrait> instructionsFonctions = new
            ArrayList<ArbreAbstrait>();

    protected int bloc = 0;

    protected ArbreAbstrait(int no) {

        noLigne = no ;
    }

    public int getNoLigne() {

            return noLigne ;
    }

    public abstract void verifier();

    public abstract String toMIPS();

    public void setBloc(int numbloc){bloc = numbloc;}

    public int getVariables(){
        return 0;
    }

}
