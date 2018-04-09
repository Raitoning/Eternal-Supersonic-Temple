package yal.tds;

public class Symbole {

    private TypeTDS typeTDS;
    private int adr;
    private int tailleTableau;

    public Symbole(TypeTDS typeTDS, int padr) {

        this.typeTDS = typeTDS;
        adr = padr;
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
