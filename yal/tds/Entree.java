package yal.tds;

public class Entree {

    private String nom;

    public Entree (String nom) {

        this.nom = nom;
    }

    public String getNom() {

        return this.nom;
    }

    @Override
    public boolean equals(Object obj){

        Entree toTest = (Entree) obj;

        return nom.equals(toTest.getNom());
    }

    @Override
    public int hashCode() {

        return nom.hashCode();
    }
}
