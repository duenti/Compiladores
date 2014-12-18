package Parse;

import ErrorMsg.Pos;
import Types.Type;
import Util.Lst;
import Util.Tree;

public class MutualFunctionDecs extends Dec
{
	public Lst<FunctionDec> list;
	
	public MutualFunctionDecs(Pos pos, Lst<FunctionDec> list)
	{
		super(pos);
		this.list = list;
	}

	@Override
	public Tree<String> toTree()
	{
		Tree<String> tree = new Tree<String>("MutualFunctionDecs");
		for (FunctionDec dec : list)
			tree.addChild(dec.toTree());
		return tree;
	}

	@Override
	public Translate.Exp semant()
	{
		for (FunctionDec fdec : list)
		{
			// check the type of each parameter
			Lst<Types.Field> ps = new Lst<Types.Field>();
			for (Field<NameTy> param : fdec.params)
			{
				Type type = env.tenv.get(param.x.name);
				if (type == null)
				{
					type = INT;
				}
				ps = new Lst<Types.Field>(new Types.Field(param.name, type), ps);
			}
			ps = ps.reverse();
			// check the type of the result
			Type result;
			if (fdec.result == null)
				result = VOID;
			else
			{
				result = env.tenv.get(fdec.result.name);
			}
			// add the function to the environment
			env.venv.put(fdec.name, new FunEntry(ps, result));
		}
		
		
		
		// check each function in the list of mutually recursive functions
		// FIX ME: does not implement mutual recursion yet
		for (FunctionDec fdec : list)
		{
			// check the type of each parameter
			Lst<Types.Field> ps = new Lst<Types.Field>();
			for (Field<NameTy> param : fdec.params)
			{
				Type type = env.tenv.get(param.x.name);
				if (type == null)
				{
					param.x.error("undefined type: " + param.x.name);
					type = INT;
				}
				ps = new Lst<Types.Field>(new Types.Field(param.name, type), ps);
			}
			ps = ps.reverse();
			// check the type of the result
			Type result;
			if (fdec.result == null)
				result = VOID;
			else
			{
				result = env.tenv.get(fdec.result.name);
				if (result == null)
				{
					fdec.result.error("undefined type: " + fdec.result.name);
					result = INT;
				}
			}
			// add the function to the environment
			env.venv.put(fdec.name, new FunEntry(ps, result));
			// check the body
			// start a new scope
			env.venv.beginScope();
			// add the parameters to the environment
			for (Types.Field param : ps)
				env.venv.put(param.name, new VarEntry(param.type));
			// compiles the body
			ExpTy body = fdec.body.semant();
			if (! body.ty.coerceTo(result))
				fdec.body.error("function body type does not match expected result");
			// end the last started scope
			env.venv.endScope();
		}
		return null;
	}
}
