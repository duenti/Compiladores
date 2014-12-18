package Parse;

import ErrorMsg.Pos;
import Types.ARRAY;
import Util.Tree;

public class SubscriptVar extends Var
{
	public Var var;
	public Exp index;

	public SubscriptVar(Pos pos, Var var, Exp index)
	{
		super(pos);
		this.var = var;
		this.index = index;
	}
	
	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("SubscriptVar")
						.addChild(var.toTree())
						.addChild(index.toTree());
	}

	@Override
	public ExpTy semant()
	{
		ExpTy v = var.semant();
		ExpTy i = index.semant();
		if (! i.ty.coerceTo(INT))
			index.error("array index should be int");
		if (v.ty instanceof ARRAY)
			return new ExpTy(null, ((ARRAY)v.ty).element);
		else
		{
			var.error("only arrays can be indexed");
			return new ExpTy(null, INT);
		}
	}
}
