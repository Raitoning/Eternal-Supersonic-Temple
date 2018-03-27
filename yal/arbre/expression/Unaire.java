package yal.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class Unaire extends Expression {
    public void setBloc(int num){bloc = num; expression.setBloc(num);}

    protected Expression expression ;

    protected Unaire(Expression expr) {

        super(expr.getNoLigne());
        expression = expr ;
    }

    public abstract String operateur() ;

    @Override
    public String toString() {

        return "(" + operateur() + expression + ")" ;
    }
}
