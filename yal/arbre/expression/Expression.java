package yal.arbre.expression;

import yal.arbre.ArbreAbstrait;
import yal.arbre.Type;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class Expression extends ArbreAbstrait {

    protected Expression(int n) {

        super(n) ;
    }

    @Override
    public abstract void verifier();

    @Override
    public abstract String toMIPS();

    public abstract Type getType();

    public void setBloc(int num){bloc = num;}
}
