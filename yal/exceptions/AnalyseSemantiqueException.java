package yal.exceptions;

public class AnalyseSemantiqueException extends AnalyseException {

    public AnalyseSemantiqueException(int ligne, String m) {

        super("ERREUR SEMANTIQUE :\n\tligne " + ligne + "\n\t" + m) ;
    }
}
