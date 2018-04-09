package yal.tds;

public class SymboleVariable extends Symbole{


    private int adr;

    public SymboleVariable(int adr) {
        super(TypeTDS.Variable);
        this.adr = adr;
    }

    public SymboleVariable(int adr, boolean parametre) {
        super(TypeTDS.Variable);
        this.adr = adr;
        estParametre = parametre;
    }

    public int getAdr() {
        if(estParametre)return adr; else return adr+1;
    }

    public boolean getParam(){return estParametre;}
}
