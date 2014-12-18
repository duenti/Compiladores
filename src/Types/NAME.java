package Types;

import Util.Tree;

public class NAME extends Type
{
	public Symbol.Symbol name;
	private Type binding;

	public NAME(Symbol.Symbol name)
	{
		this.name = name;
	}

	public void bind(Type t)
	{
		binding = t;
	}

	@Override
	public Type actual()
	{
		return  binding.actual();
	}

	@Override
	public boolean coerceTo(Type t)
	{
		return this.actual().coerceTo(t);
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>(name.toString());
	}
}
