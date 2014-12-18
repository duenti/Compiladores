package Parse;

import java.util.LinkedList;
import java.util.List;

import ErrorMsg.Pos;
import Util.Tree;

public class AssignExp extends Exp
{
	public Var v;
	public Exp e;
	
	public AssignExp(Pos pos, Var v, Exp e)
	{
		super(pos);
		this.v = v;
		this.e = e;
	}

	@Override
	public ExpTy semant()
	{
		ExpTy etE = e.semant();
		ExpTy etV = v.semant();
		
		if (! etE.ty.coerceTo(etV.ty))
			error("type mismatch");
		
		return new ExpTy(null, VOID);
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("AssignExp").addChild(v.toTree()).addChild(e.toTree());
	}

}
