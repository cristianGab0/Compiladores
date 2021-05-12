package Analizador;
import java_cup.runtime.Symbol; 


public class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

    private int comment_count = 0;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int COMMENT = 1;
	private final int yy_state_dtrans[] = {
		0,
		68
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NOT_ACCEPT,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"49:8,37,38,39,49:2,42,49:18,46,15,40,43,36,9,13,49:3,7,5,49,6,45,8,44:10,49" +
":2,10,12,11,49:2,47:26,3,41,4,49,48,49,32,34,29,31,16,26,47,30,19,47,35,27," +
"47,18,33,22,17,25,20,21,23,47,28,24,47:2,1,14,2,49:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,110,
"0,1:10,2,3,4,1,5,1:2,6,7,1:5,8:3,9,8,10,11,1,8:2,12,8:11,13,14,8,10,15,13,1" +
"6,17,18,11,19,12,13,20,21,1,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37" +
",38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62" +
",63,64,65,66,67,68")[0];

	private int yy_nxt[][] = unpackFromString(69,50,
"1,2,3,4,5,6,7,8,9,10,11,12,13,48,54,14,15,49,55,60,79,49,91,49:2,104,80,49," +
"98,109,49,107,49:2,99,49,16,17,50,56,59,62,-1,18,19,62,50,49,62:2,-1:62,20," +
"-1:49,21,-1:49,22,-1:53,49,25,49:6,81,49:2,82,49:8,-1:8,49,-1:2,49:2,-1:2,1" +
"8:38,-1:2,51,18:8,-1:44,19,58,-1:20,49:20,-1:8,49,-1:2,49:2,-1:17,49:13,70," +
"49:6,-1:8,49,-1:2,49:2,-1:39,30,-1:7,30,-1:42,31,-1:24,35,-1:29,35,-1,35,-1" +
":4,47:38,-1,32,53,47:8,-1:13,23,-1:37,18:36,57:2,66,18,51,18:4,57,18:3,-1,4" +
"7:36,61:2,64,52,53,47:4,61,47:3,-1:14,24,-1:51,26,49:2,27,49:16,-1:8,49,-1:" +
"2,49:2,-1:2,18:36,57:2,66,-1,51,18:4,57,18:3,-1:16,49:2,28,49:7,29,49:9,-1:" +
"8,49,-1:2,49:2,-1:2,47:36,61:2,64,32,53,47:4,61,47:3,-1:16,49:5,33,49:14,-1" +
":8,49,-1:2,49:2,-1:38,64:3,-1,47,-1:4,64,-1:19,49:9,34,49:10,-1:8,49,-1:2,4" +
"9:2,-1:38,66:3,-1,18,-1:4,66,-1:19,49:9,36,49:10,-1:8,49,-1:2,49:2,-1,1,62:" +
"38,-1,62:2,-1,62:7,-1:16,37,49:19,-1:8,49,-1:2,49:2,-1:17,49:9,38,49:10,-1:" +
"8,49,-1:2,49:2,-1:17,49:4,39,49:15,-1:8,49,-1:2,49:2,-1:17,49:13,40,49:6,-1" +
":8,49,-1:2,49:2,-1:17,41,49:19,-1:8,49,-1:2,49:2,-1:17,49:19,42,-1:8,49,-1:" +
"2,49:2,-1:17,49:14,43,49:5,-1:8,49,-1:2,49:2,-1:17,49:2,44,49:17,-1:8,49,-1" +
":2,49:2,-1:17,49:5,45,49:14,-1:8,49,-1:2,49:2,-1:17,46,49:19,-1:8,49,-1:2,4" +
"9:2,-1:17,63,49:11,100,49:7,-1:8,49,-1:2,49:2,-1:17,49:17,65,49:2,-1:8,49,-" +
"1:2,49:2,-1:17,49:6,67,49:13,-1:8,49,-1:2,49:2,-1:17,49:4,69,49:15,-1:8,49," +
"-1:2,49:2,-1:17,49:5,71,49:14,-1:8,49,-1:2,49:2,-1:17,49:17,72,49:2,-1:8,49" +
",-1:2,49:2,-1:17,49:11,73,49:8,-1:8,49,-1:2,49:2,-1:17,49:16,74,49:3,-1:8,4" +
"9,-1:2,49:2,-1:17,49:13,75,49:6,-1:8,49,-1:2,49:2,-1:17,49:9,76,49:10,-1:8," +
"49,-1:2,49:2,-1:17,49:11,77,49:8,-1:8,49,-1:2,49:2,-1:17,49:7,78,49:12,-1:8" +
",49,-1:2,49:2,-1:17,49:7,83,49,84,49:10,-1:8,49,-1:2,49:2,-1:17,49:3,85,49:" +
"16,-1:8,49,-1:2,49:2,-1:17,86,49:19,-1:8,49,-1:2,49:2,-1:17,49:5,87,49:14,-" +
"1:8,49,-1:2,49:2,-1:17,49:7,88,49:12,-1:8,49,-1:2,49:2,-1:17,49:7,89,49:12," +
"-1:8,49,-1:2,49:2,-1:17,49:2,90,49:17,-1:8,49,-1:2,49:2,-1:17,49:14,92,49:5" +
",-1:8,49,-1:2,49:2,-1:17,49:9,93,49:10,-1:8,49,-1:2,49:2,-1:17,49:3,94,49:1" +
"6,-1:8,49,-1:2,49:2,-1:17,49:5,95,49:14,-1:8,49,-1:2,49:2,-1:17,49:16,96,49" +
":3,-1:8,49,-1:2,49:2,-1:17,49:3,97,49:16,-1:8,49,-1:2,49:2,-1:17,101,49:19," +
"-1:8,49,-1:2,49:2,-1:17,49:10,102,49:9,-1:8,49,-1:2,49:2,-1:17,49:5,103,49:" +
"14,-1:8,49,-1:2,49:2,-1:17,105,49:19,-1:8,49,-1:2,49:2,-1:17,49:2,106,49:17" +
",-1:8,49,-1:2,49:2,-1:17,49:17,108,49:2,-1:8,49,-1:2,49:2,-1");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ return (new Symbol(sym.LLA				,yyline,yychar, new Yytoken(yytext(),"{"	,"LLA")));}
					case -3:
						break;
					case 3:
						{ return (new Symbol(sym.LLC				,yyline,yychar, new Yytoken(yytext(),"}"	,"LLC")));}
					case -4:
						break;
					case 4:
						{ return (new Symbol(sym.CA				,yyline,yychar, new Yytoken(yytext(),"["	,"CA")));}
					case -5:
						break;
					case 5:
						{ return (new Symbol(sym.CC				,yyline,yychar, new Yytoken(yytext(),"]"	,"CC")));}
					case -6:
						break;
					case 6:
						{ return (new Symbol(sym.MAS				,yyline,yychar, new Yytoken(yytext(),"+"	,"MAS")));}
					case -7:
						break;
					case 7:
						{ return (new Symbol(sym.MENOS				,yyline,yychar, new Yytoken(yytext(),"-"	,"MENOS")));}
					case -8:
						break;
					case 8:
						{ return (new Symbol(sym.MULTIPLICACION	,yyline,yychar, new Yytoken(yytext(),"*"	,"MULTIPLICACION")));}
					case -9:
						break;
					case 9:
						{ return (new Symbol(sym.DIVISION			,yyline,yychar, new Yytoken(yytext(),"/"	,"DIVISION")));}
					case -10:
						break;
					case 10:
						{ return (new Symbol(sym.MOD				,yyline,yychar, new Yytoken(yytext(),"%"	,"MOD")));}
					case -11:
						break;
					case 11:
						{ return (new Symbol(sym.MENOR				,yyline,yychar, new Yytoken(yytext(),"<"	,"MENOR")));}
					case -12:
						break;
					case 12:
						{ return (new Symbol(sym.MAYOR				,yyline,yychar, new Yytoken(yytext(),">"	,"MAYOR")));}
					case -13:
						break;
					case 13:
						{
    return    (new Symbol(sym.ERROR,yyline,yychar, new Yytoken(yytext(),"ERROR","ERROR")));
}
					case -14:
						break;
					case 14:
						{ return (new Symbol(sym.NOT				,yyline,yychar, new Yytoken(yytext(),"!"	,"NOT")));}
					case -15:
						break;
					case 15:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -16:
						break;
					case 16:
						{ return (new Symbol(sym.ASIG	,yyline,yychar, new Yytoken(yytext(),"$"       ,"ASIG")));}
					case -17:
						break;
					case 17:
						{ }
					case -18:
						break;
					case 18:
						{
	String str =  yytext().substring(1,yytext().length());
	return (new Symbol(sym.COMENTARIO,yyline,yychar, new Yytoken(yytext(),"#([^\\n]|\\|~|`|.|,)*","COMENTARIO")));
}
					case -19:
						break;
					case 19:
						{ 
	return (new Symbol(sym.DIGITO,yyline,yychar, new Yytoken(yytext(),"[0-9]","DIGITO")));
}
					case -20:
						break;
					case 20:
						{ return (new Symbol(sym.MENORIGUAL		,yyline,yychar, new Yytoken(yytext(),"<="	,"MENORIGUAL")));}
					case -21:
						break;
					case 21:
						{ return (new Symbol(sym.MAYORIGUAL		,yyline,yychar, new Yytoken(yytext(),">="	,"MAYORIGUAL")));}
					case -22:
						break;
					case 22:
						{ return (new Symbol(sym.IGUAL				,yyline,yychar, new Yytoken(yytext(),"=="	,"IGUAL")));}
					case -23:
						break;
					case 23:
						{ return (new Symbol(sym.AND				,yyline,yychar, new Yytoken(yytext(),"&&"	,"AND")));}
					case -24:
						break;
					case 24:
						{ return (new Symbol(sym.OR				,yyline,yychar, new Yytoken(yytext(),"||"	,"OR")));}
					case -25:
						break;
					case 25:
						{ return (new Symbol(sym.EQ				,yyline,yychar, new Yytoken(yytext(),"eq"	,"EQ")));}
					case -26:
						break;
					case 26:
						{ return (new Symbol(sym.NE				,yyline,yychar, new Yytoken(yytext(),"ne"	,"NE")));}
					case -27:
						break;
					case 27:
						{ return (new Symbol(sym.NI				,yyline,yychar, new Yytoken(yytext(),"ni"	,"NI")));}
					case -28:
						break;
					case 28:
						{ return (new Symbol(sym.IN				,yyline,yychar, new Yytoken(yytext(),"in"	,"IN")));}
					case -29:
						break;
					case 29:
						{ return (new Symbol(sym.IF		,yyline,yychar, new Yytoken(yytext(),"if"      ,"IF")));}
					case -30:
						break;
					case 30:
						{ }
					case -31:
						break;
					case 31:
						{ }
					case -32:
						break;
					case 32:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	return (new Symbol(sym.AGRUPACION,yyline,yychar, new Yytoken(yytext(),"(\\\\\"|[^\\n\\\"]|\\[\\n\\ \\t\\b\\012]+\\)*","AGRUPACION")));
}
					case -33:
						break;
					case 33:
						{ return (new Symbol(sym.SET		,yyline,yychar, new Yytoken(yytext(),"set"	   ,"SET")));}
					case -34:
						break;
					case 34:
						{ return (new Symbol(sym.FOR		,yyline,yychar, new Yytoken(yytext(),"for"     ,"FOR")));}
					case -35:
						break;
					case 35:
						{ 
	return (new Symbol(sym.DIGITODECIMAL,yyline,yychar, new Yytoken(yytext(),"[0-9]+(\".\"[  |0-9]+)?","DIGITODECIMAL")));
}
					case -36:
						break;
					case 36:
						{ return (new Symbol(sym.EXPR	,yyline,yychar, new Yytoken(yytext(),"expr"    ,"EXPR")));}
					case -37:
						break;
					case 37:
						{ return (new Symbol(sym.ELSE	,yyline,yychar, new Yytoken(yytext(),"else"    ,"ELSE")));}
					case -38:
						break;
					case 38:
						{ return (new Symbol(sym.INCR	,yyline,yychar, new Yytoken(yytext(),"incr"    ,"INCR")));}
					case -39:
						break;
					case 39:
						{ return (new Symbol(sym.PUTS	,yyline,yychar, new Yytoken(yytext(),"puts"    ,"PUTS")));}
					case -40:
						break;
					case 40:
						{ return (new Symbol(sym.PROC	,yyline,yychar, new Yytoken(yytext(),"proc"    ,"PROC")));}
					case -41:
						break;
					case 41:
						{ return (new Symbol(sym.WHILE	,yyline,yychar, new Yytoken(yytext(),"while"   ,"WHILE")));}
					case -42:
						break;
					case 42:
						{ return (new Symbol(sym.BREAK	,yyline,yychar, new Yytoken(yytext(),"break"   ,"BREAK")));}
					case -43:
						break;
					case 43:
						{ return (new Symbol(sym.SWITCH	,yyline,yychar, new Yytoken(yytext(),"switch"  ,"SWITCH")));}
					case -44:
						break;
					case 44:
						{ return (new Symbol(sym.RETURN	,yyline,yychar, new Yytoken(yytext(),"return"  ,"RETURN")));}
					case -45:
						break;
					case 45:
						{ return (new Symbol(sym.DEFAULT	,yyline,yychar, new Yytoken(yytext(),"default" ,"DEFAULT")));}
					case -46:
						break;
					case 46:
						{ return (new Symbol(sym.CONTINUE,yyline,yychar, new Yytoken(yytext(),"continue","CONTINUE")));}
					case -47:
						break;
					case 48:
						{
    return    (new Symbol(sym.ERROR,yyline,yychar, new Yytoken(yytext(),"ERROR","ERROR")));
}
					case -48:
						break;
					case 49:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -49:
						break;
					case 50:
						{ }
					case -50:
						break;
					case 51:
						{
	String str =  yytext().substring(1,yytext().length());
	return (new Symbol(sym.COMENTARIO,yyline,yychar, new Yytoken(yytext(),"#([^\\n]|\\|~|`|.|,)*","COMENTARIO")));
}
					case -51:
						break;
					case 52:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	return (new Symbol(sym.AGRUPACION,yyline,yychar, new Yytoken(yytext(),"(\\\\\"|[^\\n\\\"]|\\[\\n\\ \\t\\b\\012]+\\)*","AGRUPACION")));
}
					case -52:
						break;
					case 54:
						{
    return    (new Symbol(sym.ERROR,yyline,yychar, new Yytoken(yytext(),"ERROR","ERROR")));
}
					case -53:
						break;
					case 55:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -54:
						break;
					case 56:
						{ }
					case -55:
						break;
					case 57:
						{
	String str =  yytext().substring(1,yytext().length());
	return (new Symbol(sym.COMENTARIO,yyline,yychar, new Yytoken(yytext(),"#([^\\n]|\\|~|`|.|,)*","COMENTARIO")));
}
					case -56:
						break;
					case 59:
						{
    return    (new Symbol(sym.ERROR,yyline,yychar, new Yytoken(yytext(),"ERROR","ERROR")));
}
					case -57:
						break;
					case 60:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -58:
						break;
					case 62:
						{
    return    (new Symbol(sym.ERROR,yyline,yychar, new Yytoken(yytext(),"ERROR","ERROR")));
}
					case -59:
						break;
					case 63:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -60:
						break;
					case 65:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -61:
						break;
					case 67:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -62:
						break;
					case 69:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -63:
						break;
					case 70:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -64:
						break;
					case 71:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -65:
						break;
					case 72:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -66:
						break;
					case 73:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -67:
						break;
					case 74:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -68:
						break;
					case 75:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -69:
						break;
					case 76:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -70:
						break;
					case 77:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -71:
						break;
					case 78:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -72:
						break;
					case 79:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -73:
						break;
					case 80:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -74:
						break;
					case 81:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -75:
						break;
					case 82:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -76:
						break;
					case 83:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -77:
						break;
					case 84:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -78:
						break;
					case 85:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -79:
						break;
					case 86:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -80:
						break;
					case 87:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -81:
						break;
					case 88:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -82:
						break;
					case 89:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -83:
						break;
					case 90:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -84:
						break;
					case 91:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -85:
						break;
					case 92:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -86:
						break;
					case 93:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -87:
						break;
					case 94:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -88:
						break;
					case 95:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -89:
						break;
					case 96:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -90:
						break;
					case 97:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -91:
						break;
					case 98:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -92:
						break;
					case 99:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -93:
						break;
					case 100:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -94:
						break;
					case 101:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -95:
						break;
					case 102:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -96:
						break;
					case 103:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -97:
						break;
					case 104:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -98:
						break;
					case 105:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -99:
						break;
					case 106:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -100:
						break;
					case 107:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -101:
						break;
					case 108:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -102:
						break;
					case 109:
						{
	return (new Symbol(sym.IDENTIFICADOR,yyline,yychar, new Yytoken(yytext(),"({letra})({letra}|{digito}|_)*","IDENTIFICADOR")));
}
					case -103:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
