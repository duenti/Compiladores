package Parse;

import ErrorMsg.Pos;
import Symbol.Symbol;
import Types.Type;
import Util.Tree;

public class VarDec extends Dec
{
	Symbol name;
	NameTy type;
	Exp init;
	
	public VarDec(Pos pos, Symbol name, NameTy type, Exp init)
	{
		super(pos);
		this.name = name;
		this.type = type;
		this.init = init;
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("VarDec")
				        .addChild(new Tree<String>(name.toString()))
				        .addChild(type == null ? new Tree<String>("") : type.toTree())
				        .addChild(init.toTree());
	}

	@Override
	public Translate.Exp semant()
	{
		ExpTy i = init.semant();
		Type t = null;
		if (type == null)
			t = i.ty;
		else
		{
			t = type.semant();
			if (! i.ty.coerceTo(t))
				error(init.pos, "type mismatch in variable initializer");
		}
		env.venv.put(name, new VarEntry(t));
		return null;
	}
}
