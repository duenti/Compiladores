package Parse;

import ErrorMsg.Pos;

public abstract class Dec extends Absyn
{
	public Dec(Pos pos)
	{
		super(pos);
	}
	
	public abstract Translate.Exp semant();
}
