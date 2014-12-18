package Parse;

import ErrorMsg.Pos;
import Symbol.Symbol;
import Util.Lst;
import Util.ToTree;
import Util.Tree;

class FunctionDec implements ToTree<String>
{
	public Pos pos;
	public Symbol name;
	public Lst<Field<NameTy>> params;
	public NameTy result;
	public Exp body;
	
	public FunctionDec(Pos pos, Symbol name, Lst<Field<NameTy>> params, NameTy result, Exp body)
	{
		this.pos = pos;
		this.name = name;
		this.params = params;
		this.result = result;
		this.body = body;
	}
	
	@Override
	public Tree<String> toTree()
	{
		Tree<String> paramTree = new Tree<String>("Params");
		for (Field<NameTy> param : params)
			paramTree.addChild(new Tree<String>(param.name + ":" + param.x.name));
		return new Tree<String>("FunctionDec")
						.addChild(name.toTree())
						.addChild(paramTree)
						.addChild(result == null ? new Tree<String>("") : result.toTree())
						.addChild(body.toTree());
	}
}
