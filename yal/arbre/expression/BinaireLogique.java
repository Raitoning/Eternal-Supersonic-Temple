package yal.arbre.expression;

import yal.arbre.Type;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class BinaireLogique extends Binaire {

    protected BinaireLogique(Expression gauche, Expression droite) {

        super(gauche, droite) ;
    }

    @Override
    public void verifier() {

        if( gauche.getType() != Type.booleen) {

            throw new AnalyseSemantiqueException(noLigne, "Sous expression " +
                    "gauche non booléenne.");

        } else if (droite.getType() != Type.booleen) {

            throw new AnalyseSemantiqueException(noLigne, "Sous expression " +
                    "droite non booléenne.");

        } else {

            gauche.verifier();
            droite.verifier();
        }
    }

    @Override
    public Type getType() {

        return Type.booleen;
    }
}
