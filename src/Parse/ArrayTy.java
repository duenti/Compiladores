package Parse;

import ErrorMsg.Pos;
import Types.ARRAY;
import Types.Type;
import Util.Tree;

public class ArrayTy extends Ty
{
	public NameTy element;

	public ArrayTy(Pos pos, NameTy element)
	{
		super(pos);
		this.element = element;
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("ArrayTy").addChild(element.toTree());
	}

	@Override
	public Type semant()
	{
		// check the type of the elements
		Type t = env.tenv.get(element.name);
		if (t == null)
		{
			element.error("undefined type: " + element.name);
			t = INT;
		}
		
		// array type
		return new ARRAY(t);
	}
}
