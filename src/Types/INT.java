package Types;

import Util.Tree;

public class INT extends Type
{
	@Override
	public boolean coerceTo(Type t)
	{
		return t.actual() instanceof INT;
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("INT");
	}
}
