%{
#include<stdio.h>
int ch=0, bl=0, ln=0, wr=0;
%}

op [+*/=-]
letter [a-zA-Z]

%%
{op}                                    	 {printf("\n This is operator            : =   %s\n",yytext);}
("main")|("System.out.println")                                         {printf("\n This is a System Function   : =   %s\n",yytext);}   
("public"|"static"|"class")  			 {printf("\n This is a reserved keyword  : =   %s\n ",yytext); } 
("{")|("}")|("(")|(")")             	 	 {printf("\n Symbol or bracket is a      : =   %s\n  ",yytext);}
(";")|(",")                         	 	 {printf("\n This is delimiter           : =   %s\n ",yytext);}
("void")|("int")|("float")|("char")|("String") 	 {printf("\n The Datatype is             : =   %s\n ",yytext);}
{letter}*                               	 {printf("\n Variable is a               : =   %s\n ",yytext);}
.     ;
%%

main()
{
yylex();

}

int yywrap()
{
return 1;
}


 
