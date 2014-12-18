package Parse;

import Symbol.Symbol;
import Util.Tree;
import ErrorMsg.Pos;

public class ForExp extends Exp {
	public Symbol id;
	public Exp init;
	public Exp test;
	public Exp a;
	public boolean br;
	
	public ForExp (Pos pos, Symbol id, Exp init,  Exp test, Exp a, boolean br){
		super(pos);
		this.id = id;
		this.init = init;
		this.test = test;
		this.a = a;
		this.br = br;
	}
	
	@Override
	public Tree<String> toTree() {
		return new Tree<String>("ForExp")
				.addChild(id.toTree())
				.addChild(init.toTree())
				.addChild(test.toTree())
				.addChild(a.toTree());
	}

	@Override
	public ExpTy semant() {
		
		env.tenv.beginScope();
		env.venv.beginScope();
		Entry entry = env.venv.get(id);
		if (entry == null)
			error("undefined variable: " + id);
		else{
			ExpTy i = init.semant();
			if(! i.ty.coerceTo(INT))
				init.error("First condition of expression FOR should be int");
			ExpTy t = test.semant();
			if(! t.ty.coerceTo(INT))
				init.error("Second condition of expression FOR should be int");
			ExpTy as = a.semant();
			if(! as.ty.coerceTo(VOID))
				init.error("The body of expression FOR should be void");
			if(br){
				env.venv.endScope();
				env.tenv.endScope();
				return new ExpTy(null,VOID);
			}
			env.venv.endScope();
			env.tenv.endScope();
			return new ExpTy(null,as.ty);
		}		
		env.venv.endScope();
		env.tenv.endScope();
		return new ExpTy(null,VOID);
	}

}
