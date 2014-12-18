package Parse;

import ErrorMsg.Pos;

public abstract class Var extends Absyn
{
	public Var(Pos pos)
	{
		super(pos);
	}

	public abstract ExpTy semant();
}
