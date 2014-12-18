package Parse;

import ErrorMsg.Pos;
import Types.RECORD;
import Types.Type;
import Util.Lst;
import Util.Tree;

public class RecordTy extends Ty  {
	public Lst<Field<NameTy>> params;
	
	public RecordTy (Pos pos, Lst<Field<NameTy>> params){
		super(pos);
		this.params = params;
	}
	
	public Tree<String> toTree()
	{
		Tree<String> paramTree = new Tree<String>("Params");
		
		for (Field<NameTy> param : params)
			paramTree.addChild(new Tree<String>(param.name + ":" + param.x.name));
		
		return new Tree<String>("FunctionDec")
				.addChild(paramTree);
	}

	@Override
	public Type semant(){
		return new RECORD(params);
	}
}
