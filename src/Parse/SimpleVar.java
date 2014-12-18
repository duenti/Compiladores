package Parse;

import ErrorMsg.Pos;
import Symbol.Symbol;
import Util.Tree;

public class SimpleVar extends Var
{
	Symbol name;
	
	public SimpleVar(Pos pos, Symbol name)
	{
		super(pos);
		this.name = name;
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("SimpleVar " + name);
	}

	@Override
	public ExpTy semant()
	{
		Entry entry = env.venv.get(name);
		if (entry == null)
			error("undefined variable: " + name);
		else if (entry instanceof VarEntry)
			return new ExpTy(null, ((VarEntry) entry).ty);
		else
			error("not a variable: " + name);
		return new ExpTy(null, INT);
	}
}
