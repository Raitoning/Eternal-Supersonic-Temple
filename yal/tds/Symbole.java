package yal.tds;

public abstract class Symbole {

    private TypeTDS typeTDS;

    public Symbole(TypeTDS t) {
        this.typeTDS = t;
    }

    public TypeTDS getTypeTDS() {
        return typeTDS;
    }

}
