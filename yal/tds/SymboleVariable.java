package yal.tds;

public class SymboleVariable extends Symbole{

    private int adr;
    private int tailleTableau;

    public SymboleVariable(int adr) {

        super(TypeTDS.Variable);
        this.adr = adr;
    }

    public SymboleVariable(TypeTDS t, int padr) {

        super(t);
        adr = padr;
        estParametre=false;
    }

    public SymboleVariable(int adr, boolean parametre) {

        super(TypeTDS.Variable);
        this.adr = adr;
        estParametre = parametre;
    }

    public SymboleVariable(TypeTDS typeTDS, int padr, int taille) {

        super(typeTDS);
        adr = padr;
        tailleTableau = taille;
    }

    public int getAdr() {
        if(estParametre)return adr; else return adr+1;
    }

    public boolean getParam(){return estParametre;}

    public int getTailleTableau() {

        return tailleTableau;
    }
}
