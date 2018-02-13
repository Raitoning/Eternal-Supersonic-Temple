package yal.tds;

public class Symbole {

    private TypeTDS typeTDS;
    private int adr;

    public Symbole(TypeTDS typeTDS, int padr) {

        this.typeTDS = typeTDS;
        adr = padr;
    }

    public TypeTDS getTypeTDS() {

        return typeTDS;
    }

    public int getAdr() {
        return adr;
    }
}
