package Parse;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Formatter;

import ErrorMsg.ErrorMsg;

public class Main
{
	private static void test_lexer() throws IOException
	{
		Lexer lex = new Lexer(System.in);

		java_cup.runtime.Symbol tok;
		do
		{
			tok = lex.next_token();
			System.out.println(tok);
		}
		while (tok.sym != sym.EOF);		
	}
	
	private static void run() throws Exception
	{
		Lexer lex = new Lexer(System.in);        
		Parser p = new Parser(lex);
//		try
//		{
			java_cup.runtime.Symbol s = p.parse();
			Exp prog = (Exp)s.value;
			System.out.println(prog.toTree().prettyPrint());
			System.out.printf("\n==> %s\n", prog.semant().ty.toTree().prettyPrint());
//		}
//		catch (Exception e)
//		{
//			System.err.println("Erro");
//			throw e;
//		}
	}

	public static void main(String[] args) throws Exception
	{
		java.io.Reader input;
		String fileName;
		ErrorMsg err;

		if (args.length == 0)
		{
			fileName = "stdin";
			input = new java.io.InputStreamReader(System.in);
			err = new ErrorMsg("-");
		}
		else
		{
			fileName = args[0];
			input = new java.io.FileReader(fileName);
			err = new ErrorMsg(fileName);
		}

		Parse.Lexer lex = new Parse.Lexer(input, err);
		Parse.Parser p = new Parse.Parser(lex, err);

		java_cup.runtime.Symbol prog = p.parse();

		Exp parseTree = (Exp) prog.value;

		System.out.println(parseTree.toTree().prettyPrint());
		System.out.println();

		Absyn.e = err;
		ExpTy et = parseTree.semant();

		System.out.printf("\n==> %s\n", et.ty.toTree().prettyPrint());
	}
}
