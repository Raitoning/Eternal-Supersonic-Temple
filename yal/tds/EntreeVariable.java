package yal.tds;

public class EntreeVariable extends Entree {

    public EntreeVariable(String nom) {

        super(nom);
        this.bloc = 0;
    }

    public EntreeVariable(String nom, int bloc){
        super(nom);
        this.bloc = bloc;
    }
    @Override
    public String getNom() {

        return super.getNom();
    }

    @Override
    public boolean equals(Object obj) {

        Entree toTest = (Entree) obj;

        int hash = hashCode();

        return ("variable" + nom).equals(("variable") + toTest.getNom());
    }

    @Override
    public int hashCode() {

        String hash = "variable" +nom;

        return hash.hashCode();
    }
}
