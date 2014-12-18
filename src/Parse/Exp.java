package Parse;

import ErrorMsg.Pos;


public abstract class Exp extends Absyn
{
	public Exp(Pos pos)
	{
		super(pos);
	}

	public abstract ExpTy semant();
}
