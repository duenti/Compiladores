package Parse;

import ErrorMsg.Pos;
import Symbol.Symbol;
import Types.Type;
import Util.Tree;

public class NameTy extends Ty
{
	public Symbol name;
	
	public NameTy(Pos pos, Symbol name)
	{
		super(pos);
		this.name = name;
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>(name.toString());
	}

	@Override
	public Type semant()
	{
		Type type = env.tenv.get(name);
		if (type == null)
		{
			error("undefined type: " + name);
			return VOID;
		}
		return type;
	}
}
