# Yal - Yet Another Language

Yal est un langage de programmation fonctionnel simple écrit par des étudiants.  
Il permet de faire des opérations arithmétiques et booléennes simples tels que des additions ou de vérifier le résultat d'une opération.  
Il peux également effectuer des conditions Si, des boucles TantQue, lire et écrire des valeurs depuis et vers la sortie standard, ainsi que déclarer des variables de type entière.  

Le langage supporte également les commentaires qui sont précédés d'un double slash _//_.  

La syntaxe du langage variera et s'enrichira en fonction de l'avancement du projet.  

# Le compilateur Yal

La compilation de programmes Yal s'effectue grâce à l'archive Java disponible dans les Release.  

Pour compiler un programme Yal, il suffit d'exécuter cette ligne de commande depuis un terminal ou une invite de commande:  
java -jar yal.jar <NomDuFichier.yal>  

Le résultat de la compilation est un fichier assembleur MIPS, directement exécutable par des émulateurs MIPS tels que MARS, ou par une puce MIPS.  

Le compilateur Yal est écrit en Java 8, et requière un environnement d'exécution Java 8 pour fonctionner.  

# Coder en Yal

Pour coder en Yal, presque aucune connaissance en informatique n'est néccéssaire.  
En effet, il suffit simplement d'écrire une opération mathématique ou booléenne et le langage l'effectue pour vous.  

# Structure du code
La structure du code es très simple et facile à apprendre. Il suffit d'entourer votre programme des balises suivantes: 

programme _NomDuProgramme_ debut

// Programme

fin

_Exemples:_
* 1 + 1
* 2 - 1
* 4 * 2
* 10 / 5
* ( ( ( 1 + 1 ) - 2 ) * 3 ) / 4 )
* vrai == vrai
* faux == vrai
* 5 > 2
* 1 < 3

# Fichiers Yal
L'extension de Yal est .yal. Les fichiers ne sont pas encodés et son éditable depuis n'importe quel éditeur de texte moderne.  
