package Analizador;
import java_cup.runtime.Symbol; 
%%

%class Yylex
%cup
%public
%line
%char
%state COMMENT

%{
    private int comment_count = 0;
%}

digito = [0-9]
letra = [a-zA-Z]


blanco=[\n\ \t\b]
cadenaTexto=(\\\"|[^\n\"]|\\{blanco}+\\)*
identificador = ({letra})({letra}|{digito}|"_")*
espacio = [ \t]+
saltoLinea = [\n]+
decimal = [0-9]+("."[  |0-9]+)?

%%
<YYINITIAL> "{"  { return (new Symbol(sym.LLA				,yyline,yychar, new Yytoken(yytext(),"{"	,"LLA")));}
<YYINITIAL> "}"  { return (new Symbol(sym.LLC				,yyline,yychar, new Yytoken(yytext(),"}"	,"LLC")));}
<YYINITIAL> "["  { return (new Symbol(sym.CA				,yyline,yychar, new Yytoken(yytext(),"["	,"CA")));}
<YYINITIAL> "]"  { return (new Symbol(sym.CC				,yyline,yychar, new Yytoken(yytext(),"]"	,"CC")));}
<YYINITIAL> "+"  { return (new Symbol(sym.MAS				,yyline,yychar, new Yytoken(yytext(),"+"	,"MAS")));}
<YYINITIAL> "-"  { return (new Symbol(sym.MENOS				,yyline,yychar, new Yytoken(yytext(),"-"	,"MENOS")));}
<YYINITIAL> "*"  { return (new Symbol(sym.MULTIPLICACION	,yyline,yychar, new Yytoken(yytext(),"*"	,"MULTIPLICACION")));}
<YYINITIAL> "/"  { return (new Symbol(sym.DIVISION			,yyline,yychar, new Yytoken(yytext(),"/"	,"DIVISION")));}
<YYINITIAL> "%"  { return (new Symbol(sym.MOD				,yyline,yychar, new Yytoken(yytext(),"%"	,"MOD")));}
<YYINITIAL> "<"  { return (new Symbol(sym.MENOR				,yyline,yychar, new Yytoken(yytext(),"<"	,"MENOR")));}
<YYINITIAL> ">"  { return (new Symbol(sym.MAYOR				,yyline,yychar, new Yytoken(yytext(),">"	,"MAYOR")));}
<YYINITIAL> "<=" { return (new Symbol(sym.MENORIGUAL		,yyline,yychar, new Yytoken(yytext(),"<="	,"MENORIGUAL")));}
<YYINITIAL> ">=" { return (new Symbol(sym.MAYORIGUAL		,yyline,yychar, new Yytoken(yytext(),">="	,"MAYORIGUAL")));}
<YYINITIAL> "==" { return (new Symbol(sym.IGUAL				,yyline,yychar, new Yytoken(yytext(),"=="	,"IGUAL")));}
<YYINITIAL> "&&" { return (new Symbol(sym.AND				,yyline,yychar, new Yytoken(yytext(),"&&"	,"AND")));}
<YYINITIAL> "||" { return (new Symbol(sym.OR				,yyline,yychar, new Yytoken(yytext(),"||"	,"OR")));}
<YYINITIAL> "!"  { return (new Symbol(sym.NOT				,yyline,yychar, new Yytoken(yytext(),"!"	,"NOT")));}
<YYINITIAL> "eq" { return (new Symbol(sym.EQ				,yyline,yychar, new Yytoken(yytext(),"eq"	,"EQ")));}
<YYINITIAL> "ne" { return (new Symbol(sym.NE				,yyline,yychar, new Yytoken(yytext(),"ne"	,"NE")));}
<YYINITIAL> "in" { return (new Symbol(sym.IN				,yyline,yychar, new Yytoken(yytext(),"in"	,"IN")));}
<YYINITIAL> "ni" { return (new Symbol(sym.NI				,yyline,yychar, new Yytoken(yytext(),"ni"	,"NI")));}

	

<YYINITIAL> "set"      { return (new Symbol(sym.SET		,yyline,yychar, new Yytoken(yytext(),"set"	   ,"SET")));}	
<YYINITIAL> "puts"     { return (new Symbol(sym.PUTS	,yyline,yychar, new Yytoken(yytext(),"puts"    ,"PUTS")));}
<YYINITIAL> "expr"     { return (new Symbol(sym.EXPR	,yyline,yychar, new Yytoken(yytext(),"expr"    ,"EXPR")));}
<YYINITIAL> "if"       { return (new Symbol(sym.IF		,yyline,yychar, new Yytoken(yytext(),"if"      ,"IF")));}
<YYINITIAL> "else"     { return (new Symbol(sym.ELSE	,yyline,yychar, new Yytoken(yytext(),"else"    ,"ELSE")));}
<YYINITIAL> "switch"   { return (new Symbol(sym.SWITCH	,yyline,yychar, new Yytoken(yytext(),"switch"  ,"SWITCH")));}
<YYINITIAL> "default"  { return (new Symbol(sym.DEFAULT	,yyline,yychar, new Yytoken(yytext(),"default" ,"DEFAULT")));}
<YYINITIAL> "while"    { return (new Symbol(sym.WHILE	,yyline,yychar, new Yytoken(yytext(),"while"   ,"WHILE")));}
<YYINITIAL> "continue" { return (new Symbol(sym.CONTINUE,yyline,yychar, new Yytoken(yytext(),"continue","CONTINUE")));}
<YYINITIAL> "break"    { return (new Symbol(sym.BREAK	,yyline,yychar, new Yytoken(yytext(),"break"   ,"BREAK")));}
<YYINITIAL> "for"      { return (new Symbol(sym.FOR		,yyline,yychar, new Yytoken(yytext(),"for"     ,"FOR")));}
<YYINITIAL> "incr"     { return (new Symbol(sym.INCR	,yyline,yychar, new Yytoken(yytext(),"incr"    ,"INCR")));}
<YYINITIAL> "proc"     { return (new Symbol(sym.PROC	,yyline,yychar, new Yytoken(yytext(),"proc"    ,"PROC")));}
<YYINITIAL> "return"   { return (new Symbol(sym.RETURN	,yyline,yychar, new Yytoken(yytext(),"return"  ,"RETURN")));}	
<YYINITIAL> "$"        { return (new Symbol(sym.ASIG	,yyline,yychar, new Yytoken(yytext(),"$"       ,"ASIG")));}

	
<YYINITIAL> {blanco} { }
<YYINITIAL> {espacio} { }
<YYINITIAL> {saltoLinea} { }
 
<YYINITIAL>  \"{cadenaTexto}\" {
	String str =  yytext().substring(1,yytext().length() - 1);
	
	return (new Symbol(sym.AGRUPACION,yyline,yychar, new Yytoken(yytext(),"(\\\\\"|[^\\n\\\"]|\\[\\n\\ \\t\\b\\012]+\\)*","AGRUPACION")));
}
<YYINITIAL> \#{cadenaTexto} {
	String str =  yytext().substring(1,yytext().length());

	return (new Symbol(sym.COMENTARIO,yyline,yychar, new Yytoken(yytext(),"#([^\\n]|\\|~|`|.|,)*","COMENTARIO")));
} 
<YYINITIAL> {digito}+ { 
	return (new Symbol(sym.DIGITO,yyline,yychar, new Yytoken(yytext(),"[0-9]","DIGITO")));
}	

<YYINITIAL> {decimal} { 
	return (new Symbol(sym.DIGITODECIMAL,yyline,yychar, new Yytoken(yytext(),"[0-9]+(\".\"[  |0-9]+)?","DIGITODECIMAL")));
}

<YYINITIAL> {identificador} {
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}	
<YYINITIAL,COMMENT> . {
    return    (new Symbol(sym.ERROR,yyline,yychar, new Yytoken(yytext(),"ERROR","ERROR")));
}

