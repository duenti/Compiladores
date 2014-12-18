package Parse;

import ErrorMsg.Pos;
import Symbol.Symbol;
import Util.Tree;

public class FieldsVar extends Var {

	public Symbol name1;
	public Symbol name2;
	
	public FieldsVar(Pos pos, Symbol name1, Symbol name2){
		super(pos);
		this.name1 = name1;
		this.name2 = name2;
	}
	
	@Override
	public Tree<String> toTree() {
		return new Tree<String>("SimpleVar " + name1 + "." + name2);
	}

	@Override
	public ExpTy semant() {
		Entry entry1 = env.venv.get(name1);
		//Entry entry2 = env.venv.get(name2);
		
		if (entry1 == null)
			error("undefined variable: " + name1);
		else if (entry1 instanceof VarEntry)
			return new ExpTy(null, ((VarEntry) entry1).ty);
		else
			error("not a variable: " + name1);
		return new ExpTy(null, INT);
	}

}
