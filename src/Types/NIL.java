package Types;

import Util.Tree;

public class NIL extends Type
{
	@Override
	public boolean coerceTo(Type t)
	{
		Type a = t.actual();
		return (a instanceof RECORD) || (a instanceof NIL);
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("NIL");
	}
}
