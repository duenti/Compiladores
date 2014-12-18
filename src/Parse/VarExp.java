package Parse;

import ErrorMsg.Pos;
import Util.Tree;

public class VarExp extends Exp
{
	Var v;

	public VarExp(Pos pos, Var v)
	{
		super(pos);
		this.v = v;
	}

	@Override
	public ExpTy semant()
	{
		return v.semant();
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("VarExp").addChild(v.toTree());
	}

}
