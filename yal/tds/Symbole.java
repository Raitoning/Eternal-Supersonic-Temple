package yal.tds;

public abstract class Symbole {

    private TypeTDS typeTDS;
    private int adr;
    private int tailleTableau;
  
    protected boolean estParametre;

    public Symbole(TypeTDS t) {
        this.typeTDS = t;
        estParametre=false;
    }

    public Symbole(TypeTDS typeTDS, int padr, int taille) {

        this(typeTDS, padr);
        tailleTableau = taille;
    }

    public TypeTDS getTypeTDS() {
        return typeTDS;
    }
  
    public int getAdr() {
        return adr;
    }

    public int getTailleTableau() {

        return tailleTableau;
    }
}
