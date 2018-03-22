package yal.tds;

public abstract class Entree {

    protected String nom;

    protected int bloc;

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
}
