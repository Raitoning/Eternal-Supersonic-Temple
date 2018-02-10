package yal.arbre.instruction;

import yal.arbre.ArbreAbstrait;

public class Declaration extends Instruction{

    private String nom;

    public Declaration(String nom, int no) {

        super(no);
        this.nom = nom;
    }

    public void verifier() {


    }

    @Override
    public String toMIPS() {

        return null;
    }

    @Override
    public String toString() {

        return "entier " + nom;
    }
}
