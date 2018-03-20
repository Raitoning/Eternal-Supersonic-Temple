package yal.exceptions;

public class VariableNonDefinieException extends AnalyseSemantiqueException {

    public VariableNonDefinieException(int ligne, String m) {

        super(ligne, "Variable non définie: " + m);
    }
}
