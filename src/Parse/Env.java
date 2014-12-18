package Parse;

import Types.Field;
import Util.Lst;

class Env
{
	Symbol.Table<Types.Type> tenv;
	Symbol.Table<Entry> venv;

	Env()
	{
		tenv = new Symbol.Table<Types.Type>();
		venv = new Symbol.Table<Entry>();

		tenv.put(sym("int"), new Types.INT());
		tenv.put(sym("string"), new Types.STRING());

		venv.put(sym("print"),
				new FunEntry(Lst.list(new Field(sym("s"), Absyn.STRING)),
						     Absyn.VOID));
		venv.put(sym("flush"),
				new FunEntry(null, Absyn.VOID));
		venv.put(sym("concat"),
				new FunEntry(Lst.list(new Field(sym("s1"), Absyn.STRING),
						              new Field(sym("s2"), Absyn.STRING)),
							 Absyn.STRING));
		//...
	}

	private Symbol.Symbol sym(String name)
	{
		return Symbol.Symbol.symbol(name);
	}
}

abstract class Entry
{
}

class VarEntry extends Entry
{
	Types.Type ty;

	VarEntry(Types.Type ty)
	{
		this.ty = ty;
	}
}

class FunEntry extends Entry
{
	Lst<Field> formals;
	Types.Type result;

	FunEntry(Lst<Field> formals, Types.Type result)
	{
		this.formals = formals;
		this.result = result;
	}
}
