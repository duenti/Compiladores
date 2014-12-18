package Parse;

import ErrorMsg.Pos;
import Types.Type;

public abstract class Ty extends Absyn
{
	public Ty(Pos pos)
	{
		super(pos);
	}
	
	public abstract Type semant();
}
