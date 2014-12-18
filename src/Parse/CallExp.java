package Parse;

import ErrorMsg.Pos;
import Symbol.Symbol;
import Types.Field;
import Util.Lst;
import Util.Tree;

public class CallExp extends Exp
{
	public Symbol name;
	public Lst<Exp> args;
	
	public CallExp(Pos pos, Symbol name, Lst<Exp> args)
	{
		super(pos);
		this.name = name;
		this.args = args;
	}

	@Override
	public Tree<String> toTree()
	{
		Tree<String> t = new Tree<String>("CallExp: " + name);
		for (Exp e : args)
			t.addChild(e.toTree());
		return t;
	}

	@Override
	public ExpTy semant()
	{
		Entry entry = env.venv.get(name);
		if (entry == null)
			error("undefined function: " + name);
		else if (entry instanceof FunEntry)
		{
			FunEntry funentry = (FunEntry) entry;
			Lst<Field> formals = funentry.formals;
			Lst<Exp> actuals = args;
			while (! formals.isEmpty() && ! actuals.isEmpty())
			{
				Exp e = actuals.head();
				ExpTy et = e.semant();
				if (! et.ty.coerceTo(formals.head().type))
					e.error("type mismatch in function argument");
				formals = formals.tail();
				actuals = actuals.tail();
			}
			if (! formals.isEmpty())
				error("too few arguments to function");
			if (! actuals.isEmpty())
			{
				for (Exp e : actuals)
					e.semant();
				error("too much arguments to function");
			}
			return new ExpTy(null, funentry.result);
		}
		else
			error("not a function name: " + name);

		for (Exp e : args)
			e.semant();
		return new ExpTy(null, INT);
	}

}
