package yal.tds;

public abstract class Symbole {

    private TypeTDS typeTDS;
    private int adr;
  
    protected boolean estParametre;

    public Symbole(TypeTDS t) {
        this.typeTDS = t;
        estParametre=false;
    }

    public TypeTDS getTypeTDS() {
        return typeTDS;
    }
  
    public int getAdr() {
        return adr;
    }


}
