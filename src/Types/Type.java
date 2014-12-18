package Types;

import Util.ToTree;

public abstract class Type implements ToTree<String>
{
	public Type actual()
	{
		return this;
	}

	public boolean coerceTo(Type t)
	{
		return false;
	}
}
