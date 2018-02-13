package yal ;

import java.io.*;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import yal.analyse.AnalyseurLexical;
import yal.analyse.AnalyseurSyntaxique;
import yal.arbre.ArbreAbstrait;
import yal.exceptions.AnalyseException;

/**
 * 24 mars 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Yal {

    public Yal(String fichier) {

        try {

            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(fichier)));

            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;
            // System.err.println("expression stockée dans l'arbre : " + arbre);

            arbre.verifier() ;

            /* File outputFolder = new File("bin");

            if(!outputFolder.exists()) {

                outputFolder.mkdir();
            }*/

            File sourceFile = new File(fichier);

            /*String fileName = outputFolder.toString() + "/" + sourceFile
                    .getName();*/

            String fileName = sourceFile.getName();
            fileName = fileName.substring(0, fileName.length()-4);
            fileName = fileName.concat(".mips");

            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(arbre.toMIPS());

            bufferedWriter.close();
            fileWriter.close();

        } catch (FileNotFoundException ex) {

            System.err.println("Fichier " + fichier + " inexistant") ;
            System.exit(1);

        } catch (AnalyseException ex) {

            System.err.println(ex.getMessage());
            System.exit(1);

        } catch (Exception ex) {

            Logger.getLogger(Yal.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }

        System.out.println("COMPILATION OK");
    }

    public static void main(String[] args) {

        if (args.length != 1) {

            System.err.println("Nombre incorrect d'arguments") ;
            System.err.println("\tjava -jar yal.jar <fichierSource.yal>") ;
            System.exit(1) ;
        }

        new Yal(args[0]) ;
    }
}
