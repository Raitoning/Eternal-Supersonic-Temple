package yal.tds;

public class SymboleVariable extends Symbole{


    private int adr;
    private boolean estParametre;

    public SymboleVariable(int adr) {
        super(TypeTDS.Variable);
        this.adr = adr;
        estParametre = false;
    }

    public SymboleVariable(int adr, boolean parametre) {
        super(TypeTDS.Variable);
        this.adr = adr;
        estParametre = parametre;
    }

    public int getAdr() {
        return adr;
    }

    public boolean getParam(){return estParametre;}
}
