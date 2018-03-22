package yal.tds;

public class SymboleFonction extends Symbole {

    private int numBloc;

    public SymboleFonction(int nB) {
        super(TypeTDS.Fonction);
        this.numBloc = nB;
    }

    public int getNumBloc() {
        return numBloc;
    }
}
