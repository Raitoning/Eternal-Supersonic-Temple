package yal.exceptions;

public class DoubleDeclarationException extends AnalyseSemantiqueException {

    public DoubleDeclarationException(int ligne, String m) {

        super(ligne, "Double déclaration de " + m);
    }
}
