package yal.tds;

public abstract class Entree {

    protected String nom;

    protected int bloc = -1;

    public Entree (String nom) {

        this.nom = nom;
    }

    public String getNom() {

        return this.nom;
    }

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public int hashCode() {

        return nom.hashCode();
    }

    public int getBloc(){
        return bloc;
    }

}
