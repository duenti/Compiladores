package Parse;

import ErrorMsg.Pos;
import Util.Lst;
import Util.Tree;

public class SeqExp extends Exp
{
	public Lst<Exp> list;
	
	public SeqExp(Pos pos, Lst<Exp> list)
	{
		super(pos);
		this.list = list;
	}

	@Override
	public Tree<String> toTree()
	{
		Tree<String> tree = new Tree<String>("SeqExp");
		for (Exp e : list)
			tree.addChild(e.toTree());
		return tree;
	}

	@Override
	public ExpTy semant()
	{
		// the type of the empty sequence is void
		ExpTy et = new ExpTy(null, VOID);
		
		// compile each expression from the sequence
		for (Exp e : list)
			et = e.semant();
		
		return et;
	}
}
