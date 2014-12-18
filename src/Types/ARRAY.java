package Types;

import Util.Tree;

public class ARRAY extends Type
{
	public final Type element;

	public ARRAY(Type element)
	{
		this.element = element;
	}

	@Override
	public boolean coerceTo(Type t)
	{
		return this == t.actual();
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("ARRAY").addChild(element.toTree());
	}
}
