package Parse;

import ErrorMsg.Pos;
import Util.Lst;
import Util.Tree;


public class LetExp extends Exp
{
	public Lst<Dec> decs;
	public Exp body;
	
	public LetExp(Pos pos, Lst<Dec> decs, Exp e)
	{
		super(pos);
		this.decs = decs;
		this.body = e;
	}

	@Override
	public Tree<String> toTree()
	{
		Tree<String> td = new Tree<String>("Decs");
		for (Dec d : decs)
			td.addChild(d.toTree());

		return new Tree<String>("LetExp").addChild(td).addChild(body.toTree());
	}

	@Override
	public ExpTy semant()
	{
		env.tenv.beginScope();
		env.venv.beginScope();
		for (Dec d : decs)
			d.semant();
		ExpTy result = body.semant();
		env.venv.endScope();
		env.tenv.endScope();
		return result;
	}

}
