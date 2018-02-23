package yal.arbre;

public abstract class ArbreAbstrait {

    /* numéro de ligne du début de l'instruction */
    protected int noLigne ;
    /* Nombre de comparaison pour les nom d'étiquettes */
    static protected int nbComp = 0;
    static protected int nbSi = 0;

    protected ArbreAbstrait(int no) {

        noLigne = no ;
    }

    public int getNoLigne() {

            return noLigne ;
    }

    public abstract void verifier();

    public abstract String toMIPS();

}
