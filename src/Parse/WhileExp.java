package Parse;

import Util.Tree;
import ErrorMsg.Pos;

public class WhileExp extends Exp {

	public Exp test;
	public Exp a;
	public boolean br;
	
	public WhileExp(Pos pos, Exp test, Exp a, boolean br){
		super(pos);
		this.test = test;
		this.a = a;
		this.br = br;
	}
	
	@Override
	public Tree<String> toTree() {
		return new Tree<String>("WhileExp")
				.addChild(test.toTree())
				.addChild(a.toTree());
	}

	@Override
	public ExpTy semant() {
		ExpTy t = test.semant();
		if (! t.ty.coerceTo(INT))
			test.error("Second condition of for expression should be int");
		
		ExpTy as = a.semant();
		
		
		return new ExpTy(null,as.ty);
	}

}
