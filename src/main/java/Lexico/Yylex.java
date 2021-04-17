package Lexico;

public class Yylex {
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
		65
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
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NOT_ACCEPT,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NOT_ACCEPT,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NOT_ACCEPT,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NOT_ACCEPT,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NOT_ACCEPT,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
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
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"47:8,42,37,38,47:2,41,47:18,37,15,39,43,36,9,13,47:3,7,6,47,5,47,8,44:10,47" +
":2,10,12,11,47:2,45:26,3,40,4,47,46,47,32,34,30,31,16,26,45,27,19,45,35,28," +
"45,18,33,22,17,25,20,21,23,45,29,24,45:2,1,14,2,47:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,112,
"0,1:7,2,1:2,3,4,5,1,6,1,7,8,9,10,1:6,11:3,12,11,1,11:3,13,11:11,14,15,11,16" +
",14,17,18,19,20,21,14,22,23,1,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38," +
"39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63," +
"64,65,66,67,68,69,70,71,72,73")[0];

	private int yy_nxt[][] = unpackFromString(74,48,
"1,2,3,4,5,6,7,8,9,10,11,12,13,49,54,14,15,50,55,59,79,92,93,50:2,106,80,50:" +
"2,100,111,109,50:2,101,50,16,17,18,58,61,-1,61,19,20,50,61:2,-1:55,21,-1:52" +
",22,-1:47,23,-1:47,24,-1:51,50,27,50:6,81,50:3,82,50:7,-1:8,50:3,-1:38,17,-" +
"1:48,18,-1:10,19:37,-1:2,51,19:7,-1:44,20,-1:19,50:20,-1:8,50:3,-1:17,50:14" +
",68,50:5,-1:8,50:3,-1:17,50:3,74,50:16,-1:8,50:3,-1:2,48:37,-1,32,53,48:7,-" +
"1:13,25,-1:35,19:36,56,63,19,51,19,56,19:5,-1,48:36,57,60,52,53,48,57,48:5," +
"-1:14,26,-1:49,28,50:2,29,50:16,-1:8,50:3,-1:2,19:36,56,63,-1,51,19,56,19:5" +
",-1,48:36,57,60,32,53,48,57,48:5,-1:16,50:2,30,50:7,31,50:9,-1:8,50:3,-1:38" +
",60:2,-1,48,-1,60,-1:21,50:5,33,50:14,-1:8,50:3,-1:38,63:2,-1,19,-1,63,-1:2" +
"1,50:9,34,50:10,-1:8,50:3,-1,1,61:37,-1,61:2,-1,61:6,-1:16,50:9,35,50:10,-1" +
":8,50:3,-1:17,36,50:19,-1:8,50:3,-1:17,50:9,37,50:10,-1:8,50:3,-1:17,50:2,3" +
"8,50:17,-1:8,50:3,-1:17,50:4,39,50:15,-1:8,50:3,-1:17,50:14,40,50:5,-1:8,50" +
":3,-1:17,41,50:19,-1:8,50:3,-1:17,50:19,42,-1:8,50:3,-1:17,50:10,43,50:9,-1" +
":8,50:3,-1:17,50:11,44,50:8,-1:8,50:3,-1:17,50:2,45,50:17,-1:8,50:3,-1:17,5" +
"0:5,46,50:14,-1:8,50:3,-1:17,47,50:19,-1:8,50:3,-1:17,62,50:12,102,50:6,-1:" +
"8,50:3,-1:17,50:17,64,50:2,-1:8,50:3,-1:17,50:6,66,50:13,-1:8,50:3,-1:17,50" +
":4,67,50:15,-1:8,50:3,-1:17,69,50:19,-1:8,50:3,-1:17,50:5,70,50:14,-1:8,50:" +
"3,-1:17,50:17,71,50:2,-1:8,50:3,-1:17,50:12,72,50:7,-1:8,50:3,-1:17,50:16,7" +
"3,50:3,-1:8,50:3,-1:17,50:14,75,50:5,-1:8,50:3,-1:17,50:9,76,50:10,-1:8,50:" +
"3,-1:17,50:12,77,50:7,-1:8,50:3,-1:17,50:7,78,50:12,-1:8,50:3,-1:17,50:11,8" +
"3,50:8,-1:8,50:3,-1:17,50:7,84,50,85,50:10,-1:8,50:3,-1:17,50:3,86,50:16,-1" +
":8,50:3,-1:17,87,50:19,-1:8,50:3,-1:17,50:5,88,50:14,-1:8,50:3,-1:17,50:7,8" +
"9,50:12,-1:8,50:3,-1:17,50:7,90,50:12,-1:8,50:3,-1:17,50:2,91,50:17,-1:8,50" +
":3,-1:17,50:11,94,50:8,-1:8,50:3,-1:17,50:9,95,50:10,-1:8,50:3,-1:17,50:3,9" +
"6,50:16,-1:8,50:3,-1:17,50:5,97,50:14,-1:8,50:3,-1:17,50:16,98,50:3,-1:8,50" +
":3,-1:17,50:3,99,50:16,-1:8,50:3,-1:17,103,50:19,-1:8,50:3,-1:17,50:10,104," +
"50:9,-1:8,50:3,-1:17,50:5,105,50:14,-1:8,50:3,-1:17,107,50:19,-1:8,50:3,-1:" +
"17,50:2,108,50:17,-1:8,50:3,-1:17,50:17,110,50:2,-1:8,50:3,-1");

	public Yytoken yylex ()
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
						{ return (new Yytoken(0,"Llave Apertura",yytext(),yytext(),yyline,yychar,yychar+1)); }
					case -3:
						break;
					case 3:
						{ return (new Yytoken(1,"Llave Cierre",yytext(),yytext(),yyline,yychar,yychar+1)); }
					case -4:
						break;
					case 4:
						{ return (new Yytoken(2,"Corchete Apertura",yytext(),yytext(),yyline,yychar,yychar+1)); }
					case -5:
						break;
					case 5:
						{ return (new Yytoken(3,"Corchete Cierre",yytext(),yytext(),yyline,yychar,yychar+1)); }
					case -6:
						break;
					case 6:
						{ return (new Yytoken(4,"Menos",yytext(),yytext(),yyline,yychar,yychar+1)); }
					case -7:
						break;
					case 7:
						{ return (new Yytoken(5,"Mas",yytext(),yytext(),yyline,yychar,yychar+1)); }
					case -8:
						break;
					case 8:
						{ return (new Yytoken(7,"Multiplicacion",yytext(),yytext(),yyline,yychar,yychar+1)); }
					case -9:
						break;
					case 9:
						{ return (new Yytoken(8,"Division",yytext(),yytext(),yyline,yychar,yychar+1)); }
					case -10:
						break;
					case 10:
						{ return (new Yytoken(9,"Mod",yytext(),yytext(),yyline,yychar,yychar+1)); }
					case -11:
						break;
					case 11:
						{ return (new Yytoken(10,"Menor que",yytext(),yytext(),yyline,yychar,yychar+1)); }
					case -12:
						break;
					case 12:
						{ return (new Yytoken(11,"Mayor que",yytext(),yytext(),yyline,yychar,yychar+1)); }
					case -13:
						break;
					case 13:
						{
        return (new Yytoken(44,"Error",yytext(),"",yyline,yychar,yychar + yytext().length()));
}
					case -14:
						break;
					case 14:
						{ return (new Yytoken(17,"NOT",yytext(),yytext(),yyline,yychar,yychar+1)); }
					case -15:
						break;
					case 15:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -16:
						break;
					case 16:
						{ return (new Yytoken(38,yytext(),yytext(),yytext(),yyline,yychar,yychar+1)); }
					case -17:
						break;
					case 17:
						{ }
					case -18:
						break;
					case 18:
						{ }
					case -19:
						break;
					case 19:
						{
	String str =  yytext().substring(1,yytext().length());
	Utility.confirmar(str.length() == yytext().length() - 1);
	return (new Yytoken(41,"Comentario",str,"#([^\\n]|\\|~|`|.|,)*",yyline,yychar,yychar + str.length()));
}
					case -20:
						break;
					case 20:
						{ 
	return (new Yytoken(42,"Digito",yytext(),"[0-9]",yyline,yychar,yychar + yytext().length()));
}
					case -21:
						break;
					case 21:
						{ return (new Yytoken(6,"Exponente",yytext(),yytext(),yyline,yychar,yychar+2)); }
					case -22:
						break;
					case 22:
						{ return (new Yytoken(12,"Menor o Igual",yytext(),yytext(),yyline,yychar,yychar+2)); }
					case -23:
						break;
					case 23:
						{ return (new Yytoken(13,"Mayor o Igual",yytext(),yytext(),yyline,yychar,yychar+2)); }
					case -24:
						break;
					case 24:
						{ return (new Yytoken(14,"Igual",yytext(),yytext(),yyline,yychar,yychar+2)); }
					case -25:
						break;
					case 25:
						{ return (new Yytoken(15,"AND",yytext(),yytext(),yyline,yychar,yychar+2)); }
					case -26:
						break;
					case 26:
						{ return (new Yytoken(16,"OR",yytext(),yytext(),yyline,yychar,yychar+2)); }
					case -27:
						break;
					case 27:
						{ return (new Yytoken(18,"Igual que",yytext(),yytext(),yyline,yychar,yychar+2)); }
					case -28:
						break;
					case 28:
						{ return (new Yytoken(19,"No igual que",yytext(),yytext(),yyline,yychar,yychar+2)); }
					case -29:
						break;
					case 29:
						{ return (new Yytoken(21,"No se Encuentra en",yytext(),yytext(),yyline,yychar,yychar+2)); }
					case -30:
						break;
					case 30:
						{ return (new Yytoken(20,"Se encuentra en",yytext(),yytext(),yyline,yychar,yychar+2)); }
					case -31:
						break;
					case 31:
						{ return (new Yytoken(25,yytext(),yytext(),yytext(),yyline,yychar,yychar+2)); }
					case -32:
						break;
					case 32:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	Utility.confirmar(str.length() == yytext().length() - 2);
	return (new Yytoken(40,"Cadena de texto",str,"(\\\\\"|[^\\n\\\"]|\\[\\n\\ \\t\\b\\012]+\\)*",yyline,yychar,yychar + str.length()));
}
					case -33:
						break;
					case 33:
						{ return (new Yytoken(22,yytext(),yytext(),yytext(),yyline,yychar,yychar+3)); }
					case -34:
						break;
					case 34:
						{ return (new Yytoken(34,yytext(),yytext(),yytext(),yyline,yychar,yychar+3)); }
					case -35:
						break;
					case 35:
						{ return (new Yytoken(24,yytext(),yytext(),yytext(),yyline,yychar,yychar+4)); }
					case -36:
						break;
					case 36:
						{ return (new Yytoken(27,yytext(),yytext(),yytext(),yyline,yychar,yychar+4)); }
					case -37:
						break;
					case 37:
						{ return (new Yytoken(35,yytext(),yytext(),yytext(),yyline,yychar,yychar+4)); }
					case -38:
						break;
					case 38:
						{ return (new Yytoken(26,yytext(),yytext(),yytext(),yyline,yychar,yychar+4)); }
					case -39:
						break;
					case 39:
						{ return (new Yytoken(23,yytext(),yytext(),yytext(),yyline,yychar,yychar+4)); }
					case -40:
						break;
					case 40:
						{ return (new Yytoken(36,yytext(),yytext(),yytext(),yyline,yychar,yychar+4)); }
					case -41:
						break;
					case 41:
						{ return (new Yytoken(31,yytext(),yytext(),yytext(),yyline,yychar,yychar+5)); }
					case -42:
						break;
					case 42:
						{ return (new Yytoken(33,yytext(),yytext(),yytext(),yyline,yychar,yychar+5)); }
					case -43:
						break;
					case 43:
						{ return (new Yytoken(28,yytext(),yytext(),yytext(),yyline,yychar,yychar+6)); }
					case -44:
						break;
					case 44:
						{ return (new Yytoken(29,yytext(),yytext(),yytext(),yyline,yychar,yychar+6)); }
					case -45:
						break;
					case 45:
						{ return (new Yytoken(37,yytext(),yytext(),yytext(),yyline,yychar,yychar+6)); }
					case -46:
						break;
					case 46:
						{ return (new Yytoken(30,yytext(),yytext(),yytext(),yyline,yychar,yychar+7)); }
					case -47:
						break;
					case 47:
						{ return (new Yytoken(32,yytext(),yytext(),yytext(),yyline,yychar,yychar+8)); }
					case -48:
						break;
					case 49:
						{
        return (new Yytoken(44,"Error",yytext(),"",yyline,yychar,yychar + yytext().length()));
}
					case -49:
						break;
					case 50:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -50:
						break;
					case 51:
						{
	String str =  yytext().substring(1,yytext().length());
	Utility.confirmar(str.length() == yytext().length() - 1);
	return (new Yytoken(41,"Comentario",str,"#([^\\n]|\\|~|`|.|,)*",yyline,yychar,yychar + str.length()));
}
					case -51:
						break;
					case 52:
						{
	String str =  yytext().substring(1,yytext().length() - 1);
	Utility.confirmar(str.length() == yytext().length() - 2);
	return (new Yytoken(40,"Cadena de texto",str,"(\\\\\"|[^\\n\\\"]|\\[\\n\\ \\t\\b\\012]+\\)*",yyline,yychar,yychar + str.length()));
}
					case -52:
						break;
					case 54:
						{
        return (new Yytoken(44,"Error",yytext(),"",yyline,yychar,yychar + yytext().length()));
}
					case -53:
						break;
					case 55:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -54:
						break;
					case 56:
						{
	String str =  yytext().substring(1,yytext().length());
	Utility.confirmar(str.length() == yytext().length() - 1);
	return (new Yytoken(41,"Comentario",str,"#([^\\n]|\\|~|`|.|,)*",yyline,yychar,yychar + str.length()));
}
					case -55:
						break;
					case 58:
						{
        return (new Yytoken(44,"Error",yytext(),"",yyline,yychar,yychar + yytext().length()));
}
					case -56:
						break;
					case 59:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -57:
						break;
					case 61:
						{
        return (new Yytoken(44,"Error",yytext(),"",yyline,yychar,yychar + yytext().length()));
}
					case -58:
						break;
					case 62:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -59:
						break;
					case 64:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -60:
						break;
					case 66:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -61:
						break;
					case 67:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -62:
						break;
					case 68:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -63:
						break;
					case 69:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -64:
						break;
					case 70:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -65:
						break;
					case 71:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -66:
						break;
					case 72:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -67:
						break;
					case 73:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -68:
						break;
					case 74:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -69:
						break;
					case 75:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -70:
						break;
					case 76:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -71:
						break;
					case 77:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -72:
						break;
					case 78:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -73:
						break;
					case 79:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -74:
						break;
					case 80:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -75:
						break;
					case 81:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -76:
						break;
					case 82:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -77:
						break;
					case 83:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -78:
						break;
					case 84:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -79:
						break;
					case 85:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -80:
						break;
					case 86:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -81:
						break;
					case 87:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -82:
						break;
					case 88:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -83:
						break;
					case 89:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -84:
						break;
					case 90:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -85:
						break;
					case 91:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -86:
						break;
					case 92:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -87:
						break;
					case 93:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -88:
						break;
					case 94:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -89:
						break;
					case 95:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -90:
						break;
					case 96:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -91:
						break;
					case 97:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -92:
						break;
					case 98:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -93:
						break;
					case 99:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -94:
						break;
					case 100:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -95:
						break;
					case 101:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -96:
						break;
					case 102:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -97:
						break;
					case 103:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -98:
						break;
					case 104:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -99:
						break;
					case 105:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -100:
						break;
					case 106:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -101:
						break;
					case 107:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -102:
						break;
					case 108:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -103:
						break;
					case 109:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -104:
						break;
					case 110:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -105:
						break;
					case 111:
						{
	return (new Yytoken(43,"Identificador",yytext(),"({letra})({letra}|{digito}|_)*",yyline,yychar,yychar + yytext().length()));
}
					case -106:
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
