package yal.tds;

public class SymboleFonction extends Symbole {

    private int numBloc;
    private int nombreParametre;

    public SymboleFonction(int nB, int nbP) {
        super(TypeTDS.Fonction);
        this.numBloc = nB;
        nombreParametre = nbP;
    }

    public int getNumBloc() {
        return numBloc;
    }

    public int getNombreParametre(){
        return nombreParametre;
    }
}
