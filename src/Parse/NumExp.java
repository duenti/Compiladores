package Parse;

import ErrorMsg.Pos;
import Util.Tree;

public class NumExp extends Exp
{
	public Integer val;

	public NumExp(Pos pos, Integer val)
	{
		super(pos);
		this.val = val;
	}

	public Tree<String> toTree()
	{
		return new Tree<String>("NumExp " + val);
	}

	@Override
	public ExpTy semant()
	{
		return new ExpTy(null, INT);
	}	
}
