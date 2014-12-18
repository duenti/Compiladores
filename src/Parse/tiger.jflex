package Parse;

import Symbol.Symbol;
import ErrorMsg.ErrorMsg;

%%

%public
%class Lexer
%cup
%char

%{
    private ErrorMsg e;

    public Lexer(java.io.Reader input, ErrorMsg errorMsg)
    {
        this(input);
        e = errorMsg;
    }
    
    private java_cup.runtime.Symbol symbol(int typ, Object val)
    {
        return new java_cup.runtime.Symbol(typ, yychar, yychar + yytext().length(), val);
    }

    private java_cup.runtime.Symbol symbol(int typ)
    {
        return symbol(typ, null);
    }
%}

%%

[ \t]+                             { /* skip */ }

\n | \n\r | \r\n                   { e.newline(yychar); }

"if"                               { return symbol(sym.IF); }
"then"                             { return symbol(sym.THEN); }
"else"                             { return symbol(sym.ELSE); }
"let"                              { return symbol(sym.LET); }
"in"                               { return symbol(sym.IN); }
"end"                              { return symbol(sym.END); }
"var"                              { return symbol(sym.VAR); }
"nil"                              { return symbol(sym.NIL); }
"var"                              { return symbol(sym.VAR); }
"function"                         { return symbol(sym.FUNCTION); }
"type"                             { return symbol(sym.TYPE); }
"array"                            { return symbol(sym.ARRAY); }
"of"                               { return symbol(sym.OF); }
"for"							   { return symbol(sym.FOR); }
"to"							   { return symbol(sym.TO); }
"end-for"						   { return symbol(sym.ENDFOR); }
"while"							   { return symbol(sym.WHILE); }
"do"							   { return symbol(sym.DO); }
"end-while"						   { return symbol(sym.ENDWHILE); }
"break"							   { return symbol(sym.BREAK); }

"("                                { return symbol(sym.LPAREN); }
")"                                { return symbol(sym.RPAREN); }
"["                                { return symbol(sym.LBRACK); }
"]"                                { return symbol(sym.RBRACK); }
"{"								   { return symbol(sym.LKEY); }
"}"								   { return symbol(sym.RKEY); }

":"                                { return symbol(sym.COLON); }
";"                                { return symbol(sym.SEMICOLON); }
","                                { return symbol(sym.COMMA); }
"="                                { return symbol(sym.EQ); }
"."								   { return symbol(syn.DOT); }

"+"                                { return symbol(sym.PLUS); }
"-"                                { return symbol(sym.MINUS); }
"*"                                { return symbol(sym.TIMES); }
"/"                                { return symbol(sym.DIV); }

"="                                { return symbol(sym.EQ); }
"<>"                               { return symbol(sym.NE); }
"<"                                { return symbol(sym.LT); }
"<="                               { return symbol(sym.LE); }
">"                                { return symbol(sym.GT); }
">="                               { return symbol(sym.GE); }

"&"                                { return symbol(sym.AND); }
"|"                                { return symbol(sym.OR); }

":="                               { return symbol(sym.ASSIGN); }

[0-9]+                             { return symbol(sym.INTEGER, new Integer(yytext())); }
                        
[a-zA-Z][a-zA-Z0-9_]*              { return symbol(sym.ID, Symbol.symbol(yytext())); }
                        
\" ([^\"\n\r\\] | \\\" | \\\\)* \" { return symbol(sym.STRING, yytext()); }

.                                  { e.error(yychar, yychar+1, "illegal character <" + yytext() + ">"); }
