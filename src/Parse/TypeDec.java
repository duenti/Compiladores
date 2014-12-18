package Parse;

import ErrorMsg.Pos;
import Symbol.Symbol;
import Util.ToTree;
import Util.Tree;

class TypeDec implements ToTree<String>
{
	public Pos pos;
	public Symbol name;
	public Ty type;

	public TypeDec(Pos pos, Symbol name, Ty type)
	{
		this.pos = pos;
		this.name = name;
		this.type = type;
	}
	
	@Override
	public Tree<String> toTree()
	{
		return new Tree<String>("TypeDec")
						.addChild(name.toTree())
						.addChild(type.toTree());
	}
}
