package Types;

import Util.Tree;

public class VOID extends Type
{
	@Override
	public boolean coerceTo(Type t)
	{
		return t.actual() instanceof VOID;
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("VOID");
	}
}
