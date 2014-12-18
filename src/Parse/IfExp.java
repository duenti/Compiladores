package Parse;

import ErrorMsg.Pos;
import Util.Tree;

public class IfExp extends Exp
{
	public Exp test;
	public Exp thenclause;
	public Exp elseclause;

	public IfExp(Pos pos, Exp test, Exp thenclause, Exp elseclause)
	{
		super(pos);
		this.test = test;
		this.thenclause = thenclause;
		this.elseclause = elseclause;
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("IfExp")
						.addChild(test.toTree())
						.addChild(thenclause.toTree())
						.addChild(elseclause == null ? new Tree<String>("") : elseclause.toTree());
	}

	@Override
	public ExpTy semant()
	{
		ExpTy t = test.semant();
		if (! t.ty.coerceTo(INT))
			test.error("condition of if expression should be int");

		ExpTy a = thenclause.semant();
		if (elseclause == null)
		{
			if (! a.ty.coerceTo(VOID))
				thenclause.error("then clause should be void when else clause is missing in if expression");
			return new ExpTy(null, VOID);
		}
		ExpTy b = elseclause.semant();
		if (a.ty.coerceTo(b.ty))
			return new ExpTy(null, b.ty);
		if (b.ty.coerceTo(a.ty))
			return new ExpTy(null, a.ty);
		error("then clause and else clause should have compatible types in if expression");
		return new ExpTy(null, a.ty);
	}
}
