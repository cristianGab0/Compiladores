%%

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
%%
<YYINITIAL> "{"  { return (new Yytoken(0,"Llave Apertura",yytext(),yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "}"  { return (new Yytoken(1,"Llave Cierre",yytext(),yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "["  { return (new Yytoken(2,"Corchete Apertura",yytext(),yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "]"  { return (new Yytoken(3,"Corchete Cierre",yytext(),yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "-"  { return (new Yytoken(4,"Menos",yytext(),yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "+"  { return (new Yytoken(5,"Mas",yytext(),yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "**" { return (new Yytoken(6,"Exponente",yytext(),yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "*"  { return (new Yytoken(7,"Multiplicacion",yytext(),yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "/"  { return (new Yytoken(8,"Division",yytext(),yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "%"  { return (new Yytoken(9,"Mod",yytext(),yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "<"  { return (new Yytoken(10,"Menor que",yytext(),yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> ">"  { return (new Yytoken(11,"Mayor que",yytext(),yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "<=" { return (new Yytoken(12,"Menor o Igual",yytext(),yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> ">=" { return (new Yytoken(13,"Mayor o Igual",yytext(),yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "==" { return (new Yytoken(14,"Igual",yytext(),yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "&&" { return (new Yytoken(15,"AND",yytext(),yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "||" { return (new Yytoken(16,"OR",yytext(),yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "!"  { return (new Yytoken(17,"NOT",yytext(),yytext(),yyline,yychar,yychar+1)); }
<YYINITIAL> "eq" { return (new Yytoken(18,"Igual que",yytext(),yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "ne" { return (new Yytoken(19,"No igual que",yytext(),yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "in" { return (new Yytoken(20,"Se encuentra en",yytext(),yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "ni" { return (new Yytoken(21,"No se Encuentra en",yytext(),yytext(),yyline,yychar,yychar+2)); }

	

<YYINITIAL> "set"      { return (new Yytoken(22,yytext(),yytext(),yytext(),yyline,yychar,yychar+3)); }
<YYINITIAL> "puts"     { return (new Yytoken(23,yytext(),yytext(),yytext(),yyline,yychar,yychar+4)); }
<YYINITIAL> "expr"     { return (new Yytoken(24,yytext(),yytext(),yytext(),yyline,yychar,yychar+4)); }
<YYINITIAL> "if"       { return (new Yytoken(25,yytext(),yytext(),yytext(),yyline,yychar,yychar+2)); }
<YYINITIAL> "then"     { return (new Yytoken(26,yytext(),yytext(),yytext(),yyline,yychar,yychar+4)); }
<YYINITIAL> "else"     { return (new Yytoken(27,yytext(),yytext(),yytext(),yyline,yychar,yychar+4)); }
<YYINITIAL> "elseif"   { return (new Yytoken(28,yytext(),yytext(),yytext(),yyline,yychar,yychar+6)); }
<YYINITIAL> "switch"   { return (new Yytoken(29,yytext(),yytext(),yytext(),yyline,yychar,yychar+6)); }
<YYINITIAL> "default"  { return (new Yytoken(30,yytext(),yytext(),yytext(),yyline,yychar,yychar+7)); }
<YYINITIAL> "while"    { return (new Yytoken(31,yytext(),yytext(),yytext(),yyline,yychar,yychar+5)); }
<YYINITIAL> "continue" { return (new Yytoken(32,yytext(),yytext(),yytext(),yyline,yychar,yychar+8)); }
<YYINITIAL> "break"    { return (new Yytoken(33,yytext(),yytext(),yytext(),yyline,yychar,yychar+5)); }
<YYINITIAL> "for"      { return (new Yytoken(34,yytext(),yytext(),yytext(),yyline,yychar,yychar+3)); }
<YYINITIAL> "incr"     { return (new Yytoken(35,yytext(),yytext(),yytext(),yyline,yychar,yychar+4)); }
<YYINITIAL> "proc"     { return (new Yytoken(36,yytext(),yytext(),yytext(),yyline,yychar,yychar+4)); }
<YYINITIAL> "return"   { return (new Yytoken(37,yytext(),yytext(),yytext(),yyline,yychar,yychar+6)); }
<YYINITIAL> "$"        { return (new Yytoken(38,yytext(),yytext(),yytext(),yyline,yychar,yychar+1)); }
	
<YYINITIAL> {espacio} { }
<YYINITIAL> {saltoLinea} { }

<YYINITIAL> \"{cadenaTexto}\" {
	String str =  yytext().substring(1,yytext().length() - 1);
	
	Utility.confirmar(str.length() == yytext().length() - 2);
	return (new Yytoken(40,"Cadena de texto",str,"(\\\\\"|[^\\n\\\"]|\\[\\n\\ \\t\\b\\012]+\\)*",yyline,yychar,yychar + str.length()));
}
<YYINITIAL> \#{cadenaTexto} {
	String str =  yytext().substring(1,yytext().length());

	Utility.confirmar(str.length() == yytext().length() - 1);
	return (new Yytoken(41,"Comentario",str,"#([^\\n]|\\|~|`|.|,)*",yyline,yychar,yychar + str.length()));
} 
<YYINITIAL> {digito}+ { 
	return (new Yytoken(42,"Digito",yytext(),"[0-9]",yyline,yychar,yychar + yytext().length()));
}	
<YYINITIAL> {identificador} {
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}	
<YYINITIAL,COMMENT> . {
        return (new Yytoken(44,"Error",yytext(),"",yyline,yychar,yychar + yytext().length()));
}
