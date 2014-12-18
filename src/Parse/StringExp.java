package Parse;

import ErrorMsg.Pos;
import Util.Tree;

public class StringExp extends Exp
{
	public String val;

	public StringExp(Pos pos, String val)
	{
		super(pos);
		this.val = val;
	}

	public Tree<String> toTree()
	{
		return new Tree<String>("StringExp " + val);
	}

	@Override
	public ExpTy semant()
	{
		return new ExpTy(null,STRING);
	}
}
