package Parse;

import ErrorMsg.Pos;
import Types.ARRAY;
import Types.Type;
import Util.Tree;

public class ArrayExp extends Exp
{
	public NameTy type;
	public Exp size;
	public Exp init;
	
	public ArrayExp(Pos pos, NameTy type, Exp size, Exp init)
	{
		super(pos);
		this.type = type;
		this.size = size;
		this.init = init;
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("ArrayExp")
						.addChild(type.toTree())
						.addChild(size.toTree())
						.addChild(init.toTree());
	}

	@Override
	public ExpTy semant()
	{
		ExpTy s = size.semant();
		ExpTy i = init.semant();
		if (! s.ty.coerceTo(INT))
			size.error("size of array should be int");
		Type t = env.tenv.get(type.name);
		if (t == null)
			type.error("undefined type: " + type.name);
		else
		{
			t = t.actual();
			if (t instanceof ARRAY)
			{
				if (! i.ty.coerceTo(((ARRAY) t).element))
					init.error("type mismatch in array init");
				return new ExpTy(null, t);
			}
			else
				error("not an array type: " + type.name);
		}
		return new ExpTy(null, INT);
	}
}
