package yal.tds;

public class EntreeFonction extends Entree {

    public EntreeFonction(String nom) {

        super(nom);
    }

    @Override
    public String getNom() {

        return super.getNom();
    }

    @Override
    public boolean equals(Object obj) {

        Entree toTest = (Entree) obj;

        int hash = hashCode();

        return ("fonction" + nom).equals(("fonction") + toTest.getNom());
    }

    @Override
    public int hashCode() {

        String hash = "fonction" + nom;

        return hash.hashCode();
    }
}
