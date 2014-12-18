package Parse;

import ErrorMsg.Pos;
import Symbol.Symbol;
import Util.ToTree;
import Util.Tree;

public class Field<T extends ToTree<String>> extends Absyn implements ToTree<String>
{
	public Symbol name;
	public T x;
	public Object type;
	
	public Field(Pos pos, Symbol name, T x)
	{
		super(pos);
		this.name = name;
		this.x = x;
	}

	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("Field")
					.addChild(name.toTree())
					.addChild(x.toTree());
	}
}
