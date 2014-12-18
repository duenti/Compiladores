package Parse;

import ErrorMsg.Pos;
import Util.Tree;

public class NilExp extends Exp
{
	public NilExp(Pos pos)
	{
		super(pos);
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("nil");
	}

	@Override
	public ExpTy semant()
	{
		return new ExpTy(null, NIL);
	}
}
