package Types;

import Util.Tree;

public class STRING extends Type
{
	@Override
	public boolean coerceTo(Type t)
	{
		return t.actual() instanceof STRING;
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("STRING");
	}
}
