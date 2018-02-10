package yal.arbre.instruction;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expression.Expression;

public class Affectation extends Instruction {

    private String nom;
    private Expression valeur;

    public Affectation(String nom, Expression valeur, int no) {

        super(no);
        this.nom = nom;
        this.valeur = valeur;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {

        return null;
    }

    @Override
    public String toString() {

        return nom + " = " + valeur;
    }
}
