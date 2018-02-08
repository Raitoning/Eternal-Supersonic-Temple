package yal.arbre.expression;

import yal.arbre.Type;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class BinaireArithmetique extends Binaire {

    protected BinaireArithmetique(Expression gauche, Expression droite) {

        super(gauche, droite) ;
    }

    @Override
    public void verifier() {

        if( gauche.getType() != Type.entier) {

            throw new AnalyseSemantiqueException(noLigne, "Sous expression " +
                    "gauche non arithmetique.");

        } else if (droite.getType() != Type.entier) {

            throw new AnalyseSemantiqueException(noLigne, "Sous expression " +
                    "droite non arithmetique.");

        } else {

            gauche.verifier();
            droite.verifier();
        }
    }

    @Override
    protected Type getType() {

        return Type.entier;
    }
}
