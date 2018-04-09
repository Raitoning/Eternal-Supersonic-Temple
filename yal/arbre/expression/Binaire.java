package yal.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class Binaire extends Expression {

    public void setBloc(int num){bloc = num; gauche.setBloc(num); droite.setBloc(num);}

    protected Expression gauche ;
    protected Expression droite ;

    protected Binaire(Expression gauche, Expression droite) {

        super(gauche.getNoLigne());
        this.gauche = gauche;
        this.droite = droite;
    }

    public abstract String operateur() ;

    @Override
    public String toString() {

        return "(" + gauche + operateur() + droite + ")" ;
    }
}
