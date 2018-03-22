package yal.tds;

public class SymboleVariable extends Symbole{


    private int adr;

    public SymboleVariable(int adr) {
        super(TypeTDS.Variable);
        this.adr = adr;
    }

    public int getAdr() {
        return adr;
    }
}
