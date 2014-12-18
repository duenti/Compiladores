package Parse;

import ErrorMsg.Pos;
import Translate.Exp;
import Types.NAME;
import Types.Type;
import Util.Lst;
import Util.Tree;

public class MutualTypeDecs extends Dec
{
	public Lst<TypeDec> list;

	public MutualTypeDecs(Pos pos, Lst<TypeDec> list)
	{
		super(pos);
		this.list = list;
	}

	@Override
	public Tree<String> toTree()
	{
		Tree<String> tree = new Tree<String>("MutualTypeDecs");
		for (TypeDec dec : list)
			tree.addChild(dec.toTree());
		return tree;
	}

	@Override
	public Exp semant()
	{
		// pass 1: add type names to the environment
		for (TypeDec dec : list)
			env.tenv.put(dec.name, new NAME(dec.name));

		// pass 2: compile each type definition
		for (TypeDec dec : list)
		{
			Type type = dec.type.semant();
			NAME origType = (NAME) env.tenv.get(dec.name);
			origType.bind(type);
		}

		return null;
	}
}
