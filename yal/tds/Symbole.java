package yal.tds;

public abstract class Symbole {

    private TypeTDS typeTDS;
    protected boolean estParametre;

    public Symbole(TypeTDS t) {
        this.typeTDS = t;
        estParametre=false;
    }

    public TypeTDS getTypeTDS() {
        return typeTDS;
    }

}
