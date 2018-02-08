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

csteE = [0-9]+
csteB = "vrai" | "faux"

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]

%%

"+"                	        { return symbol(CodesLexicaux.PLUS); }
"-"                	        { return symbol(CodesLexicaux.MOINS); }
"*"                	        { return symbol(CodesLexicaux.MULT); }
"/"                	        { return symbol(CodesLexicaux.DIV); }

"=="                        { return symbol(CodesLexicaux.EGALEGAL); }
"!="                        { return symbol(CodesLexicaux.DIFF); }
"<"                	        { return symbol(CodesLexicaux.INF); }
">"                	        { return symbol(CodesLexicaux.SUP); }

"et"                	    { return symbol(CodesLexicaux.ET); }
"ou"                	    { return symbol(CodesLexicaux.OU); }
"non"                	    { return symbol(CodesLexicaux.NON); }

"("                	        { return symbol(CodesLexicaux.PAROUV); }
")"                	        { return symbol(CodesLexicaux.PARFER); }

{csteE}      	            { return symbol(CodesLexicaux.CONSTANTEINT, yytext()); }
{csteB}      	            { return symbol(CodesLexicaux.CONSTANTEBOOL, yytext()); }

{espace}                    { }

{commentaireSlashSlash}     {}

.                           { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }
