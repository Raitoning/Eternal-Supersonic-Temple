package yal.analyse ;

import java_cup.runtime.*;
import yal.exceptions.AnalyseLexicaleException;

%%

%class AnalyseurLexical
%public

%line
%column

%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{
    private Symbol symbol(int typeTDS) {

        return new Symbol(typeTDS, yyline, yycolumn) ;
    }

    private Symbol symbol(int typeTDS, Object value) {

        return new Symbol(typeTDS, yyline, yycolumn, value) ;
    }
%}

commentaireSlashSlash = [/][/].*

idf = [a-zA-Z][a-zA-Z0-9]*

csteE = [0-9]+
csteB = "vrai" | "faux"
chain = [\"][^\"]*[\"]

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]

%%

"+"                         { return symbol(CodesLexicaux.PLUS); }
"-"                         { return symbol(CodesLexicaux.MOINS); }
"*"                         { return symbol(CodesLexicaux.MULT); }
"/"                         { return symbol(CodesLexicaux.DIV); }

"="                         { return symbol(CodesLexicaux.EGAL); }

"=="                        { return symbol(CodesLexicaux.EGALEGAL); }
"!="                        { return symbol(CodesLexicaux.DIFF); }
"<"                         { return symbol(CodesLexicaux.INF); }
">"                         { return symbol(CodesLexicaux.SUP); }

"et"                        { return symbol(CodesLexicaux.ET); }
"ou"                        { return symbol(CodesLexicaux.OU); }
"non"                       { return symbol(CodesLexicaux.NON); }
"si"                        { return symbol(CodesLexicaux.SI); }
"alors"                     { return symbol(CodesLexicaux.ALORS);}
"sinon"                     { return symbol(CodesLexicaux.SINON);}
"fsi"                       { return symbol(CodesLexicaux.FSI); }

"("                         { return symbol(CodesLexicaux.PAROUV); }
")"                         { return symbol(CodesLexicaux.PARFER); }
"["                         { return symbol(CodesLexicaux.CROOUV); }
"]"                         { return symbol(CodesLexicaux.CROFER); }

";"                         { return symbol(CodesLexicaux.POINTVIRGULE); }
","                         { return symbol(CodesLexicaux.VIRGULE); }

{csteE}                     { return symbol(CodesLexicaux.CONSTANTEINT, yytext()); }
{csteB}                     { return symbol(CodesLexicaux.CONSTANTEBOOL, yytext()); }

"programme"                 { return symbol(CodesLexicaux.PROGRAMME); }

"ecrire"                    { return symbol(CodesLexicaux.ECRIRE); }
"lire"                      { return symbol(CodesLexicaux.LIRE); }
"debut"                     { return symbol(CodesLexicaux.DEBUT); }
"fin"                       { return symbol(CodesLexicaux.FIN); }

"entier"                    { return symbol(CodesLexicaux.ENTIER); }

"tantque"                   { return symbol(CodesLexicaux.TANTQUE); }
"repeter"                   { return symbol(CodesLexicaux.REPETER); }
"fintantque"                { return symbol(CodesLexicaux.FINTANTQUE); }

"fonction"                  { return symbol(CodesLexicaux.FONCTION); }
"retourne"                  { return symbol(CodesLexicaux.RETOURNE); }

{csteE}                     { return symbol(CodesLexicaux.CONSTANTEINT, yytext()); }
{csteB}                     { return symbol(CodesLexicaux.CONSTANTEBOOL, yytext()); }
{chain}                     { return symbol(CodesLexicaux.CHAINE, yytext()); }
{idf}                       { return symbol(CodesLexicaux.IDF, yytext()); }

{espace}                    { }

{commentaireSlashSlash}     {}

. { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }